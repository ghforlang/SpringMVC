package com.nbu.edu.cn.utils.beancopy;

import com.nbu.edu.cn.utils.beancopy.customconverter.BaseConverter;
import com.nbu.edu.cn.utils.beancopy.customconverter.LocalDateTimeFormatConverter;
import com.nbu.edu.cn.utils.beancopy.customconverter.example.Type2NameConverter;

public enum OrikaConverterEnum {
    TYPE_2_NAME("type2NameConverter", Type2NameConverter.getInstance()),
    LOCALDATETIME_2_FORMAT_STR("localDateTime2FormatStringConverter", LocalDateTimeFormatConverter.getInstance());

    private String convertId;
    private BaseConverter converter;

    OrikaConverterEnum(String convertId, BaseConverter converter) {
        this.convertId = convertId;
        this.converter = converter;
    }

    public String getConvertId() {
        return convertId;
    }

    public BaseConverter getConverter() {
        return converter;
    }
}
