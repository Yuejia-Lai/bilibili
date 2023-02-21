package cn.laiyuejia.bilibili.service.impl;

import cn.laiyuejia.bilibili.domain.auth.AuthRoleElementOperation;
import cn.laiyuejia.bilibili.mapper.AuthRoleElementOperationMapper;
import cn.laiyuejia.bilibili.service.AuthRoleElementOperationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
* @author laiyuejia
* @description 针对表【t_auth_role_element_operation(权限控制--角色与元素操作关联表)】的数据库操作Service实现
* @createDate 2023-01-24 17:07:31
*/
@Service
public class AuthRoleElementOperationServiceImpl extends ServiceImpl<AuthRoleElementOperationMapper, AuthRoleElementOperation>
    implements AuthRoleElementOperationService {

    @Autowired
    private AuthRoleElementOperationMapper authRoleElementOperationMapper;
    @Override
    public List<AuthRoleElementOperation> getRoleElementOperationsByRoleIds(Set<Long> roleIdSet) {
        return  authRoleElementOperationMapper.getRoleElementOperationsByRoleIds(roleIdSet);
    }
}




