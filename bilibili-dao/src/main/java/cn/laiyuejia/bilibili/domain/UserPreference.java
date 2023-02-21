package cn.laiyuejia.bilibili.domain;

import lombok.Data;

import java.util.Date;

/**
 * 个性化推荐工具实体类
 */
@Data
public class UserPreference {

    private Long id;
    private Long userId;
    private Long videoId;
    private Float value;
    private Date createTime;
}
