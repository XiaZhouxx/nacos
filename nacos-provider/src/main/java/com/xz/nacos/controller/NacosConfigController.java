package com.xz.nacos.controller;

import com.alibaba.cloud.nacos.refresh.NacosRefreshHistory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xz
 * @date 2022/3/31 9:48
 */
@RestController
public class NacosConfigController {
    @Resource
    NacosRefreshHistory history;

    @SentinelResource
    @RequestMapping("/history")
    @SentinelResource
    public Object getConfigHistory() {
        return history.getRecords();
    }


}
