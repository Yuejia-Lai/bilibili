package cn.laiyuejia.bilibili.mapper;

import cn.laiyuejia.bilibili.domain.Danmu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author laiyuejia
* @description 针对表【t_danmu(弹幕表)】的数据库操作Mapper
* @createDate 2023-01-27 19:08:49
* @Entity cn.laiyuejia.bilibili.domain.Danmu
*/
@Mapper
public interface DanmuMapper extends BaseMapper<Danmu> {

}




