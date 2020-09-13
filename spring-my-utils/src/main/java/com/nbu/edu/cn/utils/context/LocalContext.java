package com.nbu.edu.cn.utils.context;

import java.util.Objects;

public class LocalContext<T extends ContextAvailable> implements Context{

    private static final ThreadLocal<Object> LOCAL = ThreadLocal.withInitial(Object::new);

    private T stored;
    private T nowData;

    private LocalContext(){}

    public LocalContext(T data){
        this.nowData = data;
        LOCAL.set(data);
    }

    public static <T extends ContextAvailable> LocalContext<T> newInstance(T data){
        return new LocalContext<>(data);
    }

    public static <T extends ContextAvailable> LocalContext<T> instance(){
        return new LocalContext<>();
    }

    /**
     *
     * @return <T>T
     */
    public  <T> T get(){
        return (Objects.isNull(LOCAL.get()) || !(LOCAL.get() instanceof ContextAvailable)) ? null : (T)LOCAL.get();
    }


    public  T stored(T data){
        this.stored = nowData;
        this.nowData = data;
        LOCAL.set(data);
        return (T)LOCAL.get();
    }

    /**
     *
     * @param <T>
     * @return T
     */
    public  <T> T recall(){
        if(Objects.nonNull(stored)){
            LOCAL.set(stored);
            stored = null;
        }
        return (Objects.isNull(LOCAL.get()) || !(LOCAL.get() instanceof ContextAvailable)) ? null : (T)LOCAL.get();
    }

    public LocalContext<T> reset(){
        this.nowData = null;
        LOCAL.remove();
        return this;
    }

    public LocalContext<T>  resetAll(){
        this.nowData = null;
        this.stored = null;
        LOCAL.remove();
        return this;
    }


}
