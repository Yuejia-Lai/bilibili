package cn.laiyuejia.bilibili.service.impl;

import cn.laiyuejia.bilibili.domain.FollowingGroup;
import cn.laiyuejia.bilibili.domain.constant.UserConstant;
import cn.laiyuejia.bilibili.mapper.FollowingGroupMapper;
import cn.laiyuejia.bilibili.service.FollowingGroupService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author laiyuejia
* @description 针对表【t_following_group(用户关注分组表)】的数据库操作Service实现
* @createDate 2023-01-23 14:03:22
*/
@Service
public class FollowingGroupServiceImpl extends ServiceImpl<FollowingGroupMapper, FollowingGroup>
    implements FollowingGroupService {


    @Override
    //根据type获得关注信息
    public FollowingGroup getByType(String type){
        QueryWrapper<FollowingGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",type);
        FollowingGroup followingGroup = this.getOne(queryWrapper);
        return followingGroup;
    }

    //根据id获取关注信息
    @Override
    public FollowingGroup getById(String id){
        FollowingGroup followingGroup = this.getById(id);
        return followingGroup;
    }

    @Override
    public List<FollowingGroup> getByUserId(Long userId) {
        QueryWrapper<FollowingGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.or().in("type","0","1","2");
        List<FollowingGroup> list = this.list(queryWrapper);
        return list;
    }
}




