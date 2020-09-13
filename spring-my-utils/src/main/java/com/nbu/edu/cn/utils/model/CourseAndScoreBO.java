package com.nbu.edu.cn.utils.model;

import com.nbu.edu.cn.utils.context.ContextAvailable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CourseAndScoreBO implements ContextAvailable {

    private Integer courseType;
    private String score;

    public static CourseAndScoreBO getInstance(){
        CourseAndScoreBO scoreBO = new CourseAndScoreBO();
        scoreBO.setCourseType(1);
        scoreBO.setScore("200");
        return scoreBO;
    }
}
