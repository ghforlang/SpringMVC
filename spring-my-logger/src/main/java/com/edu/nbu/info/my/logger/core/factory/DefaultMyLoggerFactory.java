package com.edu.nbu.info.my.logger.core.factory;

import com.edu.nbu.info.my.logger.configuration.MyLoggerContextInitializer;
import com.edu.nbu.info.my.logger.core.MyLoggerContext;
import com.edu.nbu.info.my.logger.core.log.DefaultMyLogger;
import com.edu.nbu.info.my.logger.core.log.MyLogger;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class DefaultMyLoggerFactory implements IMyLoggerFactory{

    private MyLoggerContext loggerContext;

    public DefaultMyLoggerFactory() {
        MyLoggerContextInitializer.autoConfig();
        this.loggerContext = MyLoggerContextInitializer.getDefaultLoggerContext();
    }

    @Override
    public MyLogger createLogger(Class<?> clazz) {
        if(Objects.isNull(clazz)){
            return null;
        }
        return createLogger(clazz.getName());
    }

    @Override
    public MyLogger createLogger(String name) {
        if(StringUtils.isBlank(name)){
            return null;
        }

        MyLogger logger = loggerContext.getLogger(name,true);
        if(Objects.isNull(logger)){
            logger = newLogger(name);
            loggerContext.putLogger(name,logger);
        }
        return logger;
    }

    @Override
    public MyLogger newLogger(String name) {
        if(StringUtils.isBlank(name)){
            return null;
        }

        MyLogger logger = new DefaultMyLogger(name);
        MyLogger parentLogger = null;
        //向上寻找parentLogger
        for(int index = name.lastIndexOf("."); index >= 0 ;index = name.lastIndexOf(".",index-1)){
            String parentName = name.substring(0,index);
            parentLogger = loggerContext.getLogger(parentName,false);
            if(Objects.nonNull(parentLogger)){
                break;
            }
        }
        if(Objects.isNull(parentLogger)){
            parentLogger = loggerContext.getRootLogger();
        }
        logger.setParentLogger(parentLogger);
        logger.setLoggerContext(loggerContext);
        return logger;
    }
}
