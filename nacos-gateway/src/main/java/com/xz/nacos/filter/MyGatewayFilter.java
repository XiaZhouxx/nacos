package com.xz.nacos.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 *  * Gateway中局部过滤器，装载进Spring容器中也不会生效，不单独使用，一般配合FilterFactory处理。
 *
 * @author xz
 * @date 2020/12/7 9:59
 **/
public class MyGatewayFilter implements GatewayFilter {

    private Logger log = LoggerFactory.getLogger(MyGatewayFilter.class);

    private AbstractNameValueGatewayFilterFactory.NameValueConfig config;

    public MyGatewayFilter(AbstractNameValueGatewayFilterFactory.NameValueConfig config) {
        this.config = config;
    }

    public MyGatewayFilter() {
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("my filter config name : {}, value : {}", config.getName(), config.getValue());
        return chain.filter(exchange);
    }
}

