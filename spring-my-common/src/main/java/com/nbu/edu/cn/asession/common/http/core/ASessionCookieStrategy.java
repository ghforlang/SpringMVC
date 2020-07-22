package com.nbu.edu.cn.asession.common.http.core;

import com.nbu.edu.cn.exception.ResponseEnum;
import com.nbu.edu.cn.utils.CookieUtils;
import com.nbu.edu.cn.utils.HttpRequestUtils;
import com.nbu.edu.cn.utils.HttpServletUtils;
import com.nbu.edu.cn.asession.common.http.conf.ASessionHttpCookieConfig;
import com.nbu.edu.cn.asession.core.ASession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
public class ASessionCookieStrategy implements ASessionStrategy{

    private static final boolean SUPPORT_SERVLET3 = HttpServletUtils.supportServlet3();

    private ASessionHttpCookieConfig aSessionHttpCookieConfig;

    @Override
    public String getASessionIdName() {
        return null;
    }

    @Override
    public String getRequestedASessionId() {
        return null;
    }

    @Override
    public void onNewASession(ASession aSession, HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public HttpServletRequest convertRequest(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public HttpServletResponse convertResponse(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    public Map<String,String> getASessionIds(HttpServletRequest request){
        Cookie aSessionCookie = CookieUtils.getCookie(request,aSessionHttpCookieConfig.getASessionIdFieldName());
        if(Objects.isNull(aSessionCookie) || StringUtils.isBlank(aSessionCookie.getValue())){
            return Collections.EMPTY_MAP;
        }

        Map<String,String> result = new LinkedHashMap<>();
        String[] tokens = StringUtils.split(aSessionCookie.getValue(),aSessionHttpCookieConfig.getASessionIdSeperator());
        if(tokens.length > aSessionHttpCookieConfig.getMaxASessionIdSize()){
            log.warn("aSessionId错误,ip:{}", HttpRequestUtils.getIp(request));
            throw ResponseEnum.INVALID_A_SESSION.newException("无效sessionId!");
        }
    }

}
