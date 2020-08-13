package com.nbu.edu.cn.utils.date;

import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@UtilityClass
public class DateFormatUtils {



    private static final ThreadLocal<SimpleDateFormat> DEFAULT_DATE_TIME_FORMAT_24H =
            new ThreadLocal<SimpleDateFormat>(){
                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat(DateFormatConstants.DATETIME_FORMAT_24H);
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
            throw new RuntimeException("dateTimeStr:" + dateTimeStr + "不符合" + DateFormatConstants.DATETIME_FORMAT_24H + "格式",e);
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

   public static Date getStartOfDay(Date date){
       Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
       if(Objects.nonNull(date)){
           calendar.setTime(date);
       }
       calendar.set(Calendar.HOUR_OF_DAY,0);
       calendar.set(Calendar.MINUTE,0);
       calendar.set(Calendar.SECOND,0);
       calendar.set(Calendar.MILLISECOND,0);
       return calendar.getTime();
   }

   public static Date getEndOfDay(Date date){
       Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
       if(Objects.nonNull(date)){
           calendar.setTime(date);
       }
       calendar.set(Calendar.HOUR_OF_DAY,23);
       calendar.set(Calendar.MINUTE,59);
       calendar.set(Calendar.SECOND,59);
       calendar.set(Calendar.MILLISECOND,999);
       return calendar.getTime();
   }




}
