package com.nbu.edu.cn.utils.beancopy;

import com.nbu.edu.cn.utils.JackSonUtils;
import com.nbu.edu.cn.utils.model.StudentBO;
import com.nbu.edu.cn.utils.beancopy.orika.StudentBeanConverter;
import com.nbu.edu.cn.utils.model.StudentDTO;

public class StudentBeanConverterTest {

    public static void main(String[] args) {
        testTestMap();
    }

    public static void testTestMap(){
//        System.setProperty("ma.glasnost.orika.GeneratedSourceCode.writeSourceFiles","true");
//        System.setProperty("ma.glasnost.orika.GeneratedSourceCode.writeClassFiles","true");
        StudentBO student = StudentBO.getInstance();
        StudentDTO studentDTO = StudentBeanConverter.convertToStudentDTO(student);
        System.out.println(JackSonUtils.toJsonString(studentDTO));
    }
}
