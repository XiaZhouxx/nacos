package com.xz.nacos;

import com.xz.nacos.annotation.AutoService;
import com.xz.nacos.annotation.CustomService;
import org.springframework.cloud.openfeign.FeignClient;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @author xz
 * @date 2023/6/23 11:13
 */
@AutoService(Processor.class)
public class TestService extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }
}
