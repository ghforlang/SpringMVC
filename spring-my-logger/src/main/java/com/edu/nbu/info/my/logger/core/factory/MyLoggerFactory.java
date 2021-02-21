package com.edu.nbu.info.my.logger.core.factory;

import com.edu.nbu.info.my.logger.core.log.MyLogger;

public class MyLoggerFactory {

    private static final IMyLoggerFactory loggerFactory = new DefaultMyLoggerFactory();

    public static IMyLoggerFactory getLoggerFactory(){
        return loggerFactory;
    }

    public static MyLogger getLogger(Class<?> clazz){
        return getLogger(clazz.getName());
    }

    public static MyLogger getLogger(String name){
        return loggerFactory.createLogger(name);
    }
}
