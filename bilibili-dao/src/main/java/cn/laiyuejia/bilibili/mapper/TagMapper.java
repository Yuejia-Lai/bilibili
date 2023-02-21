package cn.laiyuejia.bilibili.mapper;


import cn.laiyuejia.bilibili.domain.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author laiyuejia
* @description 针对表【t_tag(标签表)】的数据库操作Mapper
* @createDate 2023-01-25 22:15:54
* @Entity cn.laiyuejia.bilibili.domain.Tag
*/
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}




