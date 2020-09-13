package com.nbu.edu.cn.utils.context;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class ErrorContext implements Context {

    private static final ThreadLocal<ErrorContext> LOCAL = ThreadLocal.withInitial(ErrorContext::new);
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private String message;
    private Throwable cause;
    private String  errorCode;

    private ErrorContext(){}

    public static ErrorContext instance(){
        return LOCAL.get();
    }

    public ErrorContext message(String message){
        this.message = message;
        return this;
    }

    public ErrorContext cause(Throwable cause){
        this.cause = cause;
        return this;
    }

    public  ErrorContext errorCode(String errorCode){
        this.errorCode = errorCode;
        return this;
    }

    public void reset(){
        message = null;
        cause = null;
        errorCode = null;
        LOCAL.remove();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if(StringUtils.isNotBlank(this.errorCode)){
            sb.append(LINE_SEPARATOR);
            sb.append("### errorCode : ");
            sb.append(this.errorCode);
        }

        if(StringUtils.isNotBlank(this.message)){
            sb.append(LINE_SEPARATOR);
            sb.append("### error message : ");
            sb.append(this.message);
        }

        if(Objects.nonNull(this.cause)){
            sb.append(LINE_SEPARATOR);
            sb.append("### may caused by  : ");
            sb.append(this.cause);
        }

        return sb.toString();
    }
}
