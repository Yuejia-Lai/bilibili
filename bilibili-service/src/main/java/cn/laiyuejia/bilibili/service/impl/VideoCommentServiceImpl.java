package cn.laiyuejia.bilibili.service.impl;

import cn.laiyuejia.bilibili.domain.PageResult;
import cn.laiyuejia.bilibili.domain.UserInfo;
import cn.laiyuejia.bilibili.domain.Video;
import cn.laiyuejia.bilibili.domain.VideoComment;
import cn.laiyuejia.bilibili.domain.exception.ConditionException;
import cn.laiyuejia.bilibili.mapper.VideoCommentMapper;
import cn.laiyuejia.bilibili.service.UserInfoService;
import cn.laiyuejia.bilibili.service.VideoCommentService;
import cn.laiyuejia.bilibili.service.VideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @author laiyuejia
* @description 针对表【t_video_comment(视频评论表)】的数据库操作Service实现
* @createDate 2023-01-27 13:55:15
*/
@Service
public class VideoCommentServiceImpl extends ServiceImpl<VideoCommentMapper, VideoComment>
    implements VideoCommentService {

    @Autowired
    private VideoService videoService;

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public void addVideoComments(VideoComment videoComment,Long userId) {
        Long videoId = videoComment.getVideoId();
        if(videoId==null){
            throw new ConditionException("参数异常！");
        }
        Video video = videoService.getById(videoId);
        if(video==null){
            throw new ConditionException("非法视频！");
        }
        videoComment.setUserId(userId);
        this.save(videoComment);
    }

    @Override
    public PageResult<VideoComment> pageListVideoComments(Long size, Integer number, Long videoId) {
        Video video = videoService.getById(videoId);
        if(video==null){
            throw new ConditionException("非法视频！");
        }
        //分页查询
        IPage<VideoComment> iPage = new Page<>((number-1)*size,size);
        QueryWrapper<VideoComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("video_id",videoId);
        queryWrapper.isNull("root_id");
        queryWrapper.orderByDesc("id");
        IPage<VideoComment> pageResult = this.page(iPage, queryWrapper);
        long total = pageResult.getTotal();
        List<VideoComment> list = new ArrayList<>();
        if(total>0){
            list = pageResult.getRecords();
            //查询二级评论
            List<Long> parentIdList = list.stream().map(VideoComment::getId).collect(Collectors.toList());
            QueryWrapper<VideoComment> childQueryWrapper = new QueryWrapper<>();
            childQueryWrapper.in("root_id",parentIdList);
            childQueryWrapper.orderByDesc("id");
            List<VideoComment> childCommentList = this.list(childQueryWrapper);
            //查询用户信息
            Set<Long> userIdSet = list.stream().map(VideoComment::getUserId).collect(Collectors.toSet());
            Set<Long> replyUserIdSet = childCommentList.stream().map(VideoComment::getReplyUserId).collect(Collectors.toSet());
            userIdSet.addAll(replyUserIdSet);
            List<UserInfo> userInfoList = userInfoService.getListByUserIds(userIdSet);
            Map<Long,UserInfo> map = userInfoList.stream().collect(Collectors.toMap(UserInfo::getUserId,userInfo -> userInfo));
            list.forEach(comments -> {
                Long id = comments.getId();
                List<VideoComment> childList = new ArrayList<>();
                childCommentList.forEach(child ->{
                    if(id.equals(child.getRootId())){
                        child.setUserInfo(map.get(child.getUserId()));
                        child.setReplyUserInfo(map.get(child.getReplyUserId()));
                        childList.add(child);
                    }
                });
                comments.setChildList(childList);
                comments.setUserInfo(map.get(comments.getUserId()));
            });
        }
        return new PageResult<>((int) total,list);
    }
}




