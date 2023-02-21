package cn.laiyuejia.bilibili.service;


import cn.laiyuejia.bilibili.domain.UserMoments;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author laiyuejia
* @description 针对表【t_user_moments(用户动态表)】的数据库操作Service
* @createDate 2023-01-24 12:32:12
*/
public interface UserMomentsService extends IService<UserMoments> {

    void addUserMoments(UserMoments userMoments) throws Exception;

    List<UserMoments> getUserSubscribedMoments(Long userId);
}
