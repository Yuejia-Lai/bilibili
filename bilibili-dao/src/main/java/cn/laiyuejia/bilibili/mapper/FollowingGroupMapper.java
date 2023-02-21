package cn.laiyuejia.bilibili.mapper;

import cn.laiyuejia.bilibili.domain.FollowingGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author laiyuejia
* @description 针对表【t_following_group(用户关注分组表)】的数据库操作Mapper
* @createDate 2023-01-23 14:03:22
* @Entity cn.laiyuejia.bilibili.domain.FollowingGroup
*/
@Mapper
public interface FollowingGroupMapper extends BaseMapper<FollowingGroup> {

}




