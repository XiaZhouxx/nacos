package com.xz.nacos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xz
 * @ClassName NacosGatewayApplication
 * @Description
 * @date 2020/12/5 19:54
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.xz.nacos.mapper")
public class NacosGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosGatewayApplication.class, args);
    }
}
