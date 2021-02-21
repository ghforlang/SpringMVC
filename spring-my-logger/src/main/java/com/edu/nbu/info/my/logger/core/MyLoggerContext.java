package com.edu.nbu.info.my.logger.core;


import com.edu.nbu.info.my.logger.core.log.DefaultMyLogger;
import com.edu.nbu.info.my.logger.core.log.MyLogger;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MyLoggerContext {

    /**
     * 声明根logger
     */
    private MyLogger rootLogger = new DefaultMyLogger("root");

    /**
     * 缓存logger
     */
    private Map<String,MyLogger> CACHED_LOGGERS = new ConcurrentHashMap<>();

    public void putLogger(MyLogger logger){
        if(Objects.nonNull(logger)){
            CACHED_LOGGERS.putIfAbsent(logger.getName(),logger);
        }
    }

    public void putLogger(String name,MyLogger logger){
        if(StringUtils.isNotBlank(name) && Objects.nonNull(logger)){
            CACHED_LOGGERS.putIfAbsent(name,logger);
        }
    }

    public MyLogger getRootLogger(){
        return rootLogger;
    }

    public MyLogger getLogger(String name,boolean defaultRoot){
        if(StringUtils.isBlank(name)){
            if(defaultRoot){
                return rootLogger;
            }
            return null;
        }

        MyLogger logger = CACHED_LOGGERS.get(name);
        if(Objects.nonNull(logger)){
            return logger;
        }

        return defaultRoot ? rootLogger: null;
    }
}
