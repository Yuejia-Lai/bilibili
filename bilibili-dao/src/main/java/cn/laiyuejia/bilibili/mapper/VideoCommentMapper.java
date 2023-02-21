package cn.laiyuejia.bilibili.mapper;

import cn.laiyuejia.bilibili.domain.VideoComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author laiyuejia
* @description 针对表【t_video_comment(视频评论表)】的数据库操作Mapper
* @createDate 2023-01-27 13:55:15
* @Entity cn.laiyuejia.bilibili.domain.VideoComment
*/
@Mapper
public interface VideoCommentMapper extends BaseMapper<VideoComment> {

}




