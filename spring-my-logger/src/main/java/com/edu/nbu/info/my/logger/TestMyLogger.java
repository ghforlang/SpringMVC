package com.edu.nbu.info.my.logger;

import com.edu.nbu.info.my.logger.core.appender.ConsoleAppender;
import com.edu.nbu.info.my.logger.core.appender.MyLoggerAppender;
import com.edu.nbu.info.my.logger.core.log.DefaultMyLogger;
import com.edu.nbu.info.my.logger.core.log.MyLogger;

public class TestMyLogger {

    public static void main(String[] args) {
        MyLoggerAppender appender = new ConsoleAppender();
        MyLogger parentLogger = new DefaultMyLogger("parentTest");
        MyLogger logger = new DefaultMyLogger("loggerTest",appender,parentLogger);
        logger.debug("debug test");
        logger.info("info test");
        logger.trace("trace test");
        logger.error("error test");
        logger.warn("warn test");
    }
}
