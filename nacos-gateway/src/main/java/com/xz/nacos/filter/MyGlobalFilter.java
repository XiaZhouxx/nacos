package com.xz.nacos.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author xz
 * @Description 全局过滤器 该过滤器装载进 Spring容器中 就会全局生效
 * @date 2020/12/5 22:46
 **/
public class MyGlobalFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取Request/Response
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 全局请求过滤、限流、黑白名单、鉴权等。

        String rawPath = request.getURI().getRawPath();


        return null;
    }
}
