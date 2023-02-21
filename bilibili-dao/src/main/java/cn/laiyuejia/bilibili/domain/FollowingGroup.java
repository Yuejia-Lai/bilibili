package cn.laiyuejia.bilibili.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 用户关注分组表
 * @TableName t_following_group
 */
@TableName(value ="t_following_group")
@Data
public class FollowingGroup implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 关注分组名称
     */
    private String name;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 关注分组类型：0特别关注，1悄悄关注，2默认关注，3用户自定义关注
     */
    private String type;

    @TableField(exist = false)
    private List<UserInfo> followingUserInfoList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}