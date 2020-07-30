package com.nbu.edu.cn.utils.serializer.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.ByteBufferInput;
import com.esotericsoftware.kryo.io.ByteBufferOutput;
import com.nbu.edu.cn.utils.serializer.FullASerializer;
import com.nbu.edu.cn.constant.ArrayCst;
import com.nbu.edu.cn.exception.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class AKryoSerializer implements FullASerializer {

    private Kryo kryo;

    private ByteBufferOutput output;


    public AKryoSerializer(int bufferSize,int maxBufferSize) {
        this.kryo = new Kryo();
        this.output = new ByteBufferOutput(bufferSize,maxBufferSize);
    }

    @Override
    public <T> byte[] serialize(T data) throws Exception {
        log.debug("序列化{}",data);
        if(Objects.isNull(data)){
            return ArrayCst.NULL_BYTE_ARRAY;
        }

        try{
            kryo.writeClassAndObject(output,data);
            return output.toBytes();
        }catch (Exception ex){
            log.error("序列化异常:" + ex.getMessage() + "json");
            throw ResponseEnum.SERIALIZE_EXCEPTION.newException("序列化异常",ex);
        }finally {
            output.clear();
        }
    }

    @Override
    public <T> T deSerialize(byte[] data) throws Exception {
        log.debug("反序列化开始!");
        if(ArrayUtils.isEmpty(data)){
            return null;
        }

        ByteBufferInput input = new ByteBufferInput();
        input.setBuffer(data);
        try{
            return (T)kryo.readClassAndObject(input);
        }catch (Exception ex){
            log.error("反序列化异常," + ex.getMessage() + "json");
            throw ResponseEnum.DE_SERIALIZE_EXCEPTION.newException("反序列化异常",ex);
        }finally {
            input.close();
        }

    }

    @Override
    public <T> byte[] serialize(T data, Class<T> clazz) throws Exception {
        return this.serialize(data);
    }

    @Override
    public <T> T deSerialize(byte[] data, Class<T> clazz) throws Exception {
        return this.deSerialize(data);
    }

    @Override
    public void close() throws IOException {
        this.kryo = null;
        this.output.clear();
        this.output.close();
        this.output = null;
    }
}
