package cn.laiyuejia.bilibili.domain.auth;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 权限控制--页面访问表
 * @TableName t_auth_menu
 */
@TableName(value ="t_auth_menu")
@Data
public class AuthMenu implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 菜单项目名称
     */
    private String name;

    /**
     * 唯一编码
     */
    private String code;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}