package com.xz.nacos.route.repository;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xz.nacos.domain.RouteInfo;
import com.xz.nacos.mapper.GatewayRouteMapper;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 基于MySQL数据库存储路由信息.
 */
@Component
public class InMysqlRouteDefinitionRepository implements RouteDefinitionRepository {

    @Resource
    GatewayRouteMapper routeMapper;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        // gateway 内部有缓存机制, 这里虽然是实时查的, 如果变更之后还需要手动触发事件清除缓存 RefreshRoutesEvent
        List<RouteInfo> all = routeMapper.selectList(null);
        return Flux.fromIterable(
                all.stream()
                        .map(r -> JSONObject.parseObject(r.getRouteDefinition(), RouteDefinition.class))
                        .collect(Collectors.toList())
        );
    }


    @Override
    @Transactional
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(r -> {
            if (StringUtils.isEmpty(r.getId())) {
                return Mono.error(new IllegalArgumentException("id may not be empty"));
            }
            QueryWrapper<RouteInfo> qw = new QueryWrapper<>();
            qw.lambda().eq(RouteInfo::getRouteId, r.getId());
            routeMapper.delete(qw);
            // 根据id做增/改.
            RouteInfo rd = new RouteInfo();
            rd.setRouteId(r.getId());
            rd.setRouteDefinition(JSONObject.toJSONString(r));
            routeMapper.insert(rd);
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(r -> {
            if (StringUtils.isEmpty(r)) {
                return Mono.error(new IllegalArgumentException("id may not be empty"));
            }
            QueryWrapper<RouteInfo> qw = new QueryWrapper<>();
            qw.lambda().eq(RouteInfo::getRouteId, r);
            routeMapper.delete(qw);
            return Mono.empty();
        });
    }
}
