package com.nbu.edu.cn.asession.core.config;

public class ASessionRedisConfig extends AbstractASessionStoreConfig {


    public ASessionStoreConfig setExpireASessionKey(String expireASessionKey){
        this.expireASessionKey = expireASessionKey;
        return this;
    }

    @Override
    public ASessionStoreConfig setASessionKeyPrefix(String aSessionKeyPrefix) {
        this.aSessionKeyPrefix = aSessionKeyPrefix;
        return this;
    }

    @Override
    public ASessionStoreConfig setASessionAttrKeyPrefix(String aSessionAttrKeyPrefix) {
        this.aSessionAttrKeyPrefix = aSessionAttrKeyPrefix;
        return this;
    }

    @Override
    public ASessionStoreConfig setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
        return this;
    }
}
