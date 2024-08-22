package com.xz.nacos;

import com.alibaba.csp.sentinel.command.CommandCenterProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author xz
 * @ClassName NacosProviderApplication
 * @Description
 * @date 2020/12/5 19:24
 **/
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class NacosProviderApplication {

    /**
     * spring boot 加配置中心的配置加载优先级
     * 1. 在spring cloud 环境下 优先加载bootstrap.yml (默认配置spring.cloud.bootstrap.enabled:true|spring.cloud.bootstrap.name:bootstrap)
     * 2. 然后加载application.yml/properties/yaml/xml (默认配置 spring.config.name:application)
     * 3. 最后加载配置中心的配置
     * 后加载的配置会覆盖之前加载的配置
     */
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
