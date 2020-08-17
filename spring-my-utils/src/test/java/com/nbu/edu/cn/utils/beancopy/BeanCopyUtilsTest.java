package com.nbu.edu.cn.utils.beancopy;

import com.nbu.edu.cn.utils.JackSonUtils;
import com.nbu.edu.cn.utils.model.StudentBO;
import com.nbu.edu.cn.utils.model.StudentConverter;
import com.nbu.edu.cn.utils.model.StudentDTO;

public class BeanCopyUtilsTest {

    public static void main(String[] args) {
        BeanCopyUtilsTest test = new BeanCopyUtilsTest();
        test.testTestMap();
    }

    public void testTestMap(){
        StudentBO student = StudentBO.getInstance();
        StudentDTO studentDTO = StudentConverter.convertToStudentDTO(student);
        System.out.println(JackSonUtils.toJsonString(studentDTO));
    }
}
