package cn.laiyuejia.bilibili.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 用户基本信息表
 * @TableName t_user_info
 */
@TableName(value ="t_user_info")
@Data
@Document(indexName = "userinfos")
public class UserInfo implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    @Id
    private Long id;

    /**
     * 用户id（关联）
     */
    private Long userId;

    /**
     * 昵称
     */
    @Field(type = FieldType.Text)
    private String nick;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 签名
     */
    private String sign;

    /**
     * 性别：0男，1女，2未知
     */
    private String gender;

    /**
     * 生日
     */
    private String birth;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Date)
    private Date createTime;

    /**
     * 修改时间
     */
    @Field(type = FieldType.Date)
    private Date updateTime;

    @TableField(exist = false)
    private Boolean followed;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}