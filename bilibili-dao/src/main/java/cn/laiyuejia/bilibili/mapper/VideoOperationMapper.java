package cn.laiyuejia.bilibili.mapper;

import cn.laiyuejia.bilibili.domain.UserPreference;
import cn.laiyuejia.bilibili.domain.VideoOperation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author laiyuejia
* @description 针对表【t_video_operation(用户操作表)】的数据库操作Mapper
* @createDate 2023-01-28 19:22:11
* @Entity cn.laiyuejia.bilibili.domain.VideoOperation
*/
@Mapper
public interface VideoOperationMapper extends BaseMapper<VideoOperation> {

    List<UserPreference> getAllUserPreference();
}




