package com.nbu.edu.cn.utils.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class StudentBO {
    private String id;
    private String name;
    private LocalDateTime birthDay;

    public static final StudentBO getInstance(){
        StudentBO student = new StudentBO();
        student.setId("1234");
        student.setName("张三");
        student.setBirthDay(LocalDateTime.now());
        return student;
    }
}
