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
     * @return
     */
    @SentinelResource(value = "test",blockHandler = "block", blockHandlerClass = BlockHandler.class)
    public String service() {
        System.out.println("service ....");
        return "hello world";
    }

    @SentinelResource(value = "xz", blockHandler = "block1", fallback = "callBack", fallbackClass = FallBackHandler.class)
    public String service1() {
        throw new RuntimeException();
        //return "test";
    }

    public String block1(BlockException e) {
        e.printStackTrace();
        return "block1";
    }
}
