package com.nbu.edu.cn.asession.core.operation;

import com.nbu.edu.cn.asession.core.CachedASession;

public class CachedSessionOperation implements ASessionOperation<CachedASession> {

    @Override
    public CachedASession create(String aSessionId) {
        return null;
    }

    @Override
    public void save(CachedASession aSession) {

    }

    @Override
    public CachedASession get(String aSessionId) {
        return null;
    }

    @Override
    public void delete(String aSessionId) {

    }

    @Override
    public boolean isValid(String aSessionId) {
        return false;
    }
}
