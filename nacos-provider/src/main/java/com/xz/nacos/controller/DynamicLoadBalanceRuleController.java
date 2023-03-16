package com.xz.nacos.controller;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态负载均衡控制器.
 * @author xz
 * @date 2022/3/30 15:02
 */
@RestController
public class DynamicLoadBalanceRuleController {
    @Autowired
    SpringClientFactory clientFactory;

    static Map<String, IRule> ruleMap = new HashMap<>();
    static {
        ruleMap.put("random", new RandomRule());
        ruleMap.put("round", new RoundRobinRule());
        ruleMap.put("nacos", new NacosRule());
    }

    @RequestMapping("/set/rule")
    public String dynamicRule(@RequestBody RibbonRuleEditDto dto) {
        final ILoadBalancer loadBalancer = clientFactory.getLoadBalancer(dto.getClientName());
        if (loadBalancer instanceof BaseLoadBalancer) {
            ((BaseLoadBalancer) loadBalancer).setRule(ruleMap.getOrDefault(dto.getRule(), new RoundRobinRule()));
        }
        return "SUCCESS";
    }
}
