package com.edu.nbu.info.my.logger;

import com.edu.nbu.info.my.logger.core.MyLevel;
import com.edu.nbu.info.my.logger.core.MyLoggingEvent;

public class TestLoggingEvent {

    public static void main(String[] args) {
        MyLoggingEvent loggingEvent = new MyLoggingEvent(MyLevel.INFO,"test");
        System.out.println(loggingEvent.toString());
    }
}
