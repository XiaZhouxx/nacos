package com.xz.nacos.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xz.nacos.service.NacosService;
import org.springframework.stereotype.Component;

/**
 * @author xz
 * @ClassName NacosServiceImpl
 * @Description
 * @date 2020/5/11 0011 23:21
 **/
@Service
@Component
public class NacosServiceImpl implements NacosService {

    @Override
    public void test() {
        System.out.println("测试");
    }
}
