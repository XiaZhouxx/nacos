package com.xz.nacos.controller;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.xz.nacos.service.NacosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 验证sentinel.
 *
 * @author xz
 * @date 2023/3/13 11:32
 */
@RestController
@RequestMapping("/st")
public class SentinelController {
    @Resource
    NacosService nacosService;

    @RequestMapping("/test/{mode}")
    public String test(@PathVariable int mode) {
        if (mode % 2 == 0) {
            return nacosService.service1();
        } else {
            return nacosService.service();
        }
    }

    @GetMapping("/initFlow")
    public String initFlowRule() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
        return "success";
    }
}
