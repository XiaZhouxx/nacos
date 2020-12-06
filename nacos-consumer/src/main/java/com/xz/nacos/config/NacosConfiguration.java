package com.xz.nacos.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xz
 * @ClassName NacosConfiguration
 * @Description
 * @date 2020/11/6 0006 22:48
 **/
@Configuration
@EnableConfigurationProperties(MyNacosDiscoveryProperties.class)
public class NacosConfiguration {

}
