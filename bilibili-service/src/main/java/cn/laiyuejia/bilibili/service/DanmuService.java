package cn.laiyuejia.bilibili.service;

import cn.laiyuejia.bilibili.domain.Danmu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
* @author laiyuejia
* @description 针对表【t_danmu(弹幕表)】的数据库操作Service
* @createDate 2023-01-27 19:08:49
*/
public interface DanmuService extends IService<Danmu> {


    List<Danmu> getDanmus(Map<String,Object> map) throws ParseException;

    void addDanmuToRedis(Danmu danmu);

    void asyncAddDanmu(Danmu danmu);
}
