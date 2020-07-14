package com.nbu.edu.cn.asession.core;

import com.nbu.edu.cn.asession.core.ids.AsessionIdGenerator;
import com.nbu.edu.cn.exception.ResponseEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class MapASession implements ASession{
    /**
     * id
     */
    private final String id;
    /**
     * 创建时间戳
     */
    private long createTime;
    /**
     * 最近一次访问时间时间戳
     */
    private long lastAccessTime;
    /**
     * 最大空闲时间，单位s
     */
    private int maxInactiveInterval;

    /**
     * 所有属性
     */
    private Map<String,Object> attributes = new ConcurrentHashMap<>();

    public MapASession(){
        this(AsessionIdGenerator.genId());
    }

    public MapASession(String id){
        this.id = id;
    }

    public MapASession(ASession aSession){
        if(Objects.isNull(aSession) || StringUtils.isBlank(aSession.getASessionId())){
            throw ResponseEnum.BAD_REQUEST.newException("Session can not be null or ''!");
        }

        this.id = aSession.getASessionId();
        for(String attributeName : aSession.getAttributeNames()){
            attributes.put(attributeName,aSession.getAttribute(attributeName));
        }
        this.createTime = aSession.getCreateTime();
        this.lastAccessTime = aSession.getLastAccessTime();
    }



    @Override
    public String getASessionId() {
        return this.id;
    }

    @Override
    public Set<String> getAttributeNames() {
        return attributes.keySet();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return new HashMap<>(attributes);
    }

    @Override
    public <T> T getAttribute(String attributeName) {
        return (T)attributes.get(attributeName);
    }

    @Override
    public void setAttribute(String attributeName, Object attributeValue) {
        attributes.put(attributeName,attributeValue);
    }

    @Override
    public void removeAttribute(String attributeName) {
        attributes.remove(attributeName);
    }

    @Override
    public void delete() {
        attributes.clear();
    }

    @Override
    public void using() {
        this.lastAccessTime = System.currentTimeMillis();
    }

    @Override
    public long getCreateTime() {
        return createTime;
    }

    @Override
    public long getLastAccessTime() {
        return lastAccessTime;
    }

    @Override
    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    @Override
    public void setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }

    @Override
    public long expiredTime() {
        if(maxInactiveInterval <= 0){
            maxInactiveInterval = 0;
        }
        return lastAccessTime + TimeUnit.SECONDS.toMillis(maxInactiveInterval);
    }

    @Override
    public boolean isExpired() {
        if(maxInactiveInterval <= 0){
            return false;
        }
        return System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(maxInactiveInterval) >= lastAccessTime ;
    }
}
