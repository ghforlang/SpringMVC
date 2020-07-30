package com.nbu.edu.cn.utils.serializer.kryo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.util.Objects;

@Slf4j
public class AKryoSerializerFactory implements PooledObjectFactory<AKryoSerializer>{

    private AKryoSerializerConf config;

    public AKryoSerializerFactory() {
        super();
        this.config = new AKryoSerializerConf();
    }

    public AKryoSerializerFactory(AKryoSerializerConf config) {
        this.config = config;
    }

    @Override
    public PooledObject<AKryoSerializer> makeObject() throws Exception {
        log.debug("创建一个obj");
        return new DefaultPooledObject<AKryoSerializer>(new AKryoSerializer(config.getInitBufferSize(),config.getMaxBufferSize()));
    }

    @Override
    public void destroyObject(PooledObject<AKryoSerializer> pooledObject) throws Exception {
        log.debug("销毁一个obj");
        pooledObject.getObject().close();
    }

    @Override
    public boolean validateObject(PooledObject<AKryoSerializer> pooledObject) {
        log.debug("验证obj");
        return Objects.nonNull(pooledObject) && Objects.nonNull(pooledObject.getObject());
    }

    @Override
    public void activateObject(PooledObject<AKryoSerializer> pooledObject) throws Exception {
        log.debug("激活obj");
    }

    @Override
    public void passivateObject(PooledObject<AKryoSerializer> pooledObject) throws Exception {
        log.debug("钝化obj");
    }
}
