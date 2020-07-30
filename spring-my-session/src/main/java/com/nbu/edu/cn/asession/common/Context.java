package com.nbu.edu.cn.asession.common;

import java.util.Objects;

/**
 * @UtilityClass 误区
 * All methods, inner classes, and fields in the class are marked static
 */


/**
 * 参考mybatis ErrorContext
 */
public class Context {

    private static final ThreadLocal<Context> LOCAL = new ThreadLocal<>();

    /**
     * 数据
     */
    private Object data;

    /**
     * 缓存context
     */
    private Context stored;
    /**
     * 信息
     */
    private String message;

    /**
     * 异常堆栈
     */
    private Throwable cause;

    private Context(){}


    public static Context instance(){
        Context context = LOCAL.get();
        if(Objects.isNull(context)){
            context = new Context();
            LOCAL.set(context);
        }
        return context;
    }


    public Context store(){
        stored = this;
        LOCAL.set(new Context());
        return LOCAL.get();
    }

    public Context recall(){
        if(Objects.nonNull(stored)){
            LOCAL.set(stored);
            stored = null;
        }
        return LOCAL.get();
    }

    public Context message(String message){
        this.message = message;
        return this;
    }


    public Context data(Object data){
        this.data = data;
        return this;
    }

    public Context cause(Throwable cause){
        this.cause = cause;
        return this;
    }

    public Context reset(){
        data = null;
        stored = null;
        message = null;
        cause = null;
        LOCAL.remove();
        return this;
    }

    @Override
    public String toString() {
        return "Context{" +
                "data=" + data +
                ", stored=" + stored +
                ", message='" + message + '\'' +
                ", cause=" + cause.toString() +
                '}';
    }
}
