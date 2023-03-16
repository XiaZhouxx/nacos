package com.xz.address.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.swing.*;
import java.util.Map;
import java.util.Properties;

/**
 * Spring 内部环境获取.
 *
 * @author xz
 * @date 2022/11/2 9:16
 */
@RestController
public class SpringInnerEnvironmentController {
    Logger log = LoggerFactory.getLogger(SpringInnerEnvironmentController.class);
    @Autowired(required = false)
    private Properties systemProperties;

    @Resource
    private Environment environment;

    @GetMapping("/getEnv")
    public Map<String, Object> getInnerEnv() {

        log.info("get env...");
        return ((StandardEnvironment)environment).getSystemEnvironment();
    }
}
