package com.xz.nacos.limiter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author xz
 * @ClassName 限流器Key生成
 * @Description
 * @date 2020/12/5 22:51
 **/
@Component("hostKeyResolver")
public class HostKeyResolver implements KeyResolver {
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        // 根据业务规则生成限流Key, 例如IP, 业务唯一ID, URL等。
        return Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

}
