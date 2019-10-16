package com.woollen.statistic.config;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * title
 * @Author weiyang
 * @Date 2:18 PM 2019/5/9
 * @Param
 * @return
 **/
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${redis.cluster.nodes}")
    private String nodes;
    @Value("${redis.host}")
    private String host;
    @Value("${redis.maxTotal}")
    private Integer maxTotal;
    @Value("${redis.minIdle}")
    private Integer minIdle;
    @Value("${redis.maxIdle}")
    private Integer maxIdle;
    @Value("${redis.cluster.maxAttempts}")
    private Integer maxAttempts;
    @Value("${redis.cluster.soTimeout}")
    private Integer soTimeout;
    @Value("${redis.cluster.timeOut}")
    private Integer timeOut;
    @Value("${redis.cluster.maxWaitMillis}")
    private Integer maxWaitMillis;
    @Value("${redis.cluster.nodes.port}")
    private int port;
    @Value("${redis.password}")
    private String password;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        return jedisPoolConfig;
    }

//    @Bean redis集群配置
    public RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        JSONArray objects = JSONArray.parseArray(nodes);
        List<RedisNode> redisNodes = new ArrayList<>();
        for (Object node : objects) {
            Map<String,Object> nodeMap = (Map<String,Object>)node;
            RedisNode redisNode = new RedisNode((String)nodeMap.get("node"),(Integer)nodeMap.get("port"));
            redisNodes.add(redisNode);
        }
        redisClusterConfiguration.setClusterNodes(redisNodes);
//        redisClusterConfiguration.setPassword(RedisPassword.of(password));
        return redisClusterConfiguration;
    }

    //    @Bean //redis集群配置
    public JedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPoolConfig, RedisClusterConfiguration redisClusterConfiguration) {
        JedisConnectionFactory factory = new JedisConnectionFactory(redisClusterConfiguration, jedisPoolConfig);
        return factory;
    }

    @Bean//单机版配置
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
//        factory.setPassword(password);
        factory.setPort(port);
        return factory;
    }


    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> template = new StringRedisTemplate();
        template.setConnectionFactory(factory);
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(stringSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
