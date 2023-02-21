package cn.laiyuejia.bilibili.mapper;

import cn.laiyuejia.bilibili.domain.RefreshToken;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author laiyuejia
* @description 针对表【t_refresh_token(刷新令牌记录表)】的数据库操作Mapper
* @createDate 2023-01-24 23:13:22
* @Entity cn.laiyuejia.bilibili.domain.RefreshToken
*/
@Mapper
public interface RefreshTokenMapper extends BaseMapper<RefreshToken> {

}




