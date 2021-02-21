package com.edu.nbu.info.my.logger.core;

public enum MyLevel {
    ERROR(40000,"ERROR"),
    WARN(30000,"WARN"),
    INFO(20000,"INFO"),
    DEBUG(10000,"DEBUG"),
    TRACE(5000,"TRACE");


    /**
     * 日志级别值
     */
    private int level;

    /**
     * 日志级别描述
     */
    private String desc;

    MyLevel(int level, String desc) {
        this.level = level;
        this.desc = desc;
    }

    public int getLevel() {
        return level;
    }

    public String getDesc() {
        return desc;
    }
}
