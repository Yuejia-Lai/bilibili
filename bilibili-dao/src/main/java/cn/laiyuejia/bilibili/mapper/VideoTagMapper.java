package cn.laiyuejia.bilibili.mapper;

import cn.laiyuejia.bilibili.domain.VideoTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author laiyuejia
* @description 针对表【t_video_tag(视频标签关联表)】的数据库操作Mapper
* @createDate 2023-01-25 22:16:10
* @Entity cn.laiyuejia.bilibili.domain.VideoTag
*/
@Mapper
public interface VideoTagMapper extends BaseMapper<VideoTag> {

}




