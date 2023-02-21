package cn.laiyuejia.bilibili.api;

import cn.laiyuejia.bilibili.domain.JsonResponse;
import cn.laiyuejia.bilibili.domain.Video;
import cn.laiyuejia.bilibili.service.ElasticSearchService;
import cn.laiyuejia.bilibili.service.util.FastDFSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class TestApi {

    @Autowired
    private FastDFSUtil fastDFSUtil;

    @Autowired
    private ElasticSearchService elasticSearchService;

    //测试分片功能
    @GetMapping("/slices")
    public void slices(MultipartFile file) throws IOException {
        fastDFSUtil.convertFileToSlices(file);
    }

    @GetMapping("/es-videos")
    public JsonResponse<Video> getEsVideos(@RequestParam String keyword){
        Video video = elasticSearchService.getVideo(keyword);
        return new JsonResponse<>(video);
    }

    @DeleteMapping("/es-videos")
    public JsonResponse<String> deleteVideos(){
        elasticSearchService.deleteAllVideos();
        return JsonResponse.success();
    }

    @DeleteMapping("/es-contents")
    public JsonResponse<String> deleteContents(){
        elasticSearchService.deleteAllVideos();
        elasticSearchService.deleteAllUserInfos();
        return JsonResponse.success();
    }

    @GetMapping("/contents")
    public JsonResponse<List<Map<String,Object>>> getContents(@RequestParam String keyword,
                                                              @RequestParam Integer pageNumber,
                                                              @RequestParam Integer pageSize) throws IOException {
        List<Map<String, Object>> contents = elasticSearchService.getContents(keyword, pageNumber, pageSize);
        return new JsonResponse<>(contents);
    }


}
