package com.woollen.statistic.service.impl;

import com.woollen.statistic.entry.UvStatistic;
import com.woollen.statistic.mapper.UvStatisticMapper;
import com.woollen.statistic.service.UvStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Info:
 * @ClassName: UvStatisticServiceImpl
 * @Author: weiyang
 * @Data: 2019/10/15 5:01 PM
 * @Version: V1.0
 **/
@Service
public class UvStatisticServiceImpl implements UvStatisticService {

    @Autowired
    private UvStatisticMapper uvStatisticMapper;

    @Override
    public Integer insert(UvStatistic uvStatistic) {
        int insert = uvStatisticMapper.insert(uvStatistic);
        return insert;
    }
}
