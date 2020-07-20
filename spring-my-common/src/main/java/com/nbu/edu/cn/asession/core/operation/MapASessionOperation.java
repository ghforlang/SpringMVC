package com.nbu.edu.cn.asession.core.operation;

import com.nbu.edu.cn.asession.core.ASession;

public class MapASessionOperation implements ASessionOperation<ASession>{

    @Override
    public ASession create(String aSessionId) {
        return null;
    }

    @Override
    public void save(ASession aSession) {

    }

    @Override
    public ASession get(String aSessionId) {
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
