package cn.laiyuejia.bilibili.repository;

import cn.laiyuejia.bilibili.domain.UserInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends ElasticsearchRepository<UserInfo,Long> {

}
