package cn.laiyuejia.bilibili.mapper;

import cn.laiyuejia.bilibili.domain.auth.AuthMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author laiyuejia
* @description 针对表【t_auth_menu(权限控制--页面访问表)】的数据库操作Mapper
* @createDate 2023-01-24 17:07:41
* @Entity cn.laiyuejia.bilibili.domain.auth.AuthMenu
*/
@Mapper
public interface AuthMenuMapper extends BaseMapper<AuthMenu> {

}




