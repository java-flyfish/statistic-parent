package com.woollen.statistic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woollen.statistic.entry.PvStatistic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * pv表 Mapper 接口
 * </p>
 *
 * @author weiyang
 * @since 2019-10-15
 */
public interface PvStatisticMapperExter extends BaseMapper<PvStatistic> {
    Integer insertBatch(@Param("pvStatistics")List<PvStatistic> pvStatistics);
}
