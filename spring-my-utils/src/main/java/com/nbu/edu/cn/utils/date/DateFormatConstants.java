package com.nbu.edu.cn.utils.date;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatConstants {

    public static final String DATETIME_FORMAT_24H = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_FORMAT_12H = "yyyy-MM-dd hh:mm:ss";
    public static final String DATETIME_FORMAT_START_OF_DAY = "yyyy-MM-dd 00:00:00";
    public static final String DATETIME_FORMAT_END_OF_DAY = "yyyy-MM-dd 23:59:59";

    public static final DateTimeFormatter DATETIME_24H_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT_24H, Locale.CHINA);
    public static final DateTimeFormatter DATETIME_12H_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT_12H, Locale.CHINA);
    public static final DateTimeFormatter DATETIME_START_OF_DAY = DateTimeFormatter.ofPattern(DATETIME_FORMAT_START_OF_DAY,Locale.CHINA);
    public static final DateTimeFormatter DATETIME_END_OF_DAY = DateTimeFormatter.ofPattern(DATETIME_FORMAT_END_OF_DAY,Locale.CHINA);

}
