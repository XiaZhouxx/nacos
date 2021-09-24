package com.xz.nacos.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
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
