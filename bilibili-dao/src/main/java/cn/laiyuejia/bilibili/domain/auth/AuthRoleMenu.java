package cn.laiyuejia.bilibili.domain.auth;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 权限控制--角色页面菜单关联表
 * @TableName t_auth_role_menu
 */
@TableName(value ="t_auth_role_menu")
@Data
public class AuthRoleMenu implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 页面菜单id
     */
    private Long menuId;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private AuthMenu authMenu;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}