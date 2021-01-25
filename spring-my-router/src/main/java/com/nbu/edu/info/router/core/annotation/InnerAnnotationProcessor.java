package com.nbu.edu.info.router.core.annotation;

import com.nbu.edu.cn.utils.JackSonUtils;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 *
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("com.nbu.edu.info.router.core.annotation.Routing")
public class InnerAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("process innerAnnotations : " + JackSonUtils.toJsonString(annotations));
        System.out.println("process roundEnv : " + JackSonUtils.toJsonString(roundEnv));

        for (TypeElement typeElement : annotations) {
            Set<? extends Element> elementsAnnotated = roundEnv.getElementsAnnotatedWith(typeElement);
            for(Element element : elementsAnnotated){
                String beanName = element.getAnnotation(Routing.class).beanName();
                Class<?> clazz = element.getAnnotation(Routing.class).type();
                System.out.println("beanName = " + beanName);
                System.out.println("type = " + clazz.getName());
            }
        }
        return true;
    }
    
}
