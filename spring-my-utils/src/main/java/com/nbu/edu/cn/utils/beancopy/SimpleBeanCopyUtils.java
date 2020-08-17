package com.nbu.edu.cn.utils.beancopy;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;

import java.util.List;
import java.util.Set;

public class SimpleBeanCopyUtils {

    private static final MapperFactory mapperFactory = BaseConverter.getMapperFactory();
    private static final MapperFacade simpleMapper = mapperFactory.getMapperFacade();

    /**
     * 简单的复制查新类型对象;支持同属性类型(基础类型<->包装类型)、同属性名之间的简单转换；
     * 同时，依据内置的41个类型转换器，可实现简单的类型对象转换
     * @param source
     * @param destClazz
     * @param <S>
     * @param <D>
     * @return
     */
    public static <S,D> D map(S source,Class<D> destClazz){
        return simpleMapper.map(source,destClazz);
    }

    /**
     * 极致性能的复制出新类型对象
     * @param source
     * @param sourceClazz
     * @param destClazz
     * @param <S>
     * @param <D>
     * @return
     */
    public static <S,D> D map(S source,Class<S> sourceClazz,Class<D> destClazz){
        return simpleMapper.map(source,getType(sourceClazz),getType(destClazz));
    }


    /**
     * 复制新对象列表到arrayList
     * @param sourceList
     * @param sourceClazz
     * @param destClazz
     * @param <S>
     * @param <D>
     * @return
     */
    public static <S,D> List<D> mapList(Iterable<S> sourceList, Class<S> sourceClazz, Class<D> destClazz){
        return simpleMapper.mapAsList(sourceList,getType(sourceClazz),getType(destClazz));
    }

    /**
     * 复制新对象雷彪到set
     * @param sourceList
     * @param sourceClazz
     * @param destClass
     * @param <S>
     * @param <D>
     * @return
     */
    public static <S,D> Set<D> mapSet(Iterable<S> sourceList,Class<S> sourceClazz,Class<D> destClass){
        return simpleMapper.mapAsSet(sourceList,getType(sourceClazz),getType(destClass));
    }

    /**
     * 复制出简单对象列表到数组
     * @param destArray
     * @param sourceArray
     * @param destClazz
     * @param <S>
     * @param <D>
     * @return
     */
    public static <S,D> D[] mapArray(final D[] destArray,final S[] sourceArray,final Class<D> destClazz){
        return simpleMapper.mapAsArray(destArray,sourceArray,destClazz);
    }

    /**
     * 极致性能的复制出新类型对象到数组
     * @param destArray
     * @param sourceArray
     * @param sourceClazz
     * @param destClazz
     * @param <S>
     * @param <D>
     * @return
     */
    public static <S,D> D[] mapArray(final D[] destArray,final S[] sourceArray,final Class<S> sourceClazz,final Class<D> destClazz){
        return simpleMapper.mapAsArray(destArray,sourceArray,getType(sourceClazz),getType(destClazz));
    }


    public static <E> Type<E> getType(Class<E> clazz){
        return TypeFactory.valueOf(clazz);
    }
}
