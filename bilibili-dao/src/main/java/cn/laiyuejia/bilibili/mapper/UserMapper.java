package cn.laiyuejia.bilibili.mapper;


import cn.laiyuejia.bilibili.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author laiyuejia
* @description 针对表【t_user(用户表)】的数据库操作Mapper
* @createDate 2023-01-22 21:44:21
* @Entity cn.laiyuejia.bilibili.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




