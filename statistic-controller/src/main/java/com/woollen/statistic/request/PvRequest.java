package com.woollen.statistic.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Info:
 * @ClassName: PvRequest
 * @Author: weiyang
 * @Data: 2019/10/14 3:56 PM
 * @Version: V1.0
 **/
@Data
public class PvRequest {

    @NotBlank(message = "渠道来源不能为空！")
    private String source;
    //归属
    private String attach;

    private Integer type;

    @NotBlank(message = "页面链接不能为空！")
    private String path;
}
