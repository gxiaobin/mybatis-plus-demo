package com.zm.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;
/**
 * @author xiaobin
 * @date 2019/5/8 18:14
 * @desc feign请求配置
 */
@Configuration
public class FeignConfig {
    @Bean
    public Retryer feignRetryer(){
        return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1),5);
    }
}

