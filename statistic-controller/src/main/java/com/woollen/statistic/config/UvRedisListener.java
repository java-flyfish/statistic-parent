package com.woollen.statistic.config;

import com.alibaba.fastjson.JSONObject;
import com.woollen.statistic.entry.UvStatistic;
import com.woollen.statistic.service.UvStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @Info:
 * @ClassName: UvRedisListener
 * @Author: weiyang
 * @Data: 2019/10/15 2:54 PM
 * @Version: V1.0
 **/
@Component
public class UvRedisListener implements MessageListener {

    @Autowired
    private UvStatisticService uvStatisticService;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] body = message.getBody();//请使用valueSerializer
        byte[] channel = message.getChannel();
        String json = new String(body);

        UvStatistic uvStatistic = JSONObject.parseObject(json, UvStatistic.class);
        String topic = new String(channel);
        uvStatistic.setCreated(System.currentTimeMillis());
        uvStatistic.setUpdated(System.currentTimeMillis());

        Integer i = uvStatisticService.insert(uvStatistic);

    }
}
