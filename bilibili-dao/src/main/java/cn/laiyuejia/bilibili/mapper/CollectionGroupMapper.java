package cn.laiyuejia.bilibili.mapper;


import cn.laiyuejia.bilibili.domain.CollectionGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author laiyuejia
* @description 针对表【t_collection_group(用户收藏分组表)】的数据库操作Mapper
* @createDate 2023-01-26 16:47:21
* @Entity cn.laiyuejia.bilibili.domain.CollectionGroup
*/
@Mapper
public interface CollectionGroupMapper extends BaseMapper<CollectionGroup> {

}




