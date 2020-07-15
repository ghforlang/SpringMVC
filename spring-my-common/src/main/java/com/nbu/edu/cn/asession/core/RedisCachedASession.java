package com.nbu.edu.cn.asession.core;

import com.nbu.edu.cn.asession.common.Constants;
import com.nbu.edu.cn.asession.core.config.ASessionRedisConfig;
import com.nbu.edu.cn.asession.core.config.AbstractASessionStoreConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
     * 过期session操作
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
        return null;
    }

    @Override
    public <T> T getAttribute(String attributeName) {
        return null;
    }

    @Override
    public void setAttribute(String attributeName, Object attributeValue) {

    }

    @Override
    public void removeAttribute(String attributeName) {

    }

    @Override
    public void delete() {

    }

    @Override
    public void using() {

    }

    @Override
    public long getCreateTime() {
        return 0;
    }

    @Override
    public long getLastAccessTime() {
        return 0;
    }

    @Override
    public int getMaxInactiveInterval() {
        return 0;
    }

    @Override
    public void setMaxInactiveInterval(int maxInactiveInterval) {

    }

    @Override
    public long expiredTime() {
        return 0;
    }

    @Override
    public boolean isExpired() {
        return false;
    }
}
