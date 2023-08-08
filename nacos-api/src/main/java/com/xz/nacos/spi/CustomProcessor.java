package com.xz.nacos.spi;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.xz.nacos.annotation.CustomService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author xz
 * @date 2023/6/15 17:58
 */
@SupportedOptions({"debug", "verify"})
public class CustomProcessor extends AbstractProcessor {
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    public ImmutableSet<String> getSupportedAnnotationTypes() {
        return ImmutableSet.of(CustomService.class.getName());
    }

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
            generateFile();
        }
        return true;
    }

    private void generateFile() {
        Filer filer = this.processingEnv.getFiler();

            String resourceFile = "META-INF/static/test.txt";

            try {
                TreeSet allServices = Sets.newTreeSet();

                try {
                    FileObject existingFile = filer.getResource(StandardLocation.CLASS_OUTPUT, "", resourceFile);
                    Set<String> oldServices = ServicesFiles.readServiceFile(existingFile.openInputStream());
                    allServices.addAll(oldServices);
                } catch (IOException var9) {

                }
                allServices.add("hello processor");

                FileObject fileObject = filer.createResource(StandardLocation.CLASS_OUTPUT, "", resourceFile, new Element[0]);
                OutputStream out = fileObject.openOutputStream();
                ServicesFiles.writeServiceFile(allServices, out);
                out.close();
            } catch (IOException var10) {
                return;
            }
    }


}
