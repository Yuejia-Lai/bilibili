package cn.laiyuejia.bilibili.service.impl;

import cn.laiyuejia.bilibili.domain.RefreshToken;
import cn.laiyuejia.bilibili.mapper.RefreshTokenMapper;
import cn.laiyuejia.bilibili.service.RefreshTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author laiyuejia
* @description 针对表【t_refresh_token(刷新令牌记录表)】的数据库操作Service实现
* @createDate 2023-01-24 23:13:22
*/
@Service
public class RefreshTokenServiceImpl extends ServiceImpl<RefreshTokenMapper, RefreshToken>
    implements RefreshTokenService {

}




