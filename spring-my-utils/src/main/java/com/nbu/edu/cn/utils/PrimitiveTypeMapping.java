package com.nbu.edu.cn.utils;

import java.util.Objects;

public enum PrimitiveTypeMapping {
    INT(int.class,Integer.class),
    LONG(long.class,Long.class),
    DOUBLE(double.class,Double.class),
    FLOAT(float.class,Float.class),
    BYTE(byte.class,Byte.class),
    SHORT(short.class,Short.class),
    CHAR(char.class,Character.class),
    BOOLEAN(boolean.class,Boolean.class);

    private Class<?> primitiveType;
    private Class<?> boxingType;

    PrimitiveTypeMapping(Class<?> primitiveType, Class<?> boxingType) {
        this.primitiveType = primitiveType;
        this.boxingType = boxingType;
    }

    public static Class<?> getPrimitiveType(Class<?> boxingType){
        if(Objects.isNull(boxingType)){
            return null;
        }

        for (PrimitiveTypeMapping mapping : PrimitiveTypeMapping.values()) {
            if(mapping.boxingType.equals(boxingType)){
                return mapping.primitiveType;
            }
        }

        return null;
    }


    public static Class<?> getBoxingType(Class<?> primitiveType){
        if(Objects.isNull(primitiveType)){
            return null;
        }

        for (PrimitiveTypeMapping mapping : PrimitiveTypeMapping.values()) {
            if(mapping.primitiveType.equals(primitiveType)){
                return mapping.boxingType;
            }
        }
        return null;
    }

    public Class<?> getPrimitiveType() {
        return primitiveType;
    }

    public Class<?> getBoxingType() {
        return boxingType;
    }
}
