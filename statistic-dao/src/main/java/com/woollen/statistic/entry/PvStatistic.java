package com.woollen.statistic.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * pv表
 * </p>
 *
 * @author weiyang
 * @since 2019-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PvStatistic extends Model<PvStatistic> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 来源
     */
    private String source;

    /**
     * 归属
     */
    private String attach;

    /**
     * 点击类型，0:普通点击
     */
    private Integer type;

    /**
     * 链接
     */
    private String path;

    /**
     * 点击次数
     */
    private Integer click;

    /**
     * 创建时间
     */
    private Long created;

    /**
     * 更新时间
     */
    private Long updated;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
