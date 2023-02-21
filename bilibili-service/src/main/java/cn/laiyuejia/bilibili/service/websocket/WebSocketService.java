package cn.laiyuejia.bilibili.service.websocket;

import cn.laiyuejia.bilibili.domain.Danmu;
import cn.laiyuejia.bilibili.domain.constant.UserMomentsConstant;
import cn.laiyuejia.bilibili.service.DanmuService;
import cn.laiyuejia.bilibili.service.util.RocketMQUtil;
import cn.laiyuejia.bilibili.service.util.TokenUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@ServerEndpoint("/imserver/{token}")
public class WebSocketService {

    //解决单例模式
    private static ApplicationContext APPLICATION_CONTEXT;

    public static void setApplicationContext(ApplicationContext applicationContext){
        WebSocketService.APPLICATION_CONTEXT = applicationContext;
    }

    //日志记录
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);

    public static final ConcurrentHashMap<String,WebSocketService> WEBSOCKET_MAP = new ConcurrentHashMap<>();

    private Long userId;

    private Session session;

    private String sessionId;

    @OnOpen
    public void openConnection(Session session, @PathParam("token") String token){
        try {
            this.userId = TokenUtil.verifyToken(token);
        }catch (Exception e){}
        this.sessionId = session.getId();
        this.session = session;
        if(WEBSOCKET_MAP.containsKey(sessionId)){
            WEBSOCKET_MAP.remove(sessionId);
            WEBSOCKET_MAP.put(sessionId,this);
        }else{
            WEBSOCKET_MAP.put(sessionId,this);
            ONLINE_COUNT.getAndIncrement();
        }
        logger.info("用户连接成功：" + sessionId + ",当前在线人数：" + ONLINE_COUNT.get());
        try{
            this.sendMessage("0");
        }catch (Exception e){
            logger.error("连接异常！");
        }
    }

    @OnClose
    public void closeConnection(){
        if(WEBSOCKET_MAP.containsKey(sessionId)){
            WEBSOCKET_MAP.remove(sessionId);
            ONLINE_COUNT.getAndDecrement();
        }
        logger.info("用户退出:"+ sessionId+",当前在线人数为："+ ONLINE_COUNT.get());
    }

    public Session getSession() {
        return session;
    }

    public String getSessionId() {
        return sessionId;
    }

    @OnMessage
    public void onMessage(String message){
        logger.info("用户信息："+sessionId+",报文："+ message);
        if(StringUtils.isNotBlank(message)){
            try{
                //群发消息
                for(Map.Entry<String,WebSocketService> entry : WEBSOCKET_MAP.entrySet()){
                    WebSocketService webSocketService = entry.getValue();
                    DefaultMQProducer danmusProducer = (DefaultMQProducer) APPLICATION_CONTEXT.getBean("danmusProducer");
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("message",message);
                    jsonObject.put("sessionId",webSocketService.getSessionId());
                    Message message1 = new Message(UserMomentsConstant.TOPIC_DANMUS,jsonObject.toJSONString().getBytes("UTF-8"));
                    RocketMQUtil.asyncSendMsg(danmusProducer,message1);
                }
                if(this.userId !=null){
                    //弹幕保存持久化
                    Danmu danmu = JSONObject.parseObject(message, Danmu.class);
                    danmu.setUserId(userId);
                    DanmuService danmuService = (DanmuService) APPLICATION_CONTEXT.getBean("danmuService");
                    // TODO 通过消息队列对持久化进行削峰
                    danmuService.asyncAddDanmu(danmu);
                    //保存到redis
                    danmuService.addDanmuToRedis(danmu);
                }
            }catch (Exception e){
                logger.error("弹幕接受出现问题！");
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Throwable e){

    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    //统计在线人数 5s
    @Scheduled(fixedRate = 5000)
    private void noticeOnlineCount() throws IOException {
        for(Map.Entry<String,WebSocketService> entry : WebSocketService.WEBSOCKET_MAP.entrySet()){
            WebSocketService webSocketService = entry.getValue();
            if(webSocketService.getSession().isOpen()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("onlineCount",ONLINE_COUNT.get());
                jsonObject.put("message","当前在线人数为："+ONLINE_COUNT.get());
                webSocketService.sendMessage(jsonObject.toJSONString());
            }
        }
    }
}
