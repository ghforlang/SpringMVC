package com.nbu.edu.cn.exception;

import com.nbu.edu.cn.exception.BusinessExceptionAssert;

public enum ResponseEnum implements BusinessExceptionAssert {
    ILLEGAL_ARGS("1111","非法参数"),
    BAD_REQUEST("1000","bad Request"),
    BAD_UTILS("1100","工具类异常")
    ;

    /**
     * 响应code
     */
    private String code;
    /**
     * 响应信息
     */
    private String message;



    ResponseEnum(String code,String message){
        this.code = code;
        this.message = message;
    }


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
