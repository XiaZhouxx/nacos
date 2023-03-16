package com.xz.nacos.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Nacos 配置刷新会触发的事件.
 *
 * @author xz
 * @date 2023/1/30 16:55
 */
public class NacosConfigEnvListener  implements ApplicationListener<RefreshEvent> {
    @Override
    public void onApplicationEvent(RefreshEvent event) {
        System.out.println(event);
        System.out.println(event.getEventDesc());
    }
}
