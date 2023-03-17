package com.nacos.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * remark.
 *
 * @author xz
 * @date 2023/3/16 15:08
 */
@SpringBootApplication
public class MqApplication {
    private static final Logger log = LoggerFactory.getLogger(MqApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(MqApplication.class, args);
        log.info("application start success!");
    }
}
