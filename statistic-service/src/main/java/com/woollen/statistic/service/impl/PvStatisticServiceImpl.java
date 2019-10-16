package com.woollen.statistic.service.impl;

import com.woollen.statistic.entry.PvStatistic;
import com.woollen.statistic.mapper.PvStatisticMapperExter;
import com.woollen.statistic.service.PvStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Info:
 * @ClassName: PvStatisticServiceImpl
 * @Author: weiyang
 * @Data: 2019/10/15 5:04 PM
 * @Version: V1.0
 **/
@Service
public class PvStatisticServiceImpl implements PvStatisticService {

    @Autowired
    private PvStatisticMapperExter pvStatisticMapperExter;

    @Override
    public Integer insertBatch(List<PvStatistic> pvStatistics) {

        Integer num = pvStatisticMapperExter.insertBatch(pvStatistics);
        return num;
    }
}
