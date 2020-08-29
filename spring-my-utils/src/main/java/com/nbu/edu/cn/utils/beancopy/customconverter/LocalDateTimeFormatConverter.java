package com.nbu.edu.cn.utils.beancopy.customconverter;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeFormatConverter extends BaseConverter<LocalDateTime,String> {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");

    private static class Singleton{
        private static LocalDateTimeFormatConverter INSTANCE = new LocalDateTimeFormatConverter();
    }

    @Override
    public String convertTo(LocalDateTime source, Type<String> destinationType, MappingContext mappingContext) {
        return source.format(dateTimeFormatter);
    }

    @Override
    public LocalDateTime convertFrom(String source, Type<LocalDateTime> destinationType, MappingContext mappingContext) {
        return LocalDateTime.parse(source,dateTimeFormatter);
    }

    public static LocalDateTimeFormatConverter getInstance(){
        return Singleton.INSTANCE;
    }
}
