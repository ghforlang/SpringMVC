package com.edu.nbu.info.my.logger.core.log;

import com.edu.nbu.info.my.logger.core.MyLevel;
import com.edu.nbu.info.my.logger.core.MyLoggerContext;
import com.edu.nbu.info.my.logger.core.MyLoggingEvent;
import com.edu.nbu.info.my.logger.core.appender.ConsoleAppender;
import com.edu.nbu.info.my.logger.core.appender.MyLoggerAppender;
import com.edu.nbu.info.my.logger.core.wrapper.MyLoggerWrapper;
import com.edu.nbu.info.my.logger.core.wrapper.MyLoggerWrapperFactory;

import java.util.Objects;

public class DefaultMyLogger implements MyLogger{

    /**
     * 名称
     */
    private String name;

    /**
     * 日志输出器
     */
    private MyLoggerAppender appender;

    /**
     * 默认日志级别TRACE
     */
    private MyLevel defaultLevel = MyLevel.TRACE;

    /**
     * 事件包装器
     */
    private MyLoggerWrapper wrapper;

    /**
     * 引入logger继承关系，加入日志层次
     */
    private MyLogger parentLogger;

    /**
     * loggerContext
     */
    private MyLoggerContext loggerContext;

    public DefaultMyLogger(String name) {
        this(name,new ConsoleAppender(),null);
    }

    public DefaultMyLogger(String name, MyLoggerAppender appender,MyLogger parentLogger) {
        this.name = name;
        this.appender = appender;
        this.parentLogger = parentLogger;
        wrapper = MyLoggerWrapperFactory.getInstance().buildMyLoggerWrapper(appender, new MyLoggingEvent(defaultLevel,name));
    }

    @Override
    public void trace(String message) {
        for(MyLogger current = this; Objects.nonNull(current) ; current = current.getParent()){
            wrapper = current.getWrapper();
            if(wrapper.getAppender() == null){
                continue;
            }
            wrapper.log(message);
            break;
        }
    }

    @Override
    public void info(String message) {
        wrapper = MyLoggerWrapperFactory.getInstance().buildMyLoggerWrapper(appender, new MyLoggingEvent(MyLevel.INFO,name));
        for(MyLogger current = this; Objects.nonNull(current) ; current = current.getParent()){
            wrapper = current.getWrapper();
            if(wrapper.getAppender() == null){
                continue;
            }
            wrapper.log(message);
            break;
        }
    }

    @Override
    public void debug(String message) {
        wrapper = MyLoggerWrapperFactory.getInstance().buildMyLoggerWrapper(appender, new MyLoggingEvent(MyLevel.DEBUG,name));
        for(MyLogger current = this; Objects.nonNull(current) ; current = current.getParent()){
            wrapper = current.getWrapper();
            if(wrapper.getAppender() == null){
                continue;
            }
            wrapper.log(message);
            break;
        }
    }

    @Override
    public void warn(String message) {
        wrapper = MyLoggerWrapperFactory.getInstance().buildMyLoggerWrapper(appender, new MyLoggingEvent(MyLevel.WARN,name));
        for(MyLogger current = this; Objects.nonNull(current) ; current = current.getParent()){
            wrapper = current.getWrapper();
            if(wrapper.getAppender() == null){
                continue;
            }
            wrapper.log(message);
            break;
        }
    }

    @Override
    public void error(String message) {
        wrapper = MyLoggerWrapperFactory.getInstance().buildMyLoggerWrapper(appender, new MyLoggingEvent(MyLevel.ERROR,name));
        for(MyLogger current = this; Objects.nonNull(current) ; current = current.getParent()){
            wrapper = current.getWrapper();
            if(wrapper.getAppender() == null){
                continue;
            }
            wrapper.log(message);
            break;
        }

    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAppender(MyLoggerAppender appender) {
        this.appender = appender;
    }

    @Override
    public MyLogger getParent() {
        return parentLogger;
    }

    @Override
    public void setParentLogger(MyLogger logger) {
        this.parentLogger = logger;
    }

    @Override
    public void setLoggerContext(MyLoggerContext loggerContext) {
        this.loggerContext = loggerContext;
    }

    public MyLoggerWrapper getWrapper() {
        return wrapper;
    }
}
