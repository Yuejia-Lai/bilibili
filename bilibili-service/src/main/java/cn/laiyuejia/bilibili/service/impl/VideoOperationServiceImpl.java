package cn.laiyuejia.bilibili.service.impl;

import cn.laiyuejia.bilibili.domain.UserPreference;
import cn.laiyuejia.bilibili.domain.VideoOperation;
import cn.laiyuejia.bilibili.mapper.VideoOperationMapper;
import cn.laiyuejia.bilibili.service.VideoOperationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author laiyuejia
* @description 针对表【t_video_operation(用户操作表)】的数据库操作Service实现
* @createDate 2023-01-28 19:22:11
*/
@Service
public class VideoOperationServiceImpl extends ServiceImpl<VideoOperationMapper, VideoOperation>
    implements VideoOperationService {

    @Autowired
    private VideoOperationMapper videoOperationMapper;

    @Override
    public List<UserPreference> getAllUserPreference() {
        return  videoOperationMapper.getAllUserPreference();
    }
}




