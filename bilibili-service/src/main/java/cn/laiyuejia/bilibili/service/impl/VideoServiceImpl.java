package cn.laiyuejia.bilibili.service.impl;

import cn.laiyuejia.bilibili.domain.*;
import cn.laiyuejia.bilibili.domain.exception.ConditionException;
import cn.laiyuejia.bilibili.mapper.VideoMapper;
import cn.laiyuejia.bilibili.service.UserService;
import cn.laiyuejia.bilibili.service.VideoOperationService;
import cn.laiyuejia.bilibili.service.VideoService;
import cn.laiyuejia.bilibili.service.VideoTagService;
import cn.laiyuejia.bilibili.service.util.FastDFSUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* @author laiyuejia
* @description 针对表【t_video(视频投稿记录表)】的数据库操作Service实现
* @createDate 2023-01-25 22:16:01
*/
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
    implements VideoService {

    @Autowired
    private VideoTagService videoTagService;

    @Autowired
    private FastDFSUtil fastDFSUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private VideoOperationService videoOperationService;

    @Override
    @Transactional
    public void addVideos(Video video) {
        this.save(video);
        Long videoId = video.getId();
        List<VideoTag> videoTagList = video.getVideoTagList();
        if(videoTagList!=null && videoTagList.size()>0){
            videoTagList.forEach(item ->{
                item.setVideoId(videoId);
            });
            videoTagService.saveBatch(videoTagList);
        }
    }

    @Override
    public PageResult<Video> pageListVideos(Integer size, Integer page, String area) {
        if(size==null ||page ==null){
            throw new ConditionException("参数异常！");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("start",(page-1)*size);
        params.put("limit",size);
        params.put("area",area);
        List<Video> list = new ArrayList<>();
        IPage<Video> iPage = new Page<>((page-1)*size,size);
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        if(area!=null&&area!=""){
            queryWrapper.eq("area",area);
        }
        IPage<Video> page1 = this.page(iPage, queryWrapper);
        if(page1.getTotal()>0) list=page1.getRecords();
        return new PageResult<>((int) page1.getTotal(),list);
    }

    @Override
    public void viewVideoOnlineBySlices(HttpServletRequest request, HttpServletResponse response, String url) throws Exception {
        fastDFSUtil.viewVideoOnlineBySlices(request,response,url);
    }

    @Override
    public Map<String, Object> getVideoDetails(Long videoId) {
        Video video = this.getById(videoId);
        Long userId = video.getUserId();
        User user = userService.getUserInfo(userId);
        UserInfo userInfo = user.getUserInfo();
        Map<String,Object> result = new HashMap<>();
        result.put("video",video);
        result.put("userInfo",userInfo);
        return result;
    }

    @Override
    public List<Video> recommend(Long userId) throws TasteException {
        List<UserPreference> userPreferenceList =  videoOperationService.getAllUserPreference();
        //创建数据模型
        DataModel dataModel = this.createDataModel(userPreferenceList);
        //获取用户相似度
        UserSimilarity similarity = new UncenteredCosineSimilarity(dataModel);
        //获取用户邻居
        UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(2,similarity,dataModel);
        long[] ar = userNeighborhood.getUserNeighborhood(userId);
        //构建推荐器
        Recommender recommender = new GenericUserBasedRecommender(dataModel,userNeighborhood,similarity);
        //推荐商品
        List<RecommendedItem> recommendedItems = recommender.recommend(userId,5);
        List<Long> collect = recommendedItems.stream().map(RecommendedItem::getItemID).collect(Collectors.toList());
        return this.listByIds(collect);
    }

    private DataModel createDataModel(List<UserPreference> userPreferenceList) {
        FastByIDMap<PreferenceArray> fastByIdMap = new FastByIDMap<>();
        Map<Long, List<UserPreference>> map = userPreferenceList.stream().collect(Collectors.groupingBy(UserPreference::getUserId));
        Collection<List<UserPreference>> list = map.values();
        for(List<UserPreference> userPreferences : list){
            GenericPreference[] array = new GenericPreference[userPreferences.size()];
            for(int i = 0; i < userPreferences.size(); i++){
                UserPreference userPreference = userPreferences.get(i);
                GenericPreference item = new GenericPreference(userPreference.getUserId(), userPreference.getVideoId(), userPreference.getValue());
                array[i] = item;
            }
            fastByIdMap.put(array[0].getUserID(), new GenericUserPreferenceArray(Arrays.asList(array)));
        }
        return new GenericDataModel(fastByIdMap);
    }
}




