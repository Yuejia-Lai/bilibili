package cn.laiyuejia.bilibili.service;


import cn.laiyuejia.bilibili.domain.VideoCollection;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author laiyuejia
* @description 针对表【t_video_collection(视频收藏表)】的数据库操作Service
* @createDate 2023-01-26 16:47:15
*/
public interface VideoCollectionService extends IService<VideoCollection> {

    void addVideoCollection(VideoCollection videoCollection, Long userId);

    void deleteVideoCollections(Long videoId, Long userId);

    Map<String, Object> getVideoCollections(Long videoId, Long userId);
}
