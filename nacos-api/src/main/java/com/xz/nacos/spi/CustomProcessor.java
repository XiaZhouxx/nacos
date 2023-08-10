package com.xz.nacos.spi;

import com.google.auto.common.MoreElements;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.xz.nacos.annotation.CustomService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

/**
 * @author xz
 * @date 2023/6/15 17:58
 */
@SupportedOptions({"debug", "verify"})
public class CustomProcessor extends AbstractProcessor {
    private Multimap<String, String> providers = HashMultimap.create();

    public CustomProcessor() {
    }

    public ImmutableSet<String> getSupportedAnnotationTypes() {
        return ImmutableSet.of(CustomService.class.getName());
    }

    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        try {
            return this.processImpl(annotations, roundEnv);
        } catch (Exception var5) {
            StringWriter writer = new StringWriter();
            var5.printStackTrace(new PrintWriter(writer));
            this.fatalError(writer.toString());
            return true;
        }
    }

    private boolean processImpl(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            this.generateConfigFiles();
        } else {
            this.processAnnotations(annotations, roundEnv);
        }

        return true;
    }

    private void processAnnotations(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(CustomService.class);
        this.log(annotations.toString());
        this.log(elements.toString());
        Iterator i$ = elements.iterator();

        while(i$.hasNext()) {
            Element e = (Element)i$.next();
            TypeElement providerImplementer = (TypeElement)e;
            AnnotationMirror providerAnnotation = (AnnotationMirror) MoreElements.getAnnotationMirror(e, CustomService.class).get();
//            DeclaredType providerInterface = this.getProviderInterface(providerAnnotation);
//            TypeElement providerType = (TypeElement)providerInterface.asElement();
//            this.log("provider interface: " + providerType.getQualifiedName());
//            this.log("provider implementer: " + providerImplementer.getQualifiedName());
//            String providerTypeName;
//            if (!this.checkImplementer(providerImplementer, providerType)) {
//                providerTypeName = "ServiceProviders must implement their service provider interface. " + providerImplementer.getQualifiedName() + " does not implement " + providerType.getQualifiedName();
//                this.error(providerTypeName, e, providerAnnotation);
//            }
//
//            providerTypeName = this.getBinaryName(providerType);
            String providerImplementerName = this.getBinaryName(providerImplementer);
//            this.log("provider interface binary name: " + providerTypeName);
            this.log("provider implementer binary name: " + providerImplementerName);
            this.providers.put("spring.factories", providerImplementerName);
        }

    }

    private void generateConfigFiles() {
        Filer filer = this.processingEnv.getFiler();
        Iterator i$ = this.providers.keySet().iterator();

        while(i$.hasNext()) {
            String providerInterface = (String)i$.next();
            String resourceFile = "META-INF/" + providerInterface;

            try {
                TreeSet allServices = Sets.newTreeSet();

                try {
                    FileObject existingFile = filer.getResource(StandardLocation.CLASS_OUTPUT, "", resourceFile);
                    this.log("Looking for existing resource file at " + existingFile.toUri());
                    Set<String> oldServices = ServicesFiles.readServiceFile(existingFile.openInputStream());
                    this.log("Existing service entries: " + oldServices);
                    allServices.addAll(oldServices);
                } catch (IOException var9) {
                    this.log("Resource file did not already exist.");
                }

                Set<String> newServices = new HashSet(this.providers.get(providerInterface));
                if (allServices.containsAll(newServices)) {
                    this.log("No new service entries being added.");
                    return;
                }

                allServices.addAll(newServices);
                this.log("New service file contents: " + allServices);
                FileObject fileObject = filer.createResource(StandardLocation.CLASS_OUTPUT, "", resourceFile, new Element[0]);
                OutputStream out = fileObject.openOutputStream();
                ServicesFiles.writeSpringFactoriesFile(allServices, out);
                out.close();
                this.log("Wrote to: " + fileObject.toUri());
            } catch (IOException var10) {
                this.fatalError("Unable to create " + resourceFile + ", " + var10);
                return;
            }
        }

    }

    private boolean checkImplementer(TypeElement providerImplementer, TypeElement providerType) {
        String verify = (String)this.processingEnv.getOptions().get("verify");
        if (verify != null && Boolean.valueOf(verify)) {
            Types types = this.processingEnv.getTypeUtils();
            return types.isSubtype(providerImplementer.asType(), providerType.asType());
        } else {
            return true;
        }
    }

    private String getBinaryName(TypeElement element) {
        return this.getBinaryNameImpl(element, element.getSimpleName().toString());
    }

    private String getBinaryNameImpl(TypeElement element, String className) {
        Element enclosingElement = element.getEnclosingElement();
        if (enclosingElement instanceof PackageElement) {
            PackageElement pkg = (PackageElement)enclosingElement;
            return pkg.isUnnamed() ? className : pkg.getQualifiedName() + "." + className;
        } else {
            TypeElement typeElement = (TypeElement)enclosingElement;
            return this.getBinaryNameImpl(typeElement, typeElement.getSimpleName() + "$" + className);
        }
    }

    private DeclaredType getProviderInterface(AnnotationMirror providerAnnotation) {
        Map<? extends ExecutableElement, ? extends AnnotationValue> valueIndex = providerAnnotation.getElementValues();
        this.log("annotation values: " + valueIndex);
        AnnotationValue value = (AnnotationValue)valueIndex.values().iterator().next();
        return (DeclaredType)value.getValue();
    }

    private void log(String msg) {
        if (this.processingEnv.getOptions().containsKey("debug")) {
            this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, msg);
        }

    }

    private void error(String msg, Element element, AnnotationMirror annotation) {
        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, msg, element, annotation);
    }

    private void fatalError(String msg) {
        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "FATAL ERROR: " + msg);
    }
}
