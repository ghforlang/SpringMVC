package com.nbu.edu.cn.asession.core.operation;

import com.nbu.edu.cn.asession.core.ASession;
import com.nbu.edu.cn.asession.core.MapASession;
import com.nbu.edu.cn.exception.BaseException;
import com.nbu.edu.cn.exception.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class MapASessionOperation implements ASessionOperation<ASession>{

    private final Map<String,ASession> mappedASession = new ConcurrentHashMap<>();

    private int maxInactiveInterval;

    public MapASessionOperation(){
        super();
    }

    public MapASessionOperation(Map<String,ASession> aSessionMap) throws BaseException {
        super();
        if(aSessionMap == null){
            throw ResponseEnum.ILLEGAL_ARGS.newException("aSessionMap can not be null !");
        }
    }

    @Override
    public ASession create(String aSessionId) {
        ASession mapASession = new MapASession(aSessionId);
        mapASession.setMaxInactiveInterval(maxInactiveInterval);
        return mapASession;
    }

    @Override
    public void save(ASession aSession) throws BaseException {
        if(aSession == null || StringUtils.isBlank(aSession.getASessionId())){
            throw ResponseEnum.ILLEGAL_ARGS.newException("aSessionMap can not be null !");
        }
        this.mappedASession.put(aSession.getASessionId(),aSession);
    }

    @Override
    public ASession get(String aSessionId) throws BaseException {
        if(StringUtils.isBlank(aSessionId)){
            log.warn("aSessionId can not be null or ''!");
            throw ResponseEnum.ILLEGAL_ARGS.newException("aSessionMap can not be null !");
        }

        ASession aSession = mappedASession.get(aSessionId);
        if(Objects.isNull(aSession) || aSession.isExpired()){
            return aSession;
        }

        aSession.using();
        return aSession;
    }

    @Override
    public void delete(String aSessionId) throws BaseException {
        if(StringUtils.isBlank(aSessionId)){
            log.warn("aSessionId can not be null or ''!");
            throw ResponseEnum.ILLEGAL_ARGS.newException("aSessionMap can not be null !");
        }

        mappedASession.remove(aSessionId);
    }

    @Override
    public boolean isValid(String aSessionId) {
        if(StringUtils.isBlank(aSessionId)){
            return false;
        }
        return mappedASession.containsKey(aSessionId);
    }
}
