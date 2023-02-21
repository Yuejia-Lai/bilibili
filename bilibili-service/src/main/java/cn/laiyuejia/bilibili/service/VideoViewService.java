package cn.laiyuejia.bilibili.service;


import cn.laiyuejia.bilibili.domain.VideoView;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author laiyuejia
* @description 针对表【t_video_view(视频观看记录表)】的数据库操作Service
* @createDate 2023-01-28 15:39:13
*/
public interface VideoViewService extends IService<VideoView> {

    void addVideoView(VideoView videoView, HttpServletRequest request);

    Integer getVideoViewCounts(Long videoId);
}
