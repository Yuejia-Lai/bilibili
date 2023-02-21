package cn.laiyuejia.bilibili.service.impl;

import cn.laiyuejia.bilibili.domain.VideoTag;
import cn.laiyuejia.bilibili.mapper.VideoTagMapper;
import cn.laiyuejia.bilibili.service.VideoTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author laiyuejia
* @description 针对表【t_video_tag(视频标签关联表)】的数据库操作Service实现
* @createDate 2023-01-25 22:16:10
*/
@Service
public class VideoTagServiceImpl extends ServiceImpl<VideoTagMapper, VideoTag>
    implements VideoTagService {

}




