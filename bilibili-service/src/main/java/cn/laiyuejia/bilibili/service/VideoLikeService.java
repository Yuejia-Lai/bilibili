package cn.laiyuejia.bilibili.service;

import cn.laiyuejia.bilibili.domain.VideoLike;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author laiyuejia
* @description 针对表【t_video_like(视频点赞表)】的数据库操作Service
* @createDate 2023-01-26 15:52:53
*/
public interface VideoLikeService extends IService<VideoLike> {

    void addVideoLike(Long videoId, Long userId);

    void deleteVideoLike(Long videoId, Long userId);

    Map<String, Object> getVideoLike(Long videoId, Long userId);
}
