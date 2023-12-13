package com.xz.nacos;

import com.alibaba.csp.sentinel.command.CommandCenterProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author xz
 * @ClassName NacosProviderApplication
 * @Description
 * @date 2020/12/5 19:24
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class NacosProviderApplication {
    
    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext run = SpringApplication.run(NacosProviderApplication.class, args);
        // 优雅的关闭Spring boot 服务.
//        run.close();
        // 当时服务使用了sentinel, 需要手动关闭sentinel, 否则进程还是会挂起
//        CommandCenterProvider.getCommandCenter().stop();
//        Thread shutdownHook = new Thread("CUSTOM") {
//            @Override
//            public void run() {
//                log.info("shutdownHook start");
//            }
//        };
//        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }
}
