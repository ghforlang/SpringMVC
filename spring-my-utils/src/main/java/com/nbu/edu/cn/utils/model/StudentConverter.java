package com.nbu.edu.cn.utils.model;

import com.nbu.edu.cn.utils.beancopy.BaseConverter;
import com.nbu.edu.cn.utils.beancopy.SimpleBeanCopyUtils;
import com.nbu.edu.cn.utils.beancopy.CopyTriplet;
import com.nbu.edu.cn.utils.beancopy.customconverter.LocalDateTimeFormatConverter;
import lombok.experimental.UtilityClass;
import ma.glasnost.orika.CustomConverter;

import java.util.*;

@UtilityClass
public class StudentConverter extends BaseConverter {

    private static  List<CopyTriplet<String,String,String>> fieldsAndConverterIds = new ArrayList<>();
    private static final Map<String, CustomConverter> convertMap = new HashMap<>();

    static{
        //初始化转换器map
        convertMap.put("dateTimeToFormatStringConverter",new LocalDateTimeFormatConverter());
        //初始化field-转换器map
        fieldsAndConverterIds.add(new CopyTriplet<>("birthDay","birth","dateTimeToFormatStringConverter"));
        registerFieldConverter(convertMap);
        registerFields(StudentBO.class,StudentDTO.class,fieldsAndConverterIds);
    }

    public static final StudentDTO convertToStudentDTO(StudentBO studentBO){
        return SimpleBeanCopyUtils.map(studentBO, StudentDTO.class);
    }
}
