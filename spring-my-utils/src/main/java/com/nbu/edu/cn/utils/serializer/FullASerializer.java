package com.nbu.edu.cn.utils.serializer;

public interface FullASerializer extends ASerializer {

    /**
     * 序列化
     * @param data
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> byte[] serialize(T data) throws Exception;

    /**
     * 反序列化
     * @param data
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T deSerialize(byte[] data) throws Exception;
}
