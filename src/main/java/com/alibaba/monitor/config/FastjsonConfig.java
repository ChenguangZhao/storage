package com.alibaba.monitor.config;

import java.nio.charset.Charset;
import java.util.ArrayList;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.monitor.converter.FastJsonpHttpMessageConverter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/08/22
 */
@Configuration
public class FastjsonConfig {

    @Bean
    public FastJsonpHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonpHttpMessageConverter converter = new FastJsonpHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
            SerializerFeature.PrettyFormat,
            SerializerFeature.WriteMapNullValue
        );
        fastJsonConfig.setCharset(Charset.forName("utf-8"));
        converter.setFastJsonConfig(fastJsonConfig);

        ArrayList<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(mediaTypes);
        return converter;
    }

}
