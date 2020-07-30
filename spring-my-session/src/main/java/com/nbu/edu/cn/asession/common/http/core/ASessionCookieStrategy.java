package com.nbu.edu.cn.asession.common.http.core;

import com.nbu.edu.cn.asession.common.http.conf.ASessionHttpCookieConfig;
import com.nbu.edu.cn.asession.core.ASession;
import com.nbu.edu.cn.exception.ResponseEnum;
import com.nbu.edu.cn.utils.CookieUtils;
import com.nbu.edu.cn.utils.HttpRequestUtils;
import com.nbu.edu.cn.utils.HttpServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class ASessionCookieStrategy implements ASessionStrategy{

    private static final boolean SUPPORT_SERVLET3 = HttpServletUtils.supportServlet3();

    private ASessionHttpCookieConfig aSessionHttpCookieConfig;

    @Override
    public String getASessionIdName() {
        return this.aSessionHttpCookieConfig.getASessionIdFieldName();
    }

    @Override
    public String getRequestedASessionId(HttpServletRequest request) {
        Cookie  cookie = CookieUtils.getCookie(request,aSessionHttpCookieConfig.getASessionIdFieldName());
        return Objects.isNull(cookie) ? null : cookie.getValue();
    }

    @Override
    public void onNewASession(ASession aSession, HttpServletRequest request, HttpServletResponse response) {
        String aSessionId = aSession.getASessionId();
        Cookie cookie = renewASessionCookie(request,aSessionId);
        response.addCookie(cookie);
    }


    @Override
    public HttpServletRequest convertRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(ASessionCookieStrategy.class.getName(),this);
        return request;
    }

    @Override
    public HttpServletResponse convertResponse(HttpServletRequest request, HttpServletResponse response) {
        return new HttpCookieASessionResponseWrapper(response);
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

        result.put(aSessionHttpCookieConfig.getASessionIdFieldName(),tokens[0]);
        for(int i = 2; i< tokens.length ; i+= 2){
            result.put(tokens[i-1],tokens[i]);
        }
        return result;
    }


    public String encodeURL(String url,String alias){
        String encodedAlias = encode(alias);
        int index = url.indexOf("?");
        boolean isDefaultAlias = aSessionHttpCookieConfig.getASessionIdFieldName().endsWith(encodedAlias);
        if(index < 0){
            return isDefaultAlias ? url : url + "?" + aSessionHttpCookieConfig.getASessionIdFieldName() + "=" + encodedAlias;
        }
        String path = url.substring(0,index);
        String query = url.substring(index + 1,url.length());
        String replacement = isDefaultAlias ? "" : "$1" + encodedAlias;
        query = query.replaceFirst("((^|&)" + aSessionHttpCookieConfig.getASessionIdFieldName() + "=)([^&]+)?",replacement);
        if(!isDefaultAlias && url.endsWith(query)){
            if(!(query.endsWith("&") || query.length() == 0)){
                query += "&";
            }
            query += aSessionHttpCookieConfig.getASessionIdFieldName() + "=" + encodedAlias;
        }
        return path + "?" + query;
    }

    public ASessionHttpCookieConfig getaSessionHttpCookieConfig() {
        return aSessionHttpCookieConfig;
    }

    public ASessionCookieStrategy setaSessionHttpCookieConfig(ASessionHttpCookieConfig aSessionHttpCookieConfig) {
        this.aSessionHttpCookieConfig = aSessionHttpCookieConfig;
        return this;
    }

    private Cookie renewASessionCookie(HttpServletRequest request, String aSessionId) {
        Cookie aSessionCookie = new Cookie(aSessionHttpCookieConfig.getASessionIdFieldName(),"");
        if(SUPPORT_SERVLET3){
            aSessionCookie.setHttpOnly(true);
        }
        aSessionCookie.setSecure(request.isSecure());
        aSessionCookie.setPath(aSessionHttpCookieConfig.getCookiePath());
        if(StringUtils.isNoneBlank(aSessionHttpCookieConfig.getCookieDomain())){
            aSessionCookie.setDomain(aSessionHttpCookieConfig.getCookieDomain());
        }

        if(StringUtils.isBlank(aSessionId)){
            aSessionCookie.setMaxAge(0);
            return aSessionCookie;
        }else{
            aSessionCookie.setMaxAge(aSessionHttpCookieConfig.getMaxInactiveInterval());
        }

        aSessionCookie.setValue(aSessionId);
        return aSessionCookie;
    }

    private class HttpCookieASessionResponseWrapper extends HttpServletResponseWrapper{
        public HttpCookieASessionResponseWrapper(HttpServletResponse response){
            super(response);
        }

        @Override
        public String encodeRedirectURL(String url) {
            String encodeURL = super.encodeRedirectURL(url);
            return ASessionCookieStrategy.this.encodeURL(encodeURL,aSessionHttpCookieConfig.getASessionIdFieldName());
        }

        @Override
        public String encodeURL(String url) {
            String encodeURL = super.encodeURL(url);
            return ASessionCookieStrategy.this.encodeURL(encodeURL,aSessionHttpCookieConfig.getASessionIdFieldName());
        }
    }

    private String encode(String value){
        try {
            return URLEncoder.encode(value,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw ResponseEnum.BAD_UTILS.newException("编码异常!");
        }
    }


}
