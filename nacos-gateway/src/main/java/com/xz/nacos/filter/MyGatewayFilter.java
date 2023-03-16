package com.xz.nacos.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 *  * Gateway中局部过滤器，装载进Spring容器中也不会生效，不单独使用，一般配合FilterFactory处理。
 *
 * @author xz
 * @date 2020/12/7 9:59
 **/
public class MyGatewayFilter implements GatewayFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return null;
    }
}

