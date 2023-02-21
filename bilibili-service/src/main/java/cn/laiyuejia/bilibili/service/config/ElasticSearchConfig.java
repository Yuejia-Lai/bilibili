package cn.laiyuejia.bilibili.service.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

//    @Value("${spring.elasticsearch.uris}")
//    private String esUrl;

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient(){
//        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo(esUrl)
//                .build();
//        return RestClients.create(clientConfiguration).rest();
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(RestClient.builder(
                new HttpHost("49.235.137.33", 9200, "http")));
        return restHighLevelClient;
    }
}
