package com.xz.nacos;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

/**
 * @author xz
 * @date 2023/6/15 17:58
 */
public class CustomProcessor extends AbstractProcessor {

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        try {
            return this.processImpl(annotations, roundEnv);
        } catch (Exception var5) {
            StringWriter writer = new StringWriter();
            var5.printStackTrace(new PrintWriter(writer));
            return true;
        }
    }

    private boolean processImpl(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {

        }

        return true;
    }
}
