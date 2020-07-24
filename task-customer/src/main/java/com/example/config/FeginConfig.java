package com.example.config;

import com.example.controller.CustomerContronller;
import com.example.interceptor.FeginIterceptor;
import feign.RequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeginConfig {

    private Logger logger = LoggerFactory.getLogger(CustomerContronller.class);

    @Bean
    public RequestInterceptor requestInterceptor(){
        return new FeginIterceptor();
    }
}
