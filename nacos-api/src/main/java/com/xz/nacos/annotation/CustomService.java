package com.xz.nacos.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author xz
 * @date 2023/6/23 11:24
 */
@Component
@Documented
@Target({ElementType.TYPE})
public @interface CustomService {
}
