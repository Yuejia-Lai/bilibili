package cn.laiyuejia.bilibili.repository;


import cn.laiyuejia.bilibili.domain.Video;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends ElasticsearchRepository<Video,Long> {
    Video findByTitleLike(String keyword);
}
