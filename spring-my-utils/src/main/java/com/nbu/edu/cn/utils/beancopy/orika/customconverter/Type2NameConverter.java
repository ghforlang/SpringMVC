package com.nbu.edu.cn.utils.beancopy.orika.customconverter;

import com.nbu.edu.cn.utils.model.CourseEnum;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

import java.util.Objects;

public class Type2NameConverter extends BaseConverter<Integer,String> {

    private static class Singleton{
        private static Type2NameConverter INSTANCE = new Type2NameConverter();
    }

    @Override
    public String convertTo(Integer source, Type<String> destinationType, MappingContext mappingContext) {
        return Objects.isNull(CourseEnum.getByType(source)) ? "未知" : CourseEnum.getByType(source).getCourseName();
    }

    @Override
    public Integer convertFrom(String source, Type<Integer> destinationType, MappingContext mappingContext) {
        return Objects.isNull(CourseEnum.getByName(source)) ? null : CourseEnum.getByName(source).getCourseType();
    }

    public static Type2NameConverter getInstance(){
        return Singleton.INSTANCE;
    }
}
