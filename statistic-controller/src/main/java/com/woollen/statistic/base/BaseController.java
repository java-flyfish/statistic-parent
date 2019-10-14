package com.woollen.statistic.base;


import com.woollen.statistic.response.Result;

/**
 * @Info:
 * @ClassName: BaseController
 * @Author: weiyang
 * @Data: 2019/9/27 9:51 AM
 * @Version: V1.0
 **/
public class BaseController {

    public Result success(Object data){
        return new Result(data,true,"处理成功");
    }

    public Result error(String msg){
        return new Result(null,false,msg);
    }
}
