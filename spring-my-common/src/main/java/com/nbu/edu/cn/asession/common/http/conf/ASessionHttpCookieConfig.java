package com.nbu.edu.cn.asession.common.http.conf;

import com.nbu.edu.cn.asession.core.config.DefaultASessionConfig;

public class ASessionHttpCookieConfig extends DefaultASessionConfig {

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

    public ASessionHttpCookieConfig setCookiePath(String cookiePath){
        this.cookiePath = cookiePath;
        return this;
    }

    public String getCookieDomain(){
        return cookieDomain;
    }


    public ASessionHttpCookieConfig setCookieDomain(String cookieDomain){
        this.cookieDomain = cookieDomain;
        return this;
    }

    public int getMaxASessionIdSize(){
        return maxASessionIdSize;
    }

    public ASessionHttpCookieConfig setMaxASessionIdSize(int maxASessionIdSize){
        this.maxASessionIdSize = maxASessionIdSize;
        return this;
    }

    public String getASessionIdSeperator(){
        return aSessionIdSeperator;
    }

    public ASessionHttpCookieConfig setASessionIdSeperator(String aSessionIdSeperator){
        this.aSessionIdSeperator = aSessionIdSeperator;
        return this;
    }


}
