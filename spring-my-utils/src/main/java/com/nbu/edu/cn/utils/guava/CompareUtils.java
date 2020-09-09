package com.nbu.edu.cn.utils.guava;

import com.google.common.collect.ComparisonChain;
import com.nbu.edu.cn.utils.PrimitiveTypeMapping;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ArrayUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

@UtilityClass
public class CompareUtils {

    public static <T extends Comparable<T>> int compare(Comparable<T> a, Comparable<T> b) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        BeanInfo beanInfo = Introspector.getBeanInfo(a.getClass());
        PropertyDescriptor[] pDescriptors = beanInfo.getPropertyDescriptors();
        Set<Method> methodSet = new HashSet<>();
        if(ArrayUtils.isNotEmpty(pDescriptors)){
            for (PropertyDescriptor pDescriptor : pDescriptors) {
                Class<?> clazz = pDescriptor.getPropertyType();
                if(clazz.isPrimitive()) {
                    clazz = PrimitiveTypeMapping.getBoxingType(clazz);
                }
                if(Comparable.class.isAssignableFrom(clazz)){
                    methodSet.add(pDescriptor.getReadMethod());
                }
            }
        }

        ComparisonChain comparisonChain = ComparisonChain.start();
        for(Method method : methodSet){
            Comparable ao = (Comparable)method.invoke(a);
            Comparable bo = (Comparable)method.invoke(b);
            if(ao instanceof Comparable && bo instanceof Comparable){
                comparisonChain = comparisonChain.compare(ao,bo);
            }
        }

        return comparisonChain.result();
    }
}
