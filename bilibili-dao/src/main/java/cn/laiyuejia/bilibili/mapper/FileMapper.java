package cn.laiyuejia.bilibili.mapper;

import cn.laiyuejia.bilibili.domain.File;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author laiyuejia
* @description 针对表【t_file(上传文件信息表)】的数据库操作Mapper
* @createDate 2023-01-25 20:27:18
* @Entity cn.laiyuejia.bilibili.domain.File
*/
@Mapper
public interface FileMapper extends BaseMapper<File> {

}




