package cn.laiyuejia.bilibili.service;


import cn.laiyuejia.bilibili.domain.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
* @author laiyuejia
* @description 针对表【t_user_info(用户基本信息表)】的数据库操作Service
* @createDate 2023-01-22 21:54:15
*/
public interface UserInfoService extends IService<UserInfo> {

    List<UserInfo> getListByUserIds(Set<Long> ids);
}
