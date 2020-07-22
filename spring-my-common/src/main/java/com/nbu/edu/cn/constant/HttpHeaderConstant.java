package com.nbu.edu.cn.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HttpHeaderConstant {

    public static final String X_FORWARDED_FOR = "X-Forwarded-For";

    public static final String PROXY_CLIENT_ID = "Proxy-Client-IP";

    public static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    public static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";

    public static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";

    public static final String USER_AGENT = "User-Agent";

    public static final String UN_KNOWN = "unknown";
}
