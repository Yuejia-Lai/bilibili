package cn.laiyuejia.bilibili.service;

import cn.laiyuejia.bilibili.domain.auth.AuthRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
* @author laiyuejia
* @description 针对表【t_auth_role_menu(权限控制--角色页面菜单关联表)】的数据库操作Service
* @createDate 2023-01-24 17:07:25
*/
public interface AuthRoleMenuService extends IService<AuthRoleMenu> {

    List<AuthRoleMenu> getRoleMenusByRoleIds(Set<Long> roleIdSet);
}
