package com.example;


import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.FlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableDiscoveryClient
@EnableApolloConfig
@SpringBootApplication
@EnableFeignClients
@EnableRedisHttpSession(flushMode = FlushMode.ON_SAVE)
public class CustomerApplication {


    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

}

