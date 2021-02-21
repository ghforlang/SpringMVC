package com.edu.nbu.info.my.logger.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * 定义日志事件包含的内容,一次日志打印定义为输出的事件-Event
 */
@Getter
@Setter
public class MyLoggingEvent {

    /**
     * 日志时间戳
     */
    private long timeStamp;

    /**
     * {@link com.edu.nbu.info.my.logger.core.MyLevel}
     * @see  com.edu.nbu.info.my.logger.core.MyLevel
     * 日志级别
     */
    private MyLevel level;

    /**
     * 日志内容
     */
    private Object message;

    /**
     * 线程名
     */
    private String threadName;

    /**
     * 线程id
     */
    private long threadId;

    /**
     * 日志名称
     */
    private String loggerName;

    public MyLoggingEvent(MyLevel level, String loggerName) {
        this.level = level;
        this.loggerName = loggerName;
        this.threadName = Thread.currentThread().getName();
        this.timeStamp = System.currentTimeMillis();
        this.threadId = Thread.currentThread().getId();
    }

    @Override
    public String toString() {
        return "Mylogging-" +
                loggerName +
                "[" + level.getDesc() + "]" +
                "-" +
                threadName +
                "[" + threadId + "]" +
                ":" + (Objects.isNull(message) ? null : message.toString());
    }
}
