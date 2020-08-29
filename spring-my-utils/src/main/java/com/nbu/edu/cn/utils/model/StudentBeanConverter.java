package com.nbu.edu.cn.utils.model;

import com.nbu.edu.cn.utils.beancopy.CopyTriplet;
import com.nbu.edu.cn.utils.beancopy.OrikaBase;
import com.nbu.edu.cn.utils.beancopy.OrikaConverterEnum;
import com.nbu.edu.cn.utils.beancopy.SimpleBeanCopyUtils;
import com.nbu.edu.cn.utils.beancopy.customconverter.BaseConverter;
import com.nbu.edu.cn.utils.model.orika.CourseAndScoreBO;
import com.nbu.edu.cn.utils.model.orika.CourseAndScoreDTO;
import com.nbu.edu.cn.utils.model.orika.StudentBO;
import com.nbu.edu.cn.utils.model.orika.StudentDTO;
import lombok.experimental.UtilityClass;
import ma.glasnost.orika.BoundMapperFacade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class StudentBeanConverter extends OrikaBase {

    private static  List<CopyTriplet<String,String,String>> studentFieldsAndConverterIds = new ArrayList<>();
    private static List<CopyTriplet<String,String,String>> courseFieldsAndConverterIds = new ArrayList<>();

    private static final Map<String, BaseConverter> convertMap = new HashMap<>();
    private static final BoundMapperFacade<StudentBO,StudentDTO> boundMapperFacade;

    static{
        //初始化转换器map
        convertMap.put(OrikaConverterEnum.LOCALDATETIME_2_FORMAT_STR.getConvertId(),OrikaConverterEnum.LOCALDATETIME_2_FORMAT_STR.getConverter());
        convertMap.put(OrikaConverterEnum.TYPE_2_NAME.getConvertId(),OrikaConverterEnum.TYPE_2_NAME.getConverter());
        registerOrikaConverter(convertMap);

        //初始化field-转换器map
        studentFieldsAndConverterIds.add(new CopyTriplet<>("birthDay","birth",OrikaConverterEnum.LOCALDATETIME_2_FORMAT_STR.getConvertId()));
        studentFieldsAndConverterIds.add(new CopyTriplet<>("courseAndScoreList","courseAndScoreDTOList",null));
        courseFieldsAndConverterIds.add(new CopyTriplet<>("courseType","courseName",OrikaConverterEnum.TYPE_2_NAME.getConvertId()));

        registerFields(StudentBO.class, StudentDTO.class,studentFieldsAndConverterIds);
        registerFields(CourseAndScoreBO.class, CourseAndScoreDTO.class,courseFieldsAndConverterIds);
        boundMapperFacade = buildBoundMapperFacade(StudentBO.class,StudentDTO.class);
    }

    public static final StudentDTO convertToStudentDTO(StudentBO studentBO){
        return SimpleBeanCopyUtils.map(studentBO, StudentDTO.class);
    }
}
