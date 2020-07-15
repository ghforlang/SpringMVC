package com.nbu.edu.cn.asession.core.config;

public abstract class AbstractASessionStoreConfig implements ASessionStoreConfig {
    /**
     * 失效
     */
    protected String expireASessionKey = "expire:aSession";

    /**
     * session key前缀
     */
    protected String aSessionKeyPrefix = "aSession:";

    /**
     * session 属性前缀
     */
    protected String aSessionAttrKeyPrefix = "attr:";

    /**
     * 最大空闲时间间隔
     */
    protected int maxInactiveInterval = 30 * 60;

    /**
     * 获取失效aSessionKey
     * @return
     */
    public String getExpireASessionKey(){
        return expireASessionKey;
    }

    public String getASessionKeyPrefix(){
        return aSessionKeyPrefix;
    }

    public String getaSessionAttrKeyPrefix(){
        return aSessionAttrKeyPrefix;
    }

    public int getMaxInactiveInterval(){
        return maxInactiveInterval;
    }


}
