package cn.laiyuejia.bilibili.mapper;


import cn.laiyuejia.bilibili.domain.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author laiyuejia
* @description 针对表【t_user_info(用户基本信息表)】的数据库操作Mapper
* @createDate 2023-01-22 21:54:15
* @Entity generator.domain.UserInfo
*/
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}




