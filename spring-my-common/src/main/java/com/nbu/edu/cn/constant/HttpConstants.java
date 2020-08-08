package com.nbu.edu.cn.constant;

import lombok.experimental.UtilityClass;
import okhttp3.MediaType;

@UtilityClass
public class HttpConstants {

    /**
     * http协议
     */
    public static final String PROTOCOL_HTTP = "HTTP";
    /**
     * https协议
     */
    public static final String PROTOCOL_HTTPS = "HTTPS";

    /**
     * 参数连接符
     */
    public static final String URL_PARAM_JOIN = "&";

    /**
     * 连接符
     */
    public static final String URL_JOIN = "?";


    /**
     * 默认mediaType
     */
    public static final MediaType JSON = MediaType.parse("applicaton/json;charset=utf-8");
}
