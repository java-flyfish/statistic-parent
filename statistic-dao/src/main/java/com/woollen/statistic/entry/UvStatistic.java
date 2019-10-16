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
 * uv表
 * </p>
 *
 * @author weiyang
 * @since 2019-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UvStatistic extends Model<UvStatistic> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 来源,1:安卓,2:ios,3:小程序,4:网页
     */
    private Integer source;

    /**
     * 类型，1:登陆，2:注册，3：其他
     */
    private Integer type;

    /**
     * 用户id
     */
    private Integer userId;

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
