package com.nbu.edu.cn.utils.serializer.kryo;

import com.nbu.edu.cn.constant.ArrayCst;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.pool2.ObjectPool;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.util.Objects;

@Slf4j
public class AKryoCachedSerializer implements RedisSerializer<Object> {

    private ObjectPool<AKryoSerializer> serializerPool;

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if(Objects.isNull(o)){
            return ArrayCst.NULL_BYTE_ARRAY;
        }

        AKryoSerializer serializer = null;
        try {
            serializer = serializerPool.borrowObject();
            return serializer.serialize(o);
        } catch (Exception e) {
            log.error("序列化异常,e:",e);
//            throw ResponseEnum.SERIALIZE_EXCEPTION.newException("序列化异常",e);
        }finally {
            if(Objects.nonNull(serializer)){
                try {
                    serializerPool.returnObject(serializer);
                } catch (Exception e) {
                    log.error("归还serializer出错!,e:",e);
                }
            }
        }
        return ArrayCst.NULL_BYTE_ARRAY;
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if(ArrayUtils.isEmpty(bytes)){
            return null;
        }

        AKryoSerializer serializer = null;
        try {
            serializer = serializerPool.borrowObject();
            return serializer.deSerialize(bytes);
        } catch (Exception e) {
            log.error("反序列化失败,e:{}",e);
        }finally {
            if(Objects.nonNull(serializer)){
                try {
                    serializerPool.returnObject(serializer);
                } catch (Exception e) {
                    log.error("归还serializer出错!,e:",e);
                }
            }
        }
        return null;
    }

    public ObjectPool<AKryoSerializer> getSerializerPool() {
        return serializerPool;
    }

    public AKryoCachedSerializer setSerializerPool(ObjectPool<AKryoSerializer> serializerPool) {
        this.serializerPool = serializerPool;
        return this;
    }
}
