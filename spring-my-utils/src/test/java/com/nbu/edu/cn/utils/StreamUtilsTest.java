package com.nbu.edu.cn.utils;

import com.nbu.edu.cn.utils.stream.StreamUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

}
