package com.woollen.statistic.service;

import com.woollen.statistic.entry.PvStatistic;

import java.util.List; /**
 * @Info:
 * @ClassName: PvStatisticService
 * @Author: weiyang
 * @Data: 2019/10/15 5:04 PM
 * @Version: V1.0
 **/
public interface PvStatisticService {
    Integer insertBatch(List<PvStatistic> pvStatistics);
}
