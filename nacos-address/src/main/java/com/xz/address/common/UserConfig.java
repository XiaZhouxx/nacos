package com.xz.address.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * remark.
 *
 * @author xz
 * @date 2022/11/3 10:08
 */
@Component
public class UserConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    User user;
    @Resource
    User user1;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        System.out.println("init method");
    }
}
