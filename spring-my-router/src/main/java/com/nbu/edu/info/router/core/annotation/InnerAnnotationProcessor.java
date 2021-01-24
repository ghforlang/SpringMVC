package com.nbu.edu.info.router.core.annotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 *
 */
public class InnerAnnotationProcessor extends AbstractProcessor {

    private static Processor processor;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        processor = initializeInnerProcessor();
        return processor.process(annotations,roundEnv);
    }

    // TODO
    private static Processor initializeInnerProcessor(){
        return (Processor)new Object();
    }
}
