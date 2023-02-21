package cn.laiyuejia.bilibili.domain.auth;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 权限控制--角色与元素操作关联表
 * @TableName t_auth_role_element_operation
 */
@TableName(value ="t_auth_role_element_operation")
@Data
public class AuthRoleElementOperation implements Serializable {
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
     * 元素操作id
     */
    private Long elementOperationId;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private AuthElementOperation authElementOperation;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}