package com.nbu.edu.cn.asession.core.operation;

import com.nbu.edu.cn.asession.core.ASession;
import com.nbu.edu.cn.asession.core.CachedASession;
import com.nbu.edu.cn.asession.core.RedisCachedASession;
import com.nbu.edu.cn.asession.core.config.ASessionRedisConfig;
import com.nbu.edu.cn.asession.core.config.ASessionStoreConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
public class CachedSessionOperation<S extends ASession> implements ASessionOperation<S> {

    /**
     * redis模板
     */
    private RedisTemplate<String,Object> redisTemplate;
    /**
     * config
     */
    private ASessionRedisConfig aSessionStoreConfig;

    public CachedSessionOperation(RedisTemplate<String,Object> redisTemplate,ASessionRedisConfig sessionStoreConfig){
        this.redisTemplate = redisTemplate;
        this.aSessionStoreConfig = sessionStoreConfig;
    }

    @Override
    public S create(String aSessionId) {
        log.debug("创建session:{}",aSessionId);
        CachedASession cachedASession = new RedisCachedASession(redisTemplate,aSessionStoreConfig,aSessionId);
        cachedASession.using();
        return (S)cachedASession;
    }

    @Override
    public void save(S aSession) {
        log.debug("保存session:{}",aSession.getASessionId());
        aSession.using();
    }

    @Override
    public S get(String aSessionId) {
        if(!isValid(aSessionId)){
            return null;
        }
        return (S)new RedisCachedASession(redisTemplate,aSessionStoreConfig,aSessionId);
    }

    @Override
    public void delete(String aSessionId) {
        log.debug("删除aSession:{}",aSessionId);
        redisTemplate.delete(aSessionStoreConfig.getASessionKeyPrefix().concat(aSessionId));
        redisTemplate.opsForZSet().remove(aSessionStoreConfig.getExpireASessionKey(),aSessionId);
    }

    @Override
    public boolean isValid(String aSessionId) {
        return redisTemplate.hasKey(aSessionStoreConfig.getASessionKeyPrefix().concat(aSessionId));
    }
}
