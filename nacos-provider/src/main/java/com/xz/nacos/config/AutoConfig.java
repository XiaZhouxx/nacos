package com.xz.nacos.config;

import com.xz.nacos.annotation.CustomService;
import com.xz.nacos.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

/**
 * @author xz
 * @date 2023/8/10 10:32
 */
@CustomService
@Slf4j
public class AutoConfig {
    @Bean
    public User user() {
        log.info("bean init");
        return new User(1,2,3);
    }
}
