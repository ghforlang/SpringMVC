package com.nbu.edu.cn.utils;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OtherStreamOperationTest {

    private static final OtherStreamOperationTest otherStreamTest = new OtherStreamOperationTest();
    public static void main(String[] args) {
        testReduce();
        println();
        testIterate();
        println();
        otherStreamTest.testCustomSupplier();
        println();
        otherStreamTest.testGroupBy();
        println();
        otherStreamTest.testPartitioningBy();
        println();
        testStatistics();
    }

    public static void testReduce(){
        //字符串拼接
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println("字符串拼接:" + concat);

        //求最小值
        double minValue = Stream.of(-4.0, 1.0, 3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println("最小值:" + minValue);

        //求和,无起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println("有无起始值求和:" + sumValue);
        // 求和, 有起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(1, Integer::sum);
        System.out.println("有起始值求和:" + sumValue);

        //过滤拼接
        concat = Stream.of("a", "B", "c", "D", "e", "F").filter(x -> x.compareTo("Z") > 0).reduce("", String::concat);
        System.out.println("过滤和字符串连接:" + concat);
    }

    public static void testIterate(){
        System.out.println("从2开始生成一个等差队列:");
        Stream.iterate(2, n -> n + 2).limit(5).forEach(x -> System.out.println(x + " "));
    }

    public  void testCustomSupplier(){
        System.out.println("自定义一个流进行计算输出:");
        Stream.generate(new DemoSupplier()).limit(2).forEach(u -> System.out.println(u.getId() + ", " + u.getName()));
    }

    public void testGroupBy(){
        System.out.println("通过id进行分组排序:");
        Map<Integer, List<User>> personGroups = Stream.generate(new DemoSupplier()).limit(5)
                .collect(Collectors.groupingBy(User::getId));
        Iterator it = personGroups.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, List<User>> persons = (Map.Entry) it.next();
            System.out.println("id " + persons.getKey() + " = " + JackSonUtils.toJsonString(persons.getValue()));
        }
    }

    public static void testStatistics(){
        List<Integer> numbers = Arrays.asList(1, 5, 7, 3, 9);
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }

    public void testPartitioningBy(){
        System.out.println("通过年龄进行分区排序:");
        Map<Boolean, List<User>> children = Stream.generate(new DemoSupplier()).limit(5)
                .collect(Collectors.partitioningBy(p -> p.getId() < 18));

        System.out.println("小孩: " + JackSonUtils.toJsonString(children.get(true)));
        System.out.println("成年人: " + JackSonUtils.toJsonString(children.get(false)));
    }

    public static void println(){
        System.out.println("-----------------------------------------------------");
    }

    class DemoSupplier implements Supplier<User> {
        private int index = 10;
        private Random random = new Random();
        @Override
        public User get() {
            return new User(index++,"name:" + random.nextInt(10));
        }
    }
}
