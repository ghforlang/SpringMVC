package com.nbu.edu.cn.utils;

public interface JsonUtils {

    /**
     * obj转json
     * @param obj
     * @return
     */
    String toJsonString(Object obj);

    /**
     * json转obj
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T  toObj(String jsonString,Class<T> clazz);
}
