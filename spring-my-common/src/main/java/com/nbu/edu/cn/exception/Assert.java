package com.nbu.edu.cn.exception;

public interface Assert {

    /**
     * 创建异常
     * @param args
     * @return
     */
    BaseException newException(Object... args);

    /**
     * 创建异常
     * @param t
     * @param args
     * @return
     */
    BaseException newException(Throwable t,Object... args);


    default void assertNotNull(Object obj) throws Exception{
        if(obj == null){
            throw newException(obj);
        }
    }

    default void assertNotNull(Object obj,Object ... args) throws Exception{
        if(obj == null){
            throw newException(args);
        }
    }

}
