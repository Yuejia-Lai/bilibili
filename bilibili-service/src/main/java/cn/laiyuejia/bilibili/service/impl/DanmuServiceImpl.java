package cn.laiyuejia.bilibili.service.impl;

import cn.laiyuejia.bilibili.domain.Danmu;
import cn.laiyuejia.bilibili.domain.exception.ConditionException;
import cn.laiyuejia.bilibili.mapper.DanmuMapper;
import cn.laiyuejia.bilibili.service.DanmuService;
import cn.laiyuejia.bilibili.service.VideoService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
* @author laiyuejia
* @description 针对表【t_danmu(弹幕表)】的数据库操作Service实现
* @createDate 2023-01-27 19:08:49
*/
@Service
public class DanmuServiceImpl extends ServiceImpl<DanmuMapper, Danmu>
    implements DanmuService {

    public static final String DANMU_KEY = "dm-video-";
    @Autowired
    private VideoService videoService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public List<Danmu> getDanmus(Map<String,Object> map) throws ParseException {
        String videoId = (String) map.get("videoId");
        String startDate = (String) map.get("startDate");
        String endDate = (String) map.get("endDate");
        if(StringUtils.isBlank(videoId)){
            throw new ConditionException("参数异常！");
        }
        if(videoService.getById(videoId)==null){
            throw new ConditionException("非法视频！");
        }
        String key = DANMU_KEY + videoId;
        String value = redisTemplate.opsForValue().get(key);
        List<Danmu> list;
        if(StringUtils.isNotBlank(value)){
            list = JSONArray.parseArray(value, Danmu.class);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<Danmu> childList = new ArrayList<>();
            for(Danmu danmu : list){
                if(this.inTime(startDate,endDate,danmu.getCreateTime(),sdf)){
                    childList.add(danmu);
                }
            }
            list = childList;
        }else{
            QueryWrapper<Danmu> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("video_id",videoId);
            if(StringUtils.isNotBlank(startDate)){
                queryWrapper.ge("create_time",startDate);
            }
            if(StringUtils.isNotBlank(endDate)){
                queryWrapper.le("create_time",endDate);
            }
            list = this.list(queryWrapper);
            redisTemplate.opsForValue().set(key,JSONObject.toJSONString(list));
        }
        return list;
    }

    @Override
    public void addDanmuToRedis(Danmu danmu) {
        String key = "danmu-video-" + danmu.getVideoId();
        String value = redisTemplate.opsForValue().get(key);
        List<Danmu> list = new ArrayList<>();
        if(!StringUtils.isNotBlank(value)){
            list = JSONArray.parseArray(value, Danmu.class);
        }
        list.add(danmu);
        redisTemplate.opsForValue().set(key, JSONObject.toJSONString(list));
    }


    @Override
    @Async
    public void asyncAddDanmu(Danmu danmu) {
        this.save(danmu);
    }

    public boolean inTime(String startTime, String endTime, Date createTime, SimpleDateFormat sdf) throws ParseException {
        if(StringUtils.isNotBlank(startTime) && sdf.parse(startTime).after(createTime)){
            return false;
        }
        if(StringUtils.isNotBlank(endTime) && sdf.parse(endTime).before(createTime)){
            return false;
        }
        return true;
    }
}




