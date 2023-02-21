package cn.laiyuejia.bilibili.mapper;

import cn.laiyuejia.bilibili.domain.auth.AuthRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author laiyuejia
* @description 针对表【t_auth_role(权限控制--角色表)】的数据库操作Mapper
* @createDate 2023-01-24 17:07:36
* @Entity cn.laiyuejia.bilibili.domain.auth.AuthRole
*/
@Mapper
public interface AuthRoleMapper extends BaseMapper<AuthRole> {

}




