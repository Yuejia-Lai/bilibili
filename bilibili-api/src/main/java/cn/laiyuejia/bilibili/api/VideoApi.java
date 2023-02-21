package cn.laiyuejia.bilibili.api;

import cn.laiyuejia.bilibili.api.support.UserSupport;
import cn.laiyuejia.bilibili.domain.*;
import cn.laiyuejia.bilibili.service.*;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
public class VideoApi {

    @Autowired
    private VideoService videoService;

    @Autowired
    private UserSupport userSupport;

    @Autowired
    private VideoLikeService videoLikeService;

    @Autowired
    private VideoCollectionService videoCollectionService;

    @Autowired
    private VideoCoinService videoCoinService;

    @Autowired
    private VideoCommentService videoCommentService;

    @Autowired
    private VideoViewService videoViewService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    //添加视频
    @PostMapping("/videos")
    public JsonResponse<String> addVideos(@RequestBody Video video){
        Long userId = userSupport.getCurrentUserId();
        video.setUserId(userId);
        videoService.addVideos(video);
        //添加到es
        elasticSearchService.addVideo(videoService.getById(video.getId()));
        return JsonResponse.success();
    }

    //分页查询
    @GetMapping("/get-videos")
    public JsonResponse<PageResult<Video>> pageListVideos(Integer size,Integer page,String area){
        PageResult<Video> result = videoService.pageListVideos(size,page,area);
        return new JsonResponse<>(result);
    }

    //视频在线观看
    @GetMapping("/video-slices")
    public void viewVideoOnlineBySlices(HttpServletRequest request,
                                        HttpServletResponse response,
                                        String url) throws Exception {
        videoService.viewVideoOnlineBySlices(request,response,url);
    }

    //视频点赞
    @PostMapping("/video-likes")
    public JsonResponse<String> addVideoLike(@RequestParam Long videoId){
        Long userId = userSupport.getCurrentUserId();
        videoLikeService.addVideoLike(videoId,userId);
        return JsonResponse.success();
    }

    //取消点赞
    @DeleteMapping("/video-likes")
    public JsonResponse<String> deleteVideoLikes(@RequestParam Long videoId){
        Long userId = userSupport.getCurrentUserId();
        videoLikeService.deleteVideoLike(videoId,userId);
        return JsonResponse.success();
    }

    //查询视频点赞数量
    @GetMapping("video-likes")
    public JsonResponse<Map<String,Object>> getVideoLikes(@RequestParam Long videoId){
        Long userId =null;
        try {
            userId= userSupport.getCurrentUserId();
        }catch (Exception e){}
        Map<String,Object> map = videoLikeService.getVideoLike(videoId,userId);
        return new JsonResponse<>(map);
    }

    //收藏视频
    @PostMapping("/video-collections")
    public JsonResponse<String> addVideoCollection(@RequestBody VideoCollection videoCollection){
        Long userId = userSupport.getCurrentUserId();
        videoCollectionService.addVideoCollection(videoCollection,userId);
        return JsonResponse.success();
    }

    //取消收藏视频
    @DeleteMapping("/video-collections")
    public JsonResponse<String> deleteVideoCollection(@RequestParam Long videoId){
        Long userId = userSupport.getCurrentUserId();
        videoCollectionService.deleteVideoCollections(videoId,userId);
        return JsonResponse.success();
    }

    //获取当前视频收藏量
    @GetMapping("/video-collections")
    public JsonResponse<Map<String,Object>> getVideoCollections(@RequestParam Long videoId){
        Long userId =null;
        try {
            userId= userSupport.getCurrentUserId();
        }catch (Exception e){}
        Map<String,Object> map = videoCollectionService.getVideoCollections(videoId,userId);
        return new JsonResponse<>(map);
    }

    //视频投币
    @PostMapping("/video-coins")
    public JsonResponse<String> addVideoCoins(@RequestBody VideoCoin videoCoin){
        Long userId = userSupport.getCurrentUserId();
        videoCoinService.addVideoCions(videoCoin,userId);
        return JsonResponse.success();
    }

    //查询视频投币数量
    @GetMapping("/video-coins")
    public JsonResponse<Map<String,Object>> getVideoCoins(@RequestParam Long videoId){
        Long userId =null;
        try {
            userId= userSupport.getCurrentUserId();
        }catch (Exception e){}
        Map<String,Object> map = videoCoinService.getVideoCoins(videoId,userId);
        return new JsonResponse<>(map);
    }

    //添加视频评论
    @PostMapping("/video-comments")
    public JsonResponse<String> addVideoComments(@RequestBody VideoComment videoComment){
        Long userId = userSupport.getCurrentUserId();
        videoCommentService.addVideoComments(videoComment,userId);
        return JsonResponse.success();
    }

    //分页查询视频评论
    @GetMapping("/video-comments")
    public JsonResponse<PageResult<VideoComment>> pageListVideoComments(@RequestParam Long size,
                                                                        @RequestParam Integer number,
                                                                        @RequestParam Long videoId){
        PageResult<VideoComment> pageResult = videoCommentService.pageListVideoComments(size,number,videoId);
        return new JsonResponse<>(pageResult);
    }

    //获取视频详情
    @GetMapping("/video-details")
    public JsonResponse<Map<String,Object>> getVideoDetails(@RequestParam  Long videoId){
        Map<String,Object> result = videoService.getVideoDetails(videoId);
        return new JsonResponse<>(result);
    }

    //添加视频观看记录
    @PostMapping("/video-views")
    public JsonResponse<String> addVideoView(@RequestBody VideoView videoView,
                                             HttpServletRequest request){
        Long userId;
        try{
            userId = userSupport.getCurrentUserId();
            videoView.setUserId(userId);
            videoViewService.addVideoView(videoView,request);
        }catch (Exception e){
            videoViewService.addVideoView(videoView,request);
        }
        return JsonResponse.success();
    }

    //统计观看人数
    @GetMapping("/video-view-counts")
    public JsonResponse<Integer> getVideoViewCounts(@RequestParam Long videoId){
        Integer count = videoViewService.getVideoViewCounts(videoId);
        return new JsonResponse<>(count);
    }


    //个性化推荐
    @GetMapping("/recommendation")
    public JsonResponse<List<Video>> recommend() throws TasteException {
        Long userId = userSupport.getCurrentUserId();
        List<Video> list = videoService.recommend(userId);
        return new JsonResponse<>(list);
    }
}















