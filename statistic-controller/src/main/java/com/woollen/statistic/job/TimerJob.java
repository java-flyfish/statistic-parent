package com.woollen.statistic.job;

import com.alibaba.fastjson.JSONObject;
import com.woollen.statistic.entry.PvStatistic;
import com.woollen.statistic.mapper.PvStatisticMapper;
import com.woollen.statistic.request.PvRequest;
import com.woollen.statistic.service.PvStatisticService;
import com.woollen.statistic.utils.RedisUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Info:
 * @ClassName: TimerJob
 * @Author: weiyang
 * @Data: 2019/10/14 5:41 PM
 * @Version: V1.0
 **/
@Component
public class TimerJob{

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private PvStatisticService pvStatisticService;

    @Scheduled(cron = "0 0/5 * * * ? ")
    public void statisticToDb(){
        Set<String> keys = redisTemplate.keys(RedisUtils.redis_pv_prefix + "*");
        if (keys.isEmpty()){
            return;
        }

        List<PvStatistic> pvStatistics = new ArrayList<>();
        for (String key : keys) {
            String click = redisTemplate.opsForValue().getAndSet(key, "0");
            if (Integer.valueOf(click) == 0){
                continue;
            }
            String json = key.substring(key.indexOf(":")+1);
            PvRequest pvRequest = JSONObject.parseObject(json, PvRequest.class);
            PvStatistic pvStatistic = new PvStatistic();
            BeanUtils.copyProperties(pvRequest,pvStatistic);

            pvStatistic.setClick(Integer.valueOf(click));
            pvStatistic.setCreated(System.currentTimeMillis());
            pvStatistic.setUpdated(System.currentTimeMillis());
            pvStatistics.add(pvStatistic);
        }
        if (!pvStatistics.isEmpty()){
            pvStatisticService.insertBatch(pvStatistics);
        }
    }

    //清除日活用户
    @Scheduled(cron = "0 0 0 1/1 * ? ")
    public void clearRedisUv(){
        Set<String> keys = redisTemplate.keys(RedisUtils.redis_uv_prefix + "*");
        redisTemplate.delete(keys);
    }
}
