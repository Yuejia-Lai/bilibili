package cn.laiyuejia.bilibili.mapper;

import cn.laiyuejia.bilibili.domain.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author laiyuejia
* @description 针对表【t_video(视频投稿记录表)】的数据库操作Mapper
* @createDate 2023-01-25 22:16:01
* @Entity cn.laiyuejia.bilibili.domain.Video
*/
@Mapper
public interface VideoMapper extends BaseMapper<Video> {

}




