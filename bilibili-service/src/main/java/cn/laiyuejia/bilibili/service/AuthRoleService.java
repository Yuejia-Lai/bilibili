package cn.laiyuejia.bilibili.service;


import cn.laiyuejia.bilibili.domain.auth.AuthRole;
import cn.laiyuejia.bilibili.domain.auth.AuthRoleElementOperation;
import cn.laiyuejia.bilibili.domain.auth.AuthRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
* @author laiyuejia
* @description 针对表【t_auth_role(权限控制--角色表)】的数据库操作Service
* @createDate 2023-01-24 17:07:36
*/
public interface AuthRoleService extends IService<AuthRole> {

    List<AuthRoleElementOperation> getRoleElementOperationsByRoleIds(Set<Long> roleIdSet);

    List<AuthRoleMenu> getRoleMenusByRoleIds(Set<Long> roleIdSet);

    AuthRole getRoleByCode(String code);
}
