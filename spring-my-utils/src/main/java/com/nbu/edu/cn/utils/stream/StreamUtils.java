package com.nbu.edu.cn.utils.stream;

import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class StreamUtils {
    /**
     * Stream特性：非数据结构；不支持索引访问；但是很容易生成数组或者list；惰性化；并行能力；可以是无限的；
     * stream只有两种类型操作：Intermediate（中间操作） 和 Terminal(终止型)
     * 区别:一个Stream后可以有多个Intermediate 操作，目的主要是打开流，做出某种程度的数据映射/过滤，然后返回一个新的流，交给下一个操作使用；这类操作是惰性的，也就是说仅仅调用到这类方法并没有真正开始流的遍历
     *   一个Stream后只能有一个terminal操作，该操作执行完之后，流就被使用完了，无法再被操作；terminal操作的执行才会真正开始流的遍历，并且会生成一个结果或者sideEffect
     */


    /**
     * 根据supplier生成n个对象的list
     * @param supplier
     * @param n
     * @param <T>
     * @return
     */
    public static <T> List<T> buildList(Supplier<T> supplier,int n){
        return Stream.generate(supplier).limit(n).collect(Collectors.toList());
    }

    /**
     * 根据supplier生成n个对象的list
     * @param supplier
     * @param n
     * @param <T>
     * @return
     */
    public static <T> Set<T> buildSet(Supplier<T> supplier,int n){
        return Stream.generate(supplier).limit(n).collect(Collectors.toSet());
    }
    /**
     * collection to list
     * @param col
     * @param mapper
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T,R> List<R> toList(Collection<T> col,Function<T,R> mapper){
        return CollectionUtils.isEmpty(col) ? Collections.EMPTY_LIST : col.stream().map(mapper).collect(Collectors.toList());
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
        return CollectionUtils.isEmpty(col) ? Collections.EMPTY_LIST : col.stream().map(mapper).distinct().collect(Collectors.toList());
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
        return CollectionUtils.isEmpty(col) ? Collections.EMPTY_SET : col.stream().map(mapper).collect(Collectors.toSet());
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
        return CollectionUtils.isEmpty(col) ? Collections.EMPTY_MAP : col.stream().collect(Collectors.groupingBy(classifier));
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
        return CollectionUtils.isEmpty(col) ? Collections.EMPTY_MAP : col.stream().collect(Collectors.toMap(keyMapper,valueMapper,(key1,key2) -> key2));
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
            return Collections.EMPTY_MAP;
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
            return Collections.EMPTY_LIST;
        }

        for(Predicate<T> p : predicates){
            list = list.stream().filter(p).collect(Collectors.toList());
        }
        return list;
    }

    /**
     * 找到列表中符合条件的元素,这里的finaAny与findFirst效果类似
     * @param list
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> T filter(List<T> list ,Predicate<T> predicate){
        return CollectionUtils.isEmpty(list) ? null : list.stream().filter(predicate).findAny().orElse(null);
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
        return ArrayUtils.isEmpty(arrays) ? Collections.EMPTY_LIST : Arrays.asList(arrays).stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * 完全满足条件
     * @param col
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> boolean allMatch(Collection<T> col,Predicate<T> predicate){
        return CollectionUtils.isEmpty(col) ? false : col.stream().allMatch(predicate);
    }

    /**
     * 任一满足条件
     * @param col
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> boolean anyMatch(Collection<T> col,Predicate<T> predicate){
        return CollectionUtils.isEmpty(col) ? false : col.stream().anyMatch(predicate);
    }

    /**
     * 全部不满足条件
     * @param col
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> boolean nonMatch(Collection<T> col,Predicate<T> predicate){
        return CollectionUtils.isEmpty(col) ? true : col.stream().noneMatch(predicate);
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

    /**
     * 复制流，peek操作对每个元素执行操作并返回一个新的Stream
     * @param col
     * @param mapper
     * @param <T>
     * @return
     */
    public static <T> Stream<T> peekStream(Collection<T> col, Consumer<T> mapper){
        return CollectionUtils.isEmpty(col) ? Stream.empty() : col.stream().peek(mapper);
    }
}
