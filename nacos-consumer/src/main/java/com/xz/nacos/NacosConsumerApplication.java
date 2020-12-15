package com.xz.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xz
 * @ClassName NacosApplication
 * @Description
 * @date 2020/5/1
 * 1 0011 22:59
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
public class NacosConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApplication.class, args);
    }
}
