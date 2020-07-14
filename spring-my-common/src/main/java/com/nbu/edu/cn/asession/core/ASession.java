package com.nbu.edu.cn.asession.core;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public interface ASession extends Serializable {

    /**
     * 获取asessionId
     * @return
     */
    String getASessionId();

    /**
     * 获取所有attributeName
     * @return
     */
    Set<String> getAttributeNames();

    /**
     * 获取全部attribute
     * @return
     */
    Map<String,Object> getAttributes();

    /**
     * 获取attribute
     * @param attributeName
     * @param <T>
     * @return
     */
    <T> T getAttribute(String attributeName);

    /**
     * 设置属性
     * @param attributeName
     * @param attributeValue
     */
    void setAttribute(String attributeName,Object attributeValue);

    /**
     * 删除属性
     * @param attributeName
     */
    void removeAttribute(String attributeName);

    /**
     * 删除asession
     */
    void delete();

    /**
     * 正在使用asession
     */
    void using();

    /**
     * 创建时间
     * @return
     */
    long getCreateTime();

    /**
     * 最后访问时间
     * @return
     */
    long getLastAccessTime();

    /**
     * 获取最大空闲时长，单位s
     * @return
     */
    int getMaxInactiveInterval();

    /**
     * 设置最大空闲时长
     * @param maxInactiveInterval
     */
    void setMaxInactiveInterval(int maxInactiveInterval);

    /**
     * asession失效时间戳
     * @return
     */
    long expiredTime();


    /**
     * 是否已失效
     * @return
     */
    boolean isExpired();
}
