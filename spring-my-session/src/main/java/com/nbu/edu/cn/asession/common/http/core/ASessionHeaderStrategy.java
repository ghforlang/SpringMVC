package com.nbu.edu.cn.asession.common.http.core;

import com.nbu.edu.cn.asession.core.ASession;
import com.nbu.edu.cn.exception.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ASessionHeaderStrategy implements ASessionStrategy{

    private String aSessionIdName;

    @Override
    public String getASessionIdName() {
        return aSessionIdName;
    }

    public void setASessionIdName(String aSessionIdName) {
        if(StringUtils.isBlank(aSessionIdName)){
            throw ResponseEnum.INVALID_A_SESSION.newException("无效sessionIdName!");
        }
        this.aSessionIdName = aSessionIdName;
    }

    @Override
    public String getRequestedASessionId(HttpServletRequest request) {
        return request.getHeader(aSessionIdName);
    }

    @Override
    public void onNewASession(ASession aSession, HttpServletRequest request, HttpServletResponse response) {
        response.setHeader(aSessionIdName,aSession.getASessionId());
    }

    @Override
    public HttpServletRequest convertRequest(HttpServletRequest request, HttpServletResponse response) {
        return request;
    }

    @Override
    public HttpServletResponse convertResponse(HttpServletRequest request, HttpServletResponse response) {
        return response;
    }
}
