package com.nbu.edu.cn.utils;

import com.nbu.edu.cn.utils.date.DateFormatUtils;

import java.util.Date;

public class DateFormatUtilsTest {

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(testGetStartOfDay(date));
        System.out.println(testGetEndOfDay(date));
    }

    public static Date testGetStartOfDay(Date date){
        return DateFormatUtils.getStartOfDay(date);
    }

    public static Date testGetEndOfDay(Date date){
        return DateFormatUtils.getEndOfDay(date);
    }

}
