package com.xz.nacos.service;

/**
 * @author xz
 * @Description Sentinel 对于非BlockException的异常回调处理器
 * @date 2021/9/24 0024 10:06
 **/
public class FallBackHandler {
    public static String callBack(Throwable e) {
        e.printStackTrace();
        return "fallBack";
    }
}
