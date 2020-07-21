package com.nbu.edu.cn.asession.core.config;

public interface ASessionConfig {

    /**
     * 是否验证session的ip，默认true
     * @return
     */
    boolean isVerifyIp();

    /**
     * 是否验证aSession的userAgent
     * @return
     */
    boolean isVerifyUA();

    /**
     * 获取aSession中aSessionId的属性名
     * @return
     */
    String getASessionIdFieldName();

    /**
     * 获取ASession中verify属性名
     * @return
     */
    String getASessionVerifyFieldName();

    /**
     * 最大有效时间,默认30分钟
     * -1  永久有效
     * 0 立即失效，即单次有效
     * 正数，最长失效时间
     * @return
     */
    int getMaxInactiveInterval();
}
