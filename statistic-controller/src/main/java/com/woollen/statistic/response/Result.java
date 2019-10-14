package com.woollen.statistic.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Info:
 * @ClassName: Result
 * @Author: weiyang
 * @Data: 2019/9/27 9:46 AM
 * @Version: V1.0
 **/
@Data
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    private Object data;
    private Boolean success;
    private String msg;
    public Result(){}

    public Result(Object data, Boolean success, String msg){
        this.data = data;
        this.success = success;
        this.msg = msg;
    }
}
