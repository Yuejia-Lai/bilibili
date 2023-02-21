package cn.laiyuejia.bilibili.mapper;

import cn.laiyuejia.bilibili.domain.VideoCoin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author laiyuejia
* @description 针对表【t_video_coin(视频投币记录表)】的数据库操作Mapper
* @createDate 2023-01-26 17:57:49
* @Entity cn.laiyuejia.bilibili.domain.VideoCoin
*/
@Mapper
public interface VideoCoinMapper extends BaseMapper<VideoCoin> {

    Long getVideoCoins(@Param("videoId") Long videoId);
}




