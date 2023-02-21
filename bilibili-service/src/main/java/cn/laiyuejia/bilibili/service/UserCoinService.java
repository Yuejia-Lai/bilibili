package cn.laiyuejia.bilibili.service;


import cn.laiyuejia.bilibili.domain.UserCoin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author laiyuejia
* @description 针对表【t_user_coin(用户硬币数量表)】的数据库操作Service
* @createDate 2023-01-26 17:57:56
*/
public interface UserCoinService extends IService<UserCoin> {

    Long getUserCoinAmount(Long userId);

    void updateUserCoinAmount(Long userId, long amount);
}
