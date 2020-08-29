package com.nbu.edu.cn.utils.model.orika;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public enum CourseEnum {
    YUWEN(1,"语文"),SHUXUE(2,"数学");

    private Integer courseType;
    private String courseName;

    CourseEnum(Integer courseType, String courseName) {
        this.courseType = courseType;
        this.courseName = courseName;
    }

    public static CourseEnum getByType(Integer type){
        if(Objects.isNull(type)){
            return null;
        }

        for(CourseEnum courseEnum : CourseEnum.values()){
            if(courseEnum.courseType.equals(type)){
                return courseEnum;
            }
        }
        return null;
    }

    public static CourseEnum getByName(String courseName){
        if(StringUtils.isNotEmpty(courseName)){
            return null;
        }

        for(CourseEnum courseEnum : CourseEnum.values()){
            if(courseEnum.courseName.equals(courseName)){
                return courseEnum;
            }
        }
        return null;
    }


    public Integer getCourseType() {
        return courseType;
    }

    public String getCourseName() {
        return courseName;
    }
}
