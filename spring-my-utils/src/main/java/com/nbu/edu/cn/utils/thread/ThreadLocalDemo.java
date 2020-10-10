package com.nbu.edu.cn.utils.thread;

public class ThreadLocalDemo {
    //ThreadLocal与InheritableThreadLocal区别
    private static final ThreadLocal<String> THREAD_LOCAL = new InheritableThreadLocal();
    private static final ThreadLocal<String> STRING_THREAD_LOCAL = new ThreadLocal<>();

    public static void main(String[] args) {
        THREAD_LOCAL.set("parent_value_1");
        STRING_THREAD_LOCAL.set("parent_value_2");
        testThreadLocal();
        testInheritableThreadLocal();
    }

    private static void testThreadLocal(){
        new Thread(() ->{
            System.out.println(STRING_THREAD_LOCAL.get());
        },"child1").start();
    }


    private static void testInheritableThreadLocal(){
        new Thread(() ->{
            System.out.println(THREAD_LOCAL.get());
        },"child2").start();
    }
}
