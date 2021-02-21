package com.edu.nbu.info.my.logger.core.appender;

import com.edu.nbu.info.my.logger.core.MyLoggingEvent;

public interface MyLoggerAppender {

    /**
     * 日志输出功能定义
     * @param event
     */
    void append(MyLoggingEvent event);
}
