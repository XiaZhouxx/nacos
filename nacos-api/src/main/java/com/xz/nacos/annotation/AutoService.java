package com.xz.nacos.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author xz
 * @date 2023/6/23 11:12
 */
@Documented
@Target({ElementType.TYPE})
public @interface AutoService {
    Class<?> value();
}