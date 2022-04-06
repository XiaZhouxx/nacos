package com.xz.address.controller;

import com.xz.address.entity.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xz
 * @date 2022/4/1 14:40
 */
@RestController
public class AddressServerController {
    private Logger log = LoggerFactory.getLogger(AddressServerController.class);
    @RequestMapping("/nacos/serverlist")
    public RestResult<String> serverList() {
        log.info("receive get nacos server list request");
        // 集群节点地址 逗号','分隔多个即可
        return new RestResult<>(200, "127.0.0.1:8848,127.0.0.1:8849");
    }
}
