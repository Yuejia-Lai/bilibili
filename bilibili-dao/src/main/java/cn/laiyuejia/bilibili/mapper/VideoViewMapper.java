package cn.laiyuejia.bilibili.mapper;

import cn.laiyuejia.bilibili.domain.VideoView;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
* @author laiyuejia
* @description 针对表【t_video_view(视频观看记录表)】的数据库操作Mapper
* @createDate 2023-01-28 15:39:13
* @Entity cn.laiyuejia.bilibili.domain.VideoView
*/
@Mapper
public interface VideoViewMapper extends BaseMapper<VideoView> {

    VideoView getVideoView(Map<String, Object> params);
}




