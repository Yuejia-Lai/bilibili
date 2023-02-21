package cn.laiyuejia.bilibili.service.impl;

import cn.laiyuejia.bilibili.domain.UserMoments;
import cn.laiyuejia.bilibili.domain.constant.UserMomentsConstant;
import cn.laiyuejia.bilibili.mapper.UserMomentsMapper;
import cn.laiyuejia.bilibili.service.UserMomentsService;
import cn.laiyuejia.bilibili.service.util.RocketMQUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author laiyuejia
* @description 针对表【t_user_moments(用户动态表)】的数据库操作Service实现
* @createDate 2023-01-24 12:32:12
*/
@Service
public class UserMomentsServiceImpl extends ServiceImpl<UserMomentsMapper, UserMoments>
    implements UserMomentsService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public void addUserMoments(UserMoments userMoments) throws Exception {
        this.save(userMoments);
        userMoments = this.getById(userMoments.getId());
        DefaultMQProducer defaultMQProducer = (DefaultMQProducer) applicationContext.getBean("momentsProducer");
        Message message = new Message(UserMomentsConstant.TOPIC_MOMENTS, JSONObject.toJSONString(userMoments).getBytes());
        RocketMQUtil.syncSendMsg(defaultMQProducer,message);
    }

    @Override
    public List<UserMoments> getUserSubscribedMoments(Long userId) {
        String key = "subscribed-" + userId;
        String listStr = redisTemplate.opsForValue().get(key);
        System.out.println(listStr);
        return JSONArray.parseArray(listStr, UserMoments.class);
    }
}




