package com.woollen.statistic.config;

import com.woollen.statistic.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @Info:
 * @ClassName: RedisListenerConfig
 * @Author: weiyang
 * @Data: 2019/10/15 3:12 PM
 * @Version: V1.0
 **/
@Configuration
public class RedisListenerConfig {

    @Autowired
    private UvRedisListener uvRedisListener;
    @Bean
    MessageListenerAdapter listenerAdapter(UvRedisListener receiver){
        //这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“MessageReceiveTwo ”
        return new MessageListenerAdapter(uvRedisListener, "uvRedisListener");
    }
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter,new PatternTopic(RedisUtils.redis_uv_topic));
        return container;
    }
}
