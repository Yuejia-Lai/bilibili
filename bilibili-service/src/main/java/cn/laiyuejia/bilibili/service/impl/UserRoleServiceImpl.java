package cn.laiyuejia.bilibili.service.impl;

import cn.laiyuejia.bilibili.domain.auth.UserRole;
import cn.laiyuejia.bilibili.mapper.UserRoleMapper;
import cn.laiyuejia.bilibili.service.UserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author laiyuejia
* @description 针对表【t_user_role(用户角色关联表)】的数据库操作Service实现
* @createDate 2023-01-24 17:07:16
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> getUserRoleByUserId(Long userId) {
        return userRoleMapper.getUserRoleByUserId(userId);
    }
}




