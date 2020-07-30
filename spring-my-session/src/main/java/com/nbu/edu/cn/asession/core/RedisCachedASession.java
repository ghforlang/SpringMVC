package com.nbu.edu.cn.asession.core;

import com.nbu.edu.cn.asession.common.Constants;
import com.nbu.edu.cn.asession.core.config.ASessionRedisConfig;
import com.nbu.edu.cn.asession.core.config.AbstractASessionStoreConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RedisCachedASession implements CachedASession {

    /**
     * redis的template
     */
    private RedisTemplate redisTemplate;
    /**
     * sessionId
     */
    private String aSessionId;

    private AbstractASessionStoreConfig aSessionStoreConfig;

    /**
     * session 操作
     */
    private BoundHashOperations<String,String,Object> aSessionOps;

    /**
     * 存放session过期时间
     */
    private BoundZSetOperations<String,String> aSessionExpireOps;

    /**
     * 创建时间戳
     */
    private long createTime;

    /**
     * 最后一次访问时间戳
     */
    private long lastAccessedTime;

    public RedisCachedASession(){super();}


    public RedisCachedASession(RedisTemplate redisTemplate, ASessionRedisConfig redisConfig, String aSessionId){
        this.redisTemplate = redisTemplate;
        this.aSessionStoreConfig = redisConfig;
        this.aSessionId = aSessionId;
        this.aSessionOps = redisTemplate.boundHashOps(redisConfig.getASessionKeyPrefix().concat(aSessionId));
        this.aSessionExpireOps = redisTemplate.boundZSetOps(redisConfig.getExpireASessionKey());
        long now = System.currentTimeMillis();
        if(aSessionOps.size() > 0){
            this.createTime = (long)aSessionOps.get(Constants.CREATE_TIME);
            if(this.createTime > now){
                log.warn("aSession:{}的创建时间{}有误",aSessionId,this.createTime);
                this.createTime = now;
            }
        }else{
            this.createTime = now;
        }
        aSessionOps.put(Constants.CREATE_TIME,createTime);
    }





    @Override
    public String getASessionId() {
        return aSessionId;
    }

    @Override
    public Set<String> getAttributeNames() {
        Set<String> hKeys = aSessionOps.keys();
        Set<String> result = new HashSet<>(hKeys.size());
        int index = aSessionStoreConfig.getaSessionAttrKeyPrefix().length();
        for(String hKey : hKeys){
            if(hKey.startsWith(aSessionStoreConfig.getaSessionAttrKeyPrefix())){
                result.add(hKey.substring(index));
            }
        }
        return result;
    }

    @Override
    public Map<String, Object> getAttributes() {
        Map<String,Object> allAttr = aSessionOps.entries();
        Map<String,Object> result = new HashMap<>(allAttr.size());
        int beginIndex = aSessionStoreConfig.getaSessionAttrKeyPrefix().length();
        allAttr.forEach((key,obj) ->{
            if(key.startsWith(aSessionStoreConfig.getaSessionAttrKeyPrefix())){
                result.put(key.substring(beginIndex),obj);
            }
        });
        return result;
    }

    @Override
    public <T> T getAttribute(String attributeName) {
        try{
            return (T)aSessionOps.get(aSessionStoreConfig.getaSessionAttrKeyPrefix().concat(attributeName));
        }catch (Exception e){
            log.error("查询attr错误:" + aSessionId + "|" + attributeName);
            return null;
        }
    }

    @Override
    public void setAttribute(String attributeName, Object attributeValue) {
        try{
            aSessionOps.put(aSessionStoreConfig.getaSessionAttrKeyPrefix().concat(attributeName),attributeValue);
        }catch (Exception e){
            log.error("设置attr错误:" + aSessionId + "|" + attributeName);
        }
    }

    @Override
    public void removeAttribute(String attributeName) {
        try{
            aSessionOps.delete(aSessionStoreConfig.getaSessionAttrKeyPrefix().concat(attributeName));
        }catch (Exception e){
            log.error("删除attr错误:" + aSessionId + "|" + attributeName);
        }
    }

    @Override
    public void delete() {
        redisTemplate.delete(aSessionStoreConfig.getASessionKeyPrefix().concat(aSessionId));
        aSessionExpireOps.remove(aSessionId);
        this.redisTemplate = null;
        this.aSessionExpireOps = null;
        this.aSessionOps = null;
    }

    @Override
    public void using() {
        log.info("aSession is using!");
        this.lastAccessedTime = System.currentTimeMillis();
        this.aSessionOps.put(Constants.LAST_ACCESS_TIME,lastAccessedTime);
        this.aSessionExpireOps.add(aSessionId,lastAccessedTime + TimeUnit.SECONDS.toMillis(aSessionStoreConfig.getMaxInactiveInterval()));
        this.redisTemplate.expire(aSessionOps.getKey(),aSessionStoreConfig.getMaxInactiveInterval(),TimeUnit.SECONDS);
    }

    @Override
    public long getCreateTime() {
        return this.createTime;
    }

    @Override
    public long getLastAccessTime() {
        return this.lastAccessedTime;
    }

    @Override
    public int getMaxInactiveInterval() {
        return aSessionStoreConfig.getMaxInactiveInterval();
    }

    @Override
    public void setMaxInactiveInterval(int maxInactiveInterval) {
        aSessionStoreConfig.setMaxInactiveInterval(maxInactiveInterval);
    }

    @Override
    public long expiredTime() {
        if(aSessionStoreConfig.getMaxInactiveInterval() <= 0){
            return 0;
        }

        return lastAccessedTime + TimeUnit.SECONDS.toMillis(aSessionStoreConfig.getMaxInactiveInterval());
    }

    @Override
    public boolean isExpired() {
        if(aSessionStoreConfig.getMaxInactiveInterval() <= 0){
            return false;
        }

        return System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(aSessionStoreConfig.getMaxInactiveInterval()) >= lastAccessedTime;
    }
}
