package cn.laiyuejia.bilibili.service.impl;

import cn.laiyuejia.bilibili.domain.Tag;
import cn.laiyuejia.bilibili.mapper.TagMapper;
import cn.laiyuejia.bilibili.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author laiyuejia
* @description 针对表【t_tag(标签表)】的数据库操作Service实现
* @createDate 2023-01-25 22:15:54
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService {

}




