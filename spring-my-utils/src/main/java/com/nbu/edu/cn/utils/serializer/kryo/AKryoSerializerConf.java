package com.nbu.edu.cn.utils.serializer.kryo;

public class AKryoSerializerConf {

    private int initBufferSize = 1024;
    private int maxBufferSize = 1024 * 1024;


    public int getInitBufferSize() {
        return initBufferSize;
    }

    public AKryoSerializerConf setInitBufferSize(int initBufferSize) {
        this.initBufferSize = initBufferSize;
        return this;
    }

    public int getMaxBufferSize() {
        return maxBufferSize;
    }

    public AKryoSerializerConf setMaxBufferSize(int maxBufferSize) {
        this.maxBufferSize = maxBufferSize;
        return this;
    }
}
