package cn.laiyuejia.bilibili.service;

import cn.laiyuejia.bilibili.domain.VideoCoin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author laiyuejia
* @description 针对表【t_video_coin(视频投币记录表)】的数据库操作Service
* @createDate 2023-01-26 17:57:49
*/
public interface VideoCoinService extends IService<VideoCoin> {

    void addVideoCions(VideoCoin videoCoin, Long userId);

    VideoCoin getCoinByVideoIdAndUserId(Long videoId, Long userId);

    void updateVideoCion(VideoCoin videoCoin);

    Map<String, Object> getVideoCoins(Long videoId, Long userId);
}
