package cn.laiyuejia.bilibili.service;

import cn.laiyuejia.bilibili.domain.FollowingGroup;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author laiyuejia
* @description 针对表【t_following_group(用户关注分组表)】的数据库操作Service
* @createDate 2023-01-23 14:03:22
*/
public interface FollowingGroupService extends IService<FollowingGroup> {


    //根据type获得关注信息
    FollowingGroup getByType(String type);

    //根据id获取关注信息
    FollowingGroup getById(String id);

    List<FollowingGroup> getByUserId(Long userId);
}
