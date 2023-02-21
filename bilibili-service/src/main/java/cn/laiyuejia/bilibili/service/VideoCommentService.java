package cn.laiyuejia.bilibili.service;


import cn.laiyuejia.bilibili.domain.PageResult;
import cn.laiyuejia.bilibili.domain.VideoComment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author laiyuejia
* @description 针对表【t_video_comment(视频评论表)】的数据库操作Service
* @createDate 2023-01-27 13:55:15
*/
public interface VideoCommentService extends IService<VideoComment> {

    void addVideoComments(VideoComment videoComment,Long userId);

    PageResult<VideoComment> pageListVideoComments(Long size, Integer number, Long videoId);
}
