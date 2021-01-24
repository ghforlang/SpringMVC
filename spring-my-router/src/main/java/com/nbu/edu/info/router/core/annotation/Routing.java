package com.nbu.edu.info.router.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Inherited
public @interface Routing {

    /**
     * 支持按照beanName路由
     */
    String beanName();

    /**
     * 支持按照type路由
     * @return
     */
    Class<?> type();
}
