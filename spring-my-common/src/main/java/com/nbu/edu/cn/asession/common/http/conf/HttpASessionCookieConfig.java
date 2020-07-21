package com.nbu.edu.cn.asession.common.http.conf;

import com.nbu.edu.cn.asession.core.config.DefaultASessionConfig;

public class HttpASessionCookieConfig extends DefaultASessionConfig {

    /**
     * cookie path
     */
    protected String cookiePath = "/";

    /**
     * cookie domain
     */
    protected String cookieDomain = "";
    /**
     * 最多session个数
     */
    protected int maxASessionIdSize = 10;
    /***
     * 分隔符
     */
    protected String aSessionIdSeperator = ":";

    public String getCookiePath(){
        return this.cookiePath;
    }

    public HttpASessionCookieConfig setCookiePath(String cookiePath){
        this.cookiePath = cookiePath;
        return this;
    }

    public String getCookieDomain(){
        return cookieDomain;
    }


    public HttpASessionCookieConfig setCookieDomain(String cookieDomain){
        this.cookieDomain = cookieDomain;
        return this;
    }

    public int getMaxASessionIdSize(){
        return maxASessionIdSize;
    }

    public HttpASessionCookieConfig setMaxASessionIdSize(int maxASessionIdSize){
        this.maxASessionIdSize = maxASessionIdSize;
        return this;
    }

    public String getASessionIdSeperator(){
        return aSessionIdSeperator;
    }

    public HttpASessionCookieConfig setASessionIdSeperator(String aSessionIdSeperator){
        this.aSessionIdSeperator = aSessionIdSeperator;
        return this;
    }


}
