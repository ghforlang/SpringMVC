package com.nbu.edu.cn.utils;

import com.nbu.edu.cn.model.pair.DateSpanPair;
import com.nbu.edu.cn.model.pair.TimeStampSpanPair;
import com.nbu.edu.cn.utils.date.DateFormatUtils;
import com.nbu.edu.cn.utils.spanpair.TimeSpanPairUtils;
import org.junit.Test;

import java.util.Date;

public class TimeSpanUtilsTest {
    private static String fromDateStr = "2020-04-27 15:22:00";
    private static String toDateStr = "2021-04-27 15:22:20";
    private static String fromDateStr2 = "2020-04-27 15:21:21";
    private static String toDateStr2 = "2020-04-27 15:22:50";

    //phrase1
    private Date from1 = DateFormatUtils.parseToDateTime(fromDateStr);
    private Date to1 = DateFormatUtils.parseToDateTime(toDateStr);
    private Long timeStampFrom1 = from1.getTime();
    private Long timeStampTo1 = to1.getTime();

    //phrase2
    private Date from2 = DateFormatUtils.parseToDateTime(fromDateStr2);
    private Date to2 = DateFormatUtils.parseToDateTime(toDateStr2);
    private Long timeStampFrom2 = from2.getTime();
    private Long timeStampTo2 = to2.getTime();


    @Test
    public void testHasIntersectionForDate(){
        DateSpanPair pair1 = new DateSpanPair(from1,to1);
        DateSpanPair pair2 = new DateSpanPair(from2,to2);
        System.out.println(TimeSpanPairUtils.hasIntersectionForDate(pair1,pair2));
    }

    @Test
    public void testHasIntersectionForTimeStamp(){
        TimeStampSpanPair pair1 = new TimeStampSpanPair(timeStampFrom1,timeStampTo1);
        TimeStampSpanPair pair2 = new TimeStampSpanPair(timeStampFrom2,timeStampTo2);
        System.out.println(TimeSpanPairUtils.hasInterSectionForTimeStamp(pair1,pair2));
    }

    @Test
    public void testTimeSpanPair(){
        DateSpanPair pair1 = new DateSpanPair(from1,to1);
        DateSpanPair pair2 = new DateSpanPair(from2,null);
        System.out.println(pair1.hasInterSection(pair2));
    }

    @Test
    public void testTimeStampPair(){
        TimeStampSpanPair pair1 = new TimeStampSpanPair(timeStampFrom1,timeStampTo1);
        TimeStampSpanPair pair2 = new TimeStampSpanPair(timeStampFrom2,null);
        System.out.println(pair1.hasInterSection(pair2));
    }

    @Test
    public void testDateCross(){
        DateSpanPair pair1 = new DateSpanPair(from1,to1);
        System.out.println("isCrossDay: " + pair1.isCrossDay());
        System.out.println("isCrossMonth: " + pair1.isCrossMonth());
        System.out.println("isCrossYear: " + pair1.isCrossYear());
    }

    @Test
    public void testTimeStampCross(){
        TimeStampSpanPair pair1 = new TimeStampSpanPair(timeStampFrom1,timeStampTo1);
        System.out.println("isCrossDay: " + pair1.isCrossDay());
        System.out.println("isCrossMonth: " + pair1.isCrossMonth());
        System.out.println("isCrossYear: " + pair1.isCrossYear());
    }

}
