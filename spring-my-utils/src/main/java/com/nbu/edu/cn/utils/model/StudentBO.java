package com.nbu.edu.cn.utils.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StudentBO {
    private String id;
    private String name;
    private LocalDateTime birthDay;

    private List<CourseAndScoreBO> courseAndScoreList;

    public static final StudentBO getInstance(){
        StudentBO student = new StudentBO();
        student.setId("1234");
        student.setName("张三");
        student.setBirthDay(LocalDateTime.now());

        List<CourseAndScoreBO> courseAndScoreBOS = new ArrayList<>();
        CourseAndScoreBO courseAndScoreBO = new CourseAndScoreBO();
        courseAndScoreBO.setCourseType(1);
        courseAndScoreBO.setScore("124.5");
        courseAndScoreBOS.add(courseAndScoreBO);

        CourseAndScoreBO courseAndScoreBO1 = new CourseAndScoreBO();
        courseAndScoreBO1.setScore("130");
        courseAndScoreBO1.setCourseType(2);
        courseAndScoreBOS.add(courseAndScoreBO1);

        student.setCourseAndScoreList(courseAndScoreBOS);
        return student;
    }
}
