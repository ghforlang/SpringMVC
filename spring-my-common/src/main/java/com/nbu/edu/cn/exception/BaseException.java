package com.nbu.edu.cn.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends Exception {

    /**异常码
     *
     */
    private String code;

    /**
     * 异常信息
     */
    private String message;

    /**
     * 响应枚举
     */
    private IResponseEnum responseEnum;

    /**
     * 参数
     */
    private Object[] args;

    public BaseException(){}


    public  BaseException(IResponseEnum responseEnum,Object[] args,String message){
        super(message);
        this.responseEnum = responseEnum;
        this.args = args;
        this.message = message;
    }

    public BaseException(IResponseEnum responseEnum,Object[] args,String message,Throwable t){
        super(message,t);
        this.responseEnum = responseEnum;
        this.args = args;
        this.message = message;
    }

}
