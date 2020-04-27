package com.nbu.edu.cn.utils.date;

import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@UtilityClass
public class DateFormatUtils {

    public static final String DATE_FORMAT_24H = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_12H = "yyyy-MM-dd hh:mm:ss";

    private static final ThreadLocal<SimpleDateFormat> DEFAULT_DATE_TIME_FORMAT_24H =
            new ThreadLocal<SimpleDateFormat>(){
                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat(DATE_FORMAT_24H);
                }
            };


    /**
     * 默认24小时格式转date
     * @param dateTimeStr
     * @return
     */
    public static Date parseToDateTime(String  dateTimeStr){
        try {
            return DEFAULT_DATE_TIME_FORMAT_24H.get().parse(dateTimeStr);
        } catch (ParseException e) {
            throw new RuntimeException("dateTimeStr:" + dateTimeStr + "不符合" + DATE_FORMAT_24H + "格式",e);
        }
    }

    /**
     * 转默认24小时格式
     * @param date
     * @return
     */
   public static String parseToStr(Date date){
        return DEFAULT_DATE_TIME_FORMAT_24H.get().format(date);
   }

}
