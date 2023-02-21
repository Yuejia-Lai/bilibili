package cn.laiyuejia.bilibili.mapper;

import cn.laiyuejia.bilibili.domain.VideoCollection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author laiyuejia
* @description 针对表【t_video_collection(视频收藏表)】的数据库操作Mapper
* @createDate 2023-01-26 16:47:15
* @Entity cn.laiyuejia.bilibili.domain.VideoCollection
*/
@Mapper
public interface VideoCollectionMapper extends BaseMapper<VideoCollection> {

}




