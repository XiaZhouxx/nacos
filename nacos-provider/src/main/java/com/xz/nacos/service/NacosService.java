package com.xz.nacos.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

/**
 * @author xz
 * @ClassName NacosService
 * @Description
 * @date 2021/9/23 0023 13:40
 **/
@Service
public class NacosService {
    /**
     * 注解的实现, 粒度是基于任意的方法的, 更为灵活, 推荐使用这种
     * 底层基于SpringAOP {@link SentinelResourceAspect}
     */


    /**
     * sentinel针对blockException(Sentinel内部slot 熔断、流控、黑白名单)的异常处理
     * @return
     */
    @SentinelResource(blockHandler = "block", blockHandlerClass = BlockHandler.class)
    public String service() {
        return "service success";
    }

    /**
     * 非blockException的异常处理
     * @return
     */
    @SentinelResource(fallback = "callBack", fallbackClass = FallBackHandler.class)
    public String service1() {
        throw new RuntimeException();
    }

    public String block1(BlockException e) {
        e.printStackTrace();
        return "block1";
    }
}
