package com.nbu.edu.info.router.demo;

import com.nbu.edu.info.router.demo.router.Router;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RouterTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-router-context.xml");
        Router router = (Router) context.getBean("router");
        router.getRouterBean();
        System.out.println(router);
        System.out.println(router.getRouterBean());
    }
}
