package com.nbu.edu.cn.asession.core.config;

public class DefaultASessionConfig implements ASessionConfig{

    /**
     * 默认验证ip
     */
    protected  boolean isVerifyIp = true;
    /**
     * 默认验证UA
     */
    protected  boolean isVerifyUA = true;
    /**
     * aSession中获取sessionId的属性名
     */
    protected  String aSessionIdFieldName = "aSession-id";
    /**
     * aSession中获取verify属性的属性名
     */
    protected String aSessionVerifyFieldName = "aSession-verify-fn";

    /**
     * 最大失效时间，默认30分钟
     */
    protected int maxInactiveInterval = 30 * 60;


    @Override
    public boolean isVerifyIp() {
        return isVerifyIp;
    }

    @Override
    public boolean isVerifyUA() {
        return isVerifyUA;
    }

    @Override
    public String getASessionIdFieldName() {
        return aSessionIdFieldName;
    }

    @Override
    public String getASessionVerifyFieldName() {
        return aSessionVerifyFieldName;
    }

    @Override
    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    public DefaultASessionConfig setIsVerifyIp(boolean isVerifyIp){
        this.isVerifyIp = isVerifyIp;
        return this;
    }


    public DefaultASessionConfig setIsVerifyUA(boolean isVerifyUA){
        this.isVerifyUA = isVerifyUA;
        return this;
    }

    public void setaSessionIdFieldName(String aSessionIdFieldName) {
        this.aSessionIdFieldName = aSessionIdFieldName;
    }

    public void setaSessionVerifyFieldName(String aSessionVerifyFieldName) {
        this.aSessionVerifyFieldName = aSessionVerifyFieldName;
    }

    public void setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }
}
