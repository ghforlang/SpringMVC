package com.nbu.edu.cn.utils.stream;

import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class StreamUtils {

    private static final List EMPTY_LIST = new ArrayList();
    private static final Set EMPTY_SET = new HashSet<>();
    private static final Map EMPTY_MAP = new HashMap<>();


    /**
     * collection to list
     * @param col
     * @param mapper
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T,R> List<R> toList(Collection<T> col,Function<T,R> mapper){
        return CollectionUtils.isEmpty(col) ? EMPTY_LIST : col.stream().map(mapper).collect(Collectors.toList());
    }


    /**
     * collection to distinct list
     * @param col
     * @param mapper
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T,R> List<R> toDistinctList(Collection<T> col,Function<T,R> mapper){
        return CollectionUtils.isEmpty(col) ? EMPTY_LIST : col.stream().map(mapper).distinct().collect(Collectors.toList());
    }

    /**
     * collection to set
     * @param col
     * @param mapper
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T,R> Set<R> toSet(Collection<T> col,Function<T,R> mapper){
        return CollectionUtils.isEmpty(col) ? EMPTY_SET : col.stream().map(mapper).collect(Collectors.toSet());
    }

    /**
     * 简单聚合map，按照分类器结果分组
     * @param col
     * @param classifier
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T,R> Map<R,List<T>> toGroupMap(Collection<T> col,Function<T,R> classifier){
        return CollectionUtils.isEmpty(col) ? EMPTY_MAP : col.stream().collect(Collectors.groupingBy(classifier));
    }

    /**
     * collection to map ,key值可冲突，覆盖
     * @param col
     * @param keyMapper
     * @param valueMapper
     * @param <T>
     * @param <K>
     * @param <V>
     * @return
     */
    public static <T,K,V> Map<K,V> toMap(Collection<T> col,Function<T,K> keyMapper,Function<T,V> valueMapper){
        return CollectionUtils.isEmpty(col) ? EMPTY_MAP : col.stream().collect(Collectors.toMap(keyMapper,valueMapper,(key1,key2) -> key2));
    }


    /**
     * list 转两层map
     * @param col
     * @param classifier1
     * @param classifier2
     * @param <T>
     * @param <R1>
     * @param <R2>
     * @return
     */
    public static <T,R1,R2> Map<R1,Map<R2,T>> toGroupMap(List<T> col,Function<T,R1> classifier1,Function<T,R2> classifier2){
        if(CollectionUtils.isEmpty(col)){
            return EMPTY_MAP;
        }

        Map<R1,List<T>> map1 = col.stream().collect(Collectors.groupingBy(classifier1));
        Map<R1,Map<R2,T>> result = new HashMap<>();
        map1.forEach((k,v) -> {
            result.put(k,toMap(v,classifier2,vv -> vv));
        });
        return result;
    }


    /**
     * 简单的list过滤
     * @param list
     * @param predicates
     * @param <T>
     * @return
     */
    public static <T> List<T> listFilter(List<T> list, Predicate<T> ... predicates){
        if(CollectionUtils.isEmpty(list)){
            return EMPTY_LIST;
        }

        for(Predicate<T> p : predicates){
            list = list.stream().filter(p).collect(Collectors.toList());
        }
        return list;
    }

    /**
     * 简单的数据转list
     * @param arrays
     * @param mapper
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T,R> List<R> arrayTrans(T[] arrays,Function<T,R> mapper){
        return ArrayUtils.isEmpty(arrays) ? EMPTY_LIST : Arrays.asList(arrays).stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * collection to stream
     * @param col
     * @param <T>
     * @return
     */
    public static <T> Stream<T> toStream(Collection<T> col){
        return CollectionUtils.isEmpty(col) ? Stream.empty() : col.stream();
    }
}
