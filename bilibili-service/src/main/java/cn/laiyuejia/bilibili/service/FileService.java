package cn.laiyuejia.bilibili.service;

import cn.laiyuejia.bilibili.domain.File;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
* @author laiyuejia
* @description 针对表【t_file(上传文件信息表)】的数据库操作Service
* @createDate 2023-01-25 20:27:18
*/
public interface FileService extends IService<File> {

    String uploadFileBySlices(MultipartFile slice, String fileMD5, Integer sliceNumber, Integer totalSliceNumber) throws IOException;

    void deleteFile(String filePath);

    String getFileMD5(MultipartFile file) throws IOException;
}
