package com.edu.nbu.info.my.logger.core.log;

import com.edu.nbu.info.my.logger.core.MyLoggerContext;
import com.edu.nbu.info.my.logger.core.wrapper.MyLoggerWrapper;

/**
 * 日志打印入口，提供日志打印方法
 */
public interface MyLogger {

    /**
     * trace级别日志打印
     */
    void trace(String message);

    /**
     * info级别日志打印
     * @param message
     */
    void info(String message);

    /**
     * debug 级别日志打印
     * @param message
     */
    void debug(String message);

    /**
     * warn级别日志打印
     * @param message
     */
    void warn(String message);

    /**
     * error级别日志打印
     * @param message
     */
    void error(String message);

    /**
     * 获取名称
     * @return
     */
    String getName();

    /**
     * 引入层次继承关系
     * @return
     */
    MyLogger getParent();

    /**
     * 获取包装类
     * @return
     */
    MyLoggerWrapper getWrapper();

    /**
     * 设置父级logger
     * @param logger
     */
    void setParentLogger(MyLogger logger);

    /**
     * 设置loggercontext
     * @param loggerContext
     */
    void setLoggerContext(MyLoggerContext loggerContext);
}
