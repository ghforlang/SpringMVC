package com.edu.nbu.demo.registrar;

import com.edu.nbu.demo.registrar.annotation.FTest;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.util.Set;

public class MyClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {


    public MyClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        return super.doScan(basePackages);
    }

    @Override
    protected void registerDefaultFilters() {
        // 加载spring注解的bedefinition，其实默认父类也会加载，在父类构造方法内部；
        // 另外，AnnotationConfigApplicationContext在初始化的时候，也会默认加载spring注解的BeanDefinition；
        super.registerDefaultFilters();
        addIncludeFilter(new AnnotationTypeFilter(FTest.class));
        //同样可以指定不加载哪些注解
//        addExcludeFilter(new AnnotationTypeFilter(Component.class));
    }
}
