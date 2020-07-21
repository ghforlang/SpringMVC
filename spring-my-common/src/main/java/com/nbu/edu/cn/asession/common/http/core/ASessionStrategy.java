package com.nbu.edu.cn.asession.common.http.core;

import com.nbu.edu.cn.asession.core.ASession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ASessionStrategy extends ASessionHttpConvert{
    /**
     * sessionId name
     * @return
     */
    String getASessionIdName();

    /**
     * 获取request内部的aSessionId
     * @return
     */
    String getRequestedASessionId();

    /**
     * 创建新aSession
     * @param aSession
     * @param request
     * @param response
     */
    void onNewASession(ASession aSession, HttpServletRequest request, HttpServletResponse response);
}
