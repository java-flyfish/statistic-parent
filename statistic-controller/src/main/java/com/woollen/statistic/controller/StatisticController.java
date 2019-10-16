package com.woollen.statistic.controller;

import com.alibaba.fastjson.JSONObject;
import com.woollen.statistic.request.PvRequest;
import com.woollen.statistic.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Info:
 * @ClassName: StatisticController
 * @Author: weiyang
 * @Data: 2019/10/14 3:54 PM
 * @Version: V1.0
 **/
@RestController
@RequestMapping("statistic")
public class StatisticController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("pvStatistic")
    public void pvStatistic(HttpServletRequest request, PvRequest pvRequest){
        String key = RedisUtils.redis_pv_prefix + JSONObject.toJSONString(pvRequest);
        if (!redisTemplate.hasKey(key)){
            redisTemplate.opsForValue().set(key,"1");
        }else {
            redisTemplate.opsForValue().increment(key);
        }
    }

    /*@GetMapping("uvStatistic")
    public void uvStatistic(HttpServletRequest request,UvRequest pvRequest){
        String key = RedisUtils.redis_pv_prefix + pvRequest.getSource() + ":" + pvRequest.getPath();
        if (!redisTemplate.hasKey(key)){
            redisTemplate.opsForValue().set(key,"1");
        }else {
            redisTemplate.opsForValue().increment(key);
        }
    }*/
}
