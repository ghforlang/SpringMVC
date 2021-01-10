package com.edu.nbu.demo.begin;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-bean-config.xml");
        Person person = (Person)applicationContext.getBean("person");
        Person person1 = (Person)applicationContext.getBean("person");
        System.out.println(person);
        System.out.println(person1);
        System.out.println(ToStringBuilder.reflectionToString(person, ToStringStyle.SIMPLE_STYLE));

        person.setAge(10);
        person.setName("zhangsan");
        person.setPassword("zhangsan10");
        System.out.println(person);

    }
}
