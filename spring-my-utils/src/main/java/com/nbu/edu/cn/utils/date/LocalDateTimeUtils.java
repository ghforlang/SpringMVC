package com.nbu.edu.cn.utils.date;

import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@UtilityClass
public class LocalDateTimeUtils {
    /**
     * tips:
     * Instant : 瞬时时间
     * LocalDate : 本地日期，不包含时间，格式：yyyy-MM-dd
     * LocalTime : 本地时间，不包含日期，格式：yyyy-MM-dd HH:mm:ss.SSS
     * LocalDateTime : 组合了日期和时间，但不包含时差和时区信息
     * ZoneDateTime: 最完整的日期时间，包含时区和相对于UTC,或者格林威治的时差
     */


    /**
     * 转换成date
     * @param localDateTime
     * @return
     */
    public static Date toDate(LocalDateTime localDateTime){
        if(Objects.isNull(localDateTime)){
            return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * date转localDateTime
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date date){
        if(Objects.isNull(date)){
            return LocalDateTime.now();
        }
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
