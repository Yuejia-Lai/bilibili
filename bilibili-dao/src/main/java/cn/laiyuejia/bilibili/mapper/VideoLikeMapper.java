package cn.laiyuejia.bilibili.mapper;

import cn.laiyuejia.bilibili.domain.VideoLike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author laiyuejia
* @description 针对表【t_video_like(视频点赞表)】的数据库操作Mapper
* @createDate 2023-01-26 15:52:53
* @Entity cn.laiyuejia.bilibili.domain.VideoLike
*/
@Mapper
public interface VideoLikeMapper extends BaseMapper<VideoLike> {

}




