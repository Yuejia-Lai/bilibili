package cn.laiyuejia.bilibili.service.impl;

import cn.laiyuejia.bilibili.domain.UserInfo;
import cn.laiyuejia.bilibili.mapper.UserInfoMapper;
import cn.laiyuejia.bilibili.service.UserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
* @author laiyuejia
* @description 针对表【t_user_info(用户基本信息表)】的数据库操作Service实现
* @createDate 2023-01-22 21:54:15
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService {

    @Override
    public List<UserInfo> getListByUserIds(Set<Long> ids){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_id",ids);
        List<UserInfo> list = this.list(queryWrapper);
        return list;
    }
}




