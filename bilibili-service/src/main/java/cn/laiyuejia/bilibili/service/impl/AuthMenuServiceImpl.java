package cn.laiyuejia.bilibili.service.impl;

import cn.laiyuejia.bilibili.domain.auth.AuthMenu;
import cn.laiyuejia.bilibili.mapper.AuthMenuMapper;
import cn.laiyuejia.bilibili.service.AuthMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
* @author laiyuejia
* @description 针对表【t_auth_menu(权限控制--页面访问表)】的数据库操作Service实现
* @createDate 2023-01-24 17:07:41
*/
@Service
public class AuthMenuServiceImpl extends ServiceImpl<AuthMenuMapper, AuthMenu>
    implements AuthMenuService {

}




