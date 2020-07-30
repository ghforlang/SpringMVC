package com.nbu.edu.cn.asession.core.config;

public interface ASessionStoreConfig {

    /**
     * 通用参数设置
     * @param expireASessionKey
     * @return
     */
    ASessionStoreConfig setExpireASessionKey(String expireASessionKey);

    /**
     * 通用参数设置
     * @param aSessionKeyPrefix
     * @return
     */
    ASessionStoreConfig setASessionKeyPrefix(String aSessionKeyPrefix);

    /**
     * 通用参数设置
     * @param aSessionAttrKeyPrefix
     * @return
     */
    ASessionStoreConfig setASessionAttrKeyPrefix(String aSessionAttrKeyPrefix);

    /**
     * 通用参数设置
     * @param maxInactiveInterval
     * @return
     */
    ASessionStoreConfig setMaxInactiveInterval(int maxInactiveInterval);
}
