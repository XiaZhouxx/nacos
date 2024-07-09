package com.xz.nacos.controller;

import com.xz.nacos.domain.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author xz
 * @Description 路由配置
 * @date 2020/12/6 13:47
 **/
@RestController
@RequestMapping("/route")
public class RouterController implements ApplicationEventPublisherAware {
    @Autowired
    RouteDefinitionWriter routeDefinitionWriter;

    ApplicationEventPublisher applicationEventPublisher;
    @Resource
    GatewayProperties gatewayProperties;

    /**
     *
     * 动态路由大致需要的参数, 内置的一个filter 和 predicate 需要根据具体的config来生成args
     *
     * {
     *     "id": "nacos-provider",
     *     "uri": "lb://nacos-provider",
     *     "filters": [
     *         {
     *             "name": "My",
     *             "args": {
     *                 "name": "API测试",
     *                 "value": "API值"
     *             }
     *         },
     *         {
     *             "name": "StripPrefix",
     *             "args": {
     *                 "parts": 1
     *             }
     *         }
     *     ],
     *     "predicates": [
     *         {
     *             "name": "Path",
     *             "args": {
     *                 "patterns": "/nacos-provider/**"
     *             }
     *         }
     *     ]
     * }
     *
     *
     */

    @PostMapping(value = "/add")
    public BaseResult addRoute(@RequestBody RouteDefinition definition) {
        routeDefinitionWriter.delete(Mono.just(definition.getId())).subscribe();
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        gatewayProperties.getRoutes().add(definition);
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
        return BaseResult.success();
    }

    @DeleteMapping("/delete/{id}")
    public BaseResult deleteRoute(@PathVariable String id) {
        routeDefinitionWriter.delete(Mono.just(id)).subscribe();
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
        return BaseResult.success();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}

