package cn.laiyuejia.bilibili.service.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JsonHttpMessageConverterConfig {

    @Bean
//    顺序在所有转换器之前
    @Primary
    public HttpMessageConverters fastJsonHttpMessageConverters(){
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        设置时间格式
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        序列化相关配置
        fastJsonConfig.setSerializerFeatures(
//                好看的格式
                SerializerFeature.PrettyFormat,
//                返回对象中的空保留
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteMapNullValue,
//                键值对排序
                SerializerFeature.MapSortField,
//                禁止循环引用
                SerializerFeature.DisableCircularReferenceDetect
        );

        fastConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastConverter);
    }
}
