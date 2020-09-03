package com.nbu.edu.cn.utils;

import com.nbu.edu.cn.utils.stream.StreamUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUtilsTest {


    public static void main(String[] args) {
        List<Integer> list = buildList();
        System.out.println(JackSonUtils.toJsonString(list));
        testStreamListNotEmpty(list);
        testStreamListEmpty();

        Set<Integer> set = buildSet();
        System.out.println(JackSonUtils.toJsonString(set));
        testCollectionToList(set);
        testCollectionToSet(list);
        testToGroupMap(list);
        testFilter(list);

        Stream<String> stream = buildStrStream();
        String[] strArr = stream.toArray(String[]::new);
        System.out.println(JackSonUtils.toJsonString(strArr));
        testBuildList();
        /**
         * 一个流只能使用一次
         * Exception : stream has already been operated upon or closed
         */
//        List<String> list1 = stream.collect(Collectors.toList());
    }


    public static void testStreamListNotEmpty(List<Integer> list){
        list = StreamUtils.toList(list,v -> v * (v + 1)/2);
        System.out.println(JackSonUtils.toJsonString(list));
    }


    public static void testStreamListEmpty(){
        List<Integer> list = new ArrayList<>();
        list = StreamUtils.toList(list,v -> v * 2);
        System.out.println(JackSonUtils.toJsonString(list));
    }

    public static void testCollectionToList(Set<Integer> set){
        List<Integer> list = StreamUtils.toList(set,v -> v *2);
        System.out.println(JackSonUtils.toJsonString(list));
    }

    public static void testCollectionToSet(List<Integer> list){
        System.out.println(JackSonUtils.toJsonString(StreamUtils.toSet(list,v -> v * 2)));
    }

    public static void testToGroupMap(List<Integer> list){
        System.out.println(JackSonUtils.toJsonString(StreamUtils.toGroupMap(list,v -> v %3)));
    }

    public static void testFilter(List<Integer> list){
        System.out.println(JackSonUtils.toJsonString(StreamUtils.filter(list,v -> v % 2 == 0)));
    }

    public static void testBuildList(){
        System.out.println(JackSonUtils.toJsonString(StreamUtils.buildList(new Random()::nextInt,5)));
    }

    private static List<Integer> buildList(){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<5 ;i ++){
            list.add(i);
        }
        return list;
    }

    private static Set<Integer> buildSet(){
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<5 ;i ++){
            set.add(i * (i + 1));
        }
        return set;
    }

    private static  Stream<String> buildStrStream(){
        Stream<String> strStream = Stream.of("a","b","c");
        return strStream;
    }

}
