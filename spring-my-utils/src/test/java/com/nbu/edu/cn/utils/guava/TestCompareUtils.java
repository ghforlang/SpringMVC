package com.nbu.edu.cn.utils.guava;

import com.nbu.edu.cn.utils.model.compare.GuavaHello;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

public class TestCompareUtils {

    public static void main(String[] args) {
        try {
            testCompare();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void testCompare() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        GuavaHello gh1 = GuavaHello.builder().name("a").size(12).time(123L).build();
        GuavaHello gh2 = GuavaHello.builder().name("a").size(10).time(123L).build();
        int compareResult = CompareUtils.compare(gh1,gh2);
        System.out.println(compareResult);
    }
}
