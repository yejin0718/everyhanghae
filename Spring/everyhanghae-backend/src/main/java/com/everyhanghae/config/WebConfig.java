package com.everyhanghae.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String REACT_LOCAL_HOST = "http://localhost:3000";
    private static final String REACT_APP1 = "http://everyhanghae.s3-website.ap-northeast-2.amazonaws.com/";
    private static final String REACT_APP2 = "http://donghyun-hanghae.s3-website.ap-northeast-2.amazonaws.com/";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins(REACT_LOCAL_HOST, REACT_APP1, REACT_APP2)
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("Authorization");
    }
}
