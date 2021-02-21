package com.edu.nbu.info.my.logger.core.wrapper;

import com.edu.nbu.info.my.logger.core.MyLoggingEvent;
import com.edu.nbu.info.my.logger.core.appender.MyLoggerAppender;

public class MyLoggerWrapperFactory {

    public static MyLoggerWrapperFactory getInstance(){
        return InstanceHolder.factoryInstance;
    }

    public MyLoggerWrapper buildMyLoggerWrapper(MyLoggerAppender appender, MyLoggingEvent loggingEvent){
        return new MyLoggerWrapper(appender,loggingEvent);
    }

    static class InstanceHolder{
        private static MyLoggerWrapperFactory factoryInstance = new MyLoggerWrapperFactory();
    }
}
