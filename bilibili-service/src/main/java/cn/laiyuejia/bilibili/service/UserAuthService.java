package cn.laiyuejia.bilibili.service;


import cn.laiyuejia.bilibili.domain.auth.UserAuthorities;

public interface UserAuthService {
    UserAuthorities getUserAuthorities(Long userId);

    void addUserDefaultRole(Long id);
}
