package cn.laiyuejia.bilibili.service.impl;

import cn.laiyuejia.bilibili.domain.VideoView;
import cn.laiyuejia.bilibili.mapper.VideoViewMapper;
import cn.laiyuejia.bilibili.service.VideoViewService;
import cn.laiyuejia.bilibili.service.util.IpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* @author laiyuejia
* @description 针对表【t_video_view(视频观看记录表)】的数据库操作Service实现
* @createDate 2023-01-28 15:39:13
*/
@Service
public class VideoViewServiceImpl extends ServiceImpl<VideoViewMapper, VideoView>
    implements VideoViewService {

    @Autowired
    private VideoViewMapper videoViewMapper;

    @Override
    public void addVideoView(VideoView videoView, HttpServletRequest request) {
        Long videoId = videoView.getVideoId();
        Long userId = videoView.getUserId();
        //生成clientId
        String agent = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);
        int agentId = userAgent.getId();
        String ip = IpUtil.getIP(request);
        Map<String,Object> params = new HashMap<>();
        if(userId !=null){
            params.put("userId",userId);
        }else{
            params.put("ip",ip);
            params.put("clientId",agentId);
        }
        params.put("videoId",videoId);
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        params.put("today",sdf.format(now));
        //添加观看记录
        VideoView videoView1 = videoViewMapper.getVideoView(params);
        if(videoView1 ==null){
            videoView1.setIp(ip);
            videoView1.setClientId(String.valueOf(agentId));
            this.save(videoView1);
        }
    }

    @Override
    public Integer getVideoViewCounts(Long videoId) {
        QueryWrapper<VideoView> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("video_id",videoId);
        int count = this.count(queryWrapper);
        return count;
    }

}




