package com.nbu.edu.cn.exception;


public class BusinessException extends BaseException{

    public BusinessException(){}

    public BusinessException(IResponseEnum responseEnum,Object[] args,String message){
        super(responseEnum,args,message);
    }

    public BusinessException(IResponseEnum responseEnum,Object[] args,String message,Throwable t){
        super(responseEnum,args,message,t);
    }


}
