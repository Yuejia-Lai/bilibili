package cn.laiyuejia.bilibili.service;

import cn.laiyuejia.bilibili.domain.UserPreference;
import cn.laiyuejia.bilibili.domain.VideoOperation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author laiyuejia
* @description 针对表【t_video_operation(用户操作表)】的数据库操作Service
* @createDate 2023-01-28 19:22:11
*/
public interface VideoOperationService extends IService<VideoOperation> {

    List<UserPreference> getAllUserPreference();

}
