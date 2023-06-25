package com.xz.nacos;

import com.xz.nacos.spi.CustomProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xz
 * @ClassName NacosProviderApplication
 * @Description
 * @date 2020/12/5 19:24
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class NacosProviderApplication {
    public static void main(String[] args) {
        CustomProcessor cp = new CustomProcessor();
        SpringApplication.run(NacosProviderApplication.class, args);
    }
}
