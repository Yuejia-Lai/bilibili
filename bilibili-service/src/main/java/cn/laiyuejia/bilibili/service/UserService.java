package cn.laiyuejia.bilibili.service;


import cn.laiyuejia.bilibili.domain.PageResult;
import cn.laiyuejia.bilibili.domain.User;
import cn.laiyuejia.bilibili.domain.UserInfo;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author laiyuejia
* @description 针对表【t_user(用户表)】的数据库操作Service
* @createDate 2023-01-22 21:44:21
*/
public interface UserService extends IService<User> {

    void addUser(User user);

    String login(User user) throws Exception;

    User getUserInfo(Long userId);

    void updateUserInfos(UserInfo userInfo);

    PageResult<UserInfo> pageListUserInfos(JSONObject params);

    Map<String, Object> loginForDts(User user) throws Exception;

    void logout(String refreshToken, Long userId);

    String refreshAccessToken(String refreshToken) throws Exception;
}
