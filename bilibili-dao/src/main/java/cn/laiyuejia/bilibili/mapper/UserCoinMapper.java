package cn.laiyuejia.bilibili.mapper;

import cn.laiyuejia.bilibili.domain.UserCoin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author laiyuejia
* @description 针对表【t_user_coin(用户硬币数量表)】的数据库操作Mapper
* @createDate 2023-01-26 17:57:56
* @Entity cn.laiyuejia.bilibili.domain.UserCoin
*/
@Mapper
public interface UserCoinMapper extends BaseMapper<UserCoin> {

}




