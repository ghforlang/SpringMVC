package com.nbu.edu.cn.utils.serializer;

import com.nbu.edu.cn.exception.ResponseEnum;

import java.io.Closeable;

public interface ASerializer extends Closeable {

    /**
     * 序列化
     * @param data
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> byte[] serialize(T data,Class<T> clazz) throws Exception;

    /**
     * 反序列化
     * @param data
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T deSerialize(byte[] data,Class<T> clazz) throws Exception;
}
