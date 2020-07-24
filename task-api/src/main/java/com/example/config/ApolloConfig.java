package com.example.config;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApolloConfig implements ApplicationContextAware {

    private Logger logger = LoggerFactory.getLogger(ApolloConfig.class);

    private ApplicationContext applicationContext;

    private RefreshScope refreshScope;

    public ApolloConfig(RefreshScope refreshScope){
        this.refreshScope = refreshScope;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       this.applicationContext =  applicationContext;
    }

    @ApolloConfigChangeListener
    public void configChangeListener(ConfigChangeEvent configChangeEvent){
        logger.info("APOLLO start to change configs");

        for (String changedKey : configChangeEvent.changedKeys()) {
            logger.info("changedKey :{}",changedKey);
            logger.info("changedValue :{}",configChangeEvent.getChange(changedKey));
        }

        try {
            //更新配置
            this.applicationContext.publishEvent(new EnvironmentChangeEvent(configChangeEvent.changedKeys()));
            logger.info("APOLLO changed configs successfully");
        }catch (Exception e){
            logger.error("APOLLO changed configs unsuccessfully",e);
        }
    }
}
