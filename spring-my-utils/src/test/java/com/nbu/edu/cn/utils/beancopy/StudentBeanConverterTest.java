package com.nbu.edu.cn.utils.beancopy;

import com.nbu.edu.cn.utils.JackSonUtils;
import com.nbu.edu.cn.utils.model.orika.StudentBO;
import com.nbu.edu.cn.utils.model.StudentBeanConverter;
import com.nbu.edu.cn.utils.model.orika.StudentDTO;

public class StudentBeanConverterTest {

    public static void main(String[] args) {
        testTestMap();
    }

    public static void testTestMap(){
        StudentBO student = StudentBO.getInstance();
        StudentDTO studentDTO = StudentBeanConverter.convertToStudentDTO(student);
        System.out.println(JackSonUtils.toJsonString(studentDTO));
    }
}
