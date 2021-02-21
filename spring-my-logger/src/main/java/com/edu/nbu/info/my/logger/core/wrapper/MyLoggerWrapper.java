package com.edu.nbu.info.my.logger.core.wrapper;

import com.edu.nbu.info.my.logger.constants.MyLoggerConstants;
import com.edu.nbu.info.my.logger.core.MyLoggingEvent;
import com.edu.nbu.info.my.logger.core.appender.MyLoggerAppender;

public class MyLoggerWrapper {

    /**
     * 日志输出器
     */
    private MyLoggerAppender appender;

    /**
     * 日志事件
     */
    private MyLoggingEvent loggingEvent;

    /**
     * 默认有效级别
     */
    private int effectiveLevelInt = MyLoggerConstants.EFFECTIVE_LEVEL_VALUE;

    public MyLoggerWrapper(MyLoggerAppender appender, MyLoggingEvent loggingEvent) {
        this.appender = appender;
        this.loggingEvent = loggingEvent;
    }

    public void log(String message){
        if(loggingEvent.getLevel().getLevel() > effectiveLevelInt){
            loggingEvent.setMessage(message);
            appender.append(loggingEvent);
        }

    }

    public void setAppender(MyLoggerAppender appender) {
        this.appender = appender;
    }

    public void setLoggingEvent(MyLoggingEvent loggingEvent) {
        this.loggingEvent = loggingEvent;
    }

    public MyLoggingEvent getLoggingEvent() {
        return loggingEvent;
    }

    public MyLoggerAppender getAppender() {
        return appender;
    }
}
