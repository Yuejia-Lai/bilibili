package cn.laiyuejia.bilibili.domain.auth;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 权限控制--页面元素操作表
 * @TableName t_auth_element_operation
 */
@TableName(value ="t_auth_element_operation")
@Data
public class AuthElementOperation implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 页面元素名称
     */
    private String elementName;

    /**
     * 页面元素唯一编码
     */
    private String elementCode;

    /**
     * 操作类型：0可点击，1可见
     */
    private String operationType;

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