package com.edu.nbu.info.my.logger.core.factory;

import com.edu.nbu.info.my.logger.core.log.MyLogger;

public interface IMyLoggerFactory {

    /**
     * 根据类创建logger
     * @param clazz
     * @return
     */
    public MyLogger createLogger(Class<?> clazz);

    /**
     * 创建日志
     * @param name
     * @return
     */
    public MyLogger createLogger(String name);

    /**
     * 创建日志
     * @param name
     * @return
     */
    public MyLogger newLogger(String name);
}
