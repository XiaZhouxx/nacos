package com.xz.nacos.service;


import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author xz
 * @Description
 * @date 2021/9/23 0023 16:25
 **/
public class BlockHandler {
    /**
     * Sentinel内部规则熔断之后的异常处理器, 必须方法名必须和blockHandler一致,
     * 参数列表在原有的基础外 还需要在末尾增加一个BlockException 用于识别触发的异常
     * 且外部处理器方法必须为static
     */
    public static String block(BlockException e) {
        e.printStackTrace();
        return "block";
    }
}
