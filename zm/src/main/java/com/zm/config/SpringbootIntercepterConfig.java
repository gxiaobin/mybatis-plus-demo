package com.zm.config;//package com.scb.sysadmin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xiaobin
 * @date 2019/4/22 15:49
 */
@Configuration
public class SpringbootIntercepterConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/**/swagger-ui.html", "/**/api-docs", "/swagger-resources/**", "/login/**");
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/**/swagger-ui.html","/**/api-docs", "/swagger-resources/**","/login/**");
//

    }
}

