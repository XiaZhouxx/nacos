package com.xz.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xz
 * @since 2024/7/19 10:17
 */
@RestController
@RequestMapping("/nacos")
@RefreshScope
public class NacosRefreshController {
    @Value("${nacos.server.port:}")
    String port;

    @Value("#{'${spring.profiles.active:}' + '${test.config:}'}")
    String config;
    @GetMapping("/pc")
    public Object getProfileConfig() {
        return config;
    }

    @GetMapping("/pts")
    public Object getProperties(){
        return port;
    }
}
