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

    @PostMapping(value = "/add")
    public BaseResult addRoute(@RequestBody RouteDefinition definition) {
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

