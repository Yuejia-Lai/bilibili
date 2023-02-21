package cn.laiyuejia.bilibili.mapper;

import cn.laiyuejia.bilibili.domain.UserMoments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author laiyuejia
* @description 针对表【t_user_moments(用户动态表)】的数据库操作Mapper
* @createDate 2023-01-24 12:32:12
* @Entity cn.laiyuejia.bilibili.domain.UserMoments
*/
@Mapper
public interface UserMomentsMapper extends BaseMapper<UserMoments> {

}




