package cn.laiyuejia.bilibili.service.util;

import cn.laiyuejia.bilibili.domain.exception.ConditionException;
import com.github.tobato.fastdfs.domain.fdfs.FileInfo;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

@Component
public class FastDFSUtil {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private AppendFileStorageClient appendFileStorageClient;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${fdfs.http.storage-addr}")
    private String httpFdfsStorageAddr;

    private static final String DEFAULT_GROUP = "group1";
    public static final String PATH_KEY = "path-key:";
    public static final String UPLOADED_SIZE_KEY = "uploaded-size-key:";
    public static final String UPLOADED_NUMBER_KEY = "uploaded-number-key:";
    public static final int SLICE_SIZE = 1024 * 1024 * 2;

    //获取文件类型
    public String getFileType(MultipartFile file) {
        if (file == null) {
            throw new ConditionException("非法文件！");
        }
        String fileName = file.getOriginalFilename();
        int index = fileName.lastIndexOf(".");
        String fileType = fileName.substring(index + 1);
        return fileType;
    }


    //上传文件
    public String uploadCommonFile(MultipartFile file) throws IOException {
        Set<MetaData> metaDataSet = new HashSet<>();
        String fileType = this.getFileType(file);
        StorePath storePath = fastFileStorageClient.uploadFile(
                file.getInputStream(), file.getSize(), fileType, metaDataSet);
        return storePath.getPath();
    }

    //可以断点续传的文件
    public String uploadAppenderFile(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        String fileType = this.getFileType(file);
        StorePath storePath = appendFileStorageClient.uploadAppenderFile(
                DEFAULT_GROUP, file.getInputStream(), file.getSize(), fileType);
        return storePath.getPath();
    }

    //断点续传
    public void modifyAppenderFile(MultipartFile file, String filePath, long offset) throws IOException {
        appendFileStorageClient.modifyFile(
                DEFAULT_GROUP, filePath, file.getInputStream(), file.getSize(), offset);
    }

    //删除文件
    public void deleteFile(String filePath) {
        fastFileStorageClient.deleteFile(DEFAULT_GROUP+"/"+filePath);
    }


    //秒传+断点续传
    public String uploadFileBySlices(MultipartFile file, String fileMD5, Integer sliceNumber, Integer totalSliceNumber) throws IOException {
        if (file == null || sliceNumber == null || totalSliceNumber == null) {
            throw new ConditionException("参数异常！");
        }
        String pathKey = PATH_KEY + fileMD5;
        String uploadedSizeKey = UPLOADED_SIZE_KEY + fileMD5;
        String uploadedNumberKey = UPLOADED_NUMBER_KEY + fileMD5;
        String uploadedSizeStr = redisTemplate.opsForValue().get(uploadedSizeKey);
        Long uploadedSize = 0L;
        if (StringUtils.isNotBlank(uploadedSizeStr)) {
            uploadedSize = Long.valueOf(uploadedSizeStr);
        }
        String fileType = this.getFileType(file);
        if (sliceNumber == 1) { //上传第一个分片
            String path = this.uploadAppenderFile(file);
            if (StringUtils.isBlank(path)) {
                throw new ConditionException("上传失败！");
            }
            redisTemplate.opsForValue().set(pathKey, path);
            redisTemplate.opsForValue().increment(uploadedNumberKey, 1);
        } else {
            String filePath = redisTemplate.opsForValue().get(pathKey);
            if (StringUtils.isBlank(filePath)) {
                throw new ConditionException("上传失败");
            }
            this.modifyAppenderFile(file, filePath, uploadedSize);
            redisTemplate.opsForValue().increment(uploadedNumberKey,1);
        }
        //修改历史上传文件大小
        uploadedSize += file.getSize();
        redisTemplate.opsForValue().set(uploadedSizeKey, String.valueOf(uploadedSize));
        //上传完毕，清空redis值
        String uploadedNumberStr = redisTemplate.opsForValue().get(uploadedNumberKey);
        Integer uploadedNumber = Integer.valueOf(uploadedNumberStr);
        String resultPath = "";
        if (uploadedNumber.equals(totalSliceNumber)) {
            resultPath = redisTemplate.opsForValue().get(pathKey);
            List<String> keyList = Arrays.asList(uploadedNumberKey, pathKey, uploadedSizeKey);
            redisTemplate.delete(keyList);
        }
        return resultPath;
    }

    //文件分片方法
    public void convertFileToSlices(MultipartFile multipartFile) throws IOException {
        String filename = multipartFile.getOriginalFilename();
        String fileType = this.getFileType(multipartFile);
        File file = this.multipartFileToFile(multipartFile);
        long length = file.length();
        int count = 1;
        for (int i = 0; i < length; i+=SLICE_SIZE) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file,"r");
            randomAccessFile.seek(i);
            byte[] bytes = new byte[SLICE_SIZE];
            int len = randomAccessFile.read(bytes);
            String path = "/Users/laiyuejia/tmp/" + count + "." +fileType;
            File slice = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(slice);
            fileOutputStream.write(bytes,0,len);
            fileOutputStream.close();
            randomAccessFile.close();
            count++;
        }
        file.delete();
    }

    public File multipartFileToFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String[] fileName = new String[2];
        fileName[0] = originalFilename.substring(0,index);
        fileName[1] = originalFilename.substring(index+1);
        File file = File.createTempFile(fileName[0], "." + fileName[1]);
        multipartFile.transferTo(file);
        return file;
    }

    public void viewVideoOnlineBySlices(HttpServletRequest request, HttpServletResponse response, String url) throws Exception {
        FileInfo fileInfo = fastFileStorageClient.queryFileInfo(DEFAULT_GROUP, url);
        long fileSize = fileInfo.getFileSize();
        String path = httpFdfsStorageAddr+url;
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String,Object> headers = new HashMap<>();
        while(headerNames.hasMoreElements()){
            String header = headerNames.nextElement();
            headers.put(header,request.getHeader(header));
        }
        String rangeStr = request.getHeader("Range");
        String[] range;
        if(StringUtils.isBlank(rangeStr)){
            rangeStr="bytes=0-" + (fileSize-1);
        }
        range = rangeStr.split("bytes=|-");
        long begin = 0;
        if(range.length>=2){
            begin = Long.parseLong(range[1]);
        }
        long end = fileSize-1;
        if(range.length>=3){
            end = Long.parseLong(range[2]);
        }
        long len = (end-begin) +1;
        //设置响应参数
        String contentRange = "bytes " +begin + "-" + end + "/" +fileSize;
        response.setHeader("Content-Range",contentRange);
        response.setHeader("Accept-Ranges","bytes");
        response.setHeader("Content-Type","video/mp4");
        response.setContentLength((int) len);
        response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
        //发出请求
        HttpUtil.get(path,headers,response);
    }
}
