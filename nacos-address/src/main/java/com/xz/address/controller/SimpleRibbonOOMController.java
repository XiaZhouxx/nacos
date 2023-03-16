package com.xz.address.controller;

import com.netflix.loadbalancer.*;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * 错误使用ribbon导致内存泄露问题.
 *
 * @author xz
 * @date 2022/11/10 10:43
 */
@RestController
public class SimpleRibbonOOMController {

    /**
     * 负载均衡请求
     */
    @RequestMapping("/lb/request")
    public String request() {
        List<String> urls = new ArrayList<>();
        urls.add("www.baidu.com");
        urls.add("www.4399.com");

        /*
           如果直接基于ribbon 使用时, 不要使用动态负载均衡器(ZoneAwareLoadBalancer/DynamicServerListLoadBalancer), 其内部会维护线程定期刷新本地服务表.
           意味着每次调用创建的负载均衡都无法被释放，出现内存泄露导致溢出;
         */
//        ZoneAwareLoadBalancer loadBalancer = LoadBalancerBuilder.newBuilder()
//                .withRule(new RoundRobinRule())
//                .withDynamicServerList(new ServerList<Server>() {
//                    @Override
//                    public List<Server> getInitialListOfServers() {
//                        return getServers(urls);
//                    }
//
//                    @Override
//                    public List<Server> getUpdatedListOfServers() {
//                        return getServers(urls);
//                    }
//                })
//                .buildDynamicServerListLoadBalancer();

        BaseLoadBalancer loadBalancer = LoadBalancerBuilder.newBuilder()
                .withRule(new RoundRobinRule())
                .buildFixedServerListLoadBalancer(getServers(urls));

        String url = LoadBalancerCommand.<String>builder().withLoadBalancer(loadBalancer)
                .build()
                .submit((server) -> Observable.just(server.getId())).toBlocking()
                .single();
        return url;
    }

    public List<Server> getServers(List<String> urls) {
        List<Server> servers = new ArrayList<>();
        for (String url : urls) {
            servers.add(new Server(url));
        }
        return servers;
    }

    public static List<Server> getServer(List<String> urls) {
        List<Server> servers = new ArrayList<>();
        for (String url : urls) {
            servers.add(new Server(url));
        }
        return servers;
    }
}
