package com.xz.nacos.nacos.listner;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.config.NacosConfigService;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author xz
 * @since 2024/7/9 16:41
 */
@Component
public class GatewayRouteConfigListener {

    @Resource
    NacosConfigManager configManager;
    @Resource
    RouteDefinitionWriter routeDefinitionWriter;

    @PostConstruct
    public void addConfigListener() {
        ConfigService configService = configManager.getConfigService();
        try {
            configService.addListener("xz-gateway", "test", new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    List<RouteDefinition> routes = JSON.parseArray(configInfo, RouteDefinition.class);
                    routes.forEach(r -> routeDefinitionWriter.save(Mono.just(r)));
                }
            });
        } catch (NacosException e) {
            // ignore error.
            e.printStackTrace();
        }
    }
}
