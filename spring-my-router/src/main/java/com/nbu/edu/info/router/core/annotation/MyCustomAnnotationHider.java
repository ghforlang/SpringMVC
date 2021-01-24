package com.nbu.edu.info.router.core.annotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * 参照lombok 的 AnnotationProcessorHider设计方式，个人认为，可能是单例原因
 */
public class MyCustomAnnotationHider {

    public static class MyCustomAnnotationProcessor extends AbstractProcessor {

        private static Processor processor;

        @Override
        public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
            processor = initializeInnerProcessor();
            return processor.process(annotations,roundEnv);
        }

        // TODO
        private static Processor initializeInnerProcessor(){
            return new InnerAnnotationProcessor();
        }
    }
}
