package com.coldcat.lambdademo;

import com.coldcat.lambdademo.entity.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 一、Stream的三个操作步骤：
 *      1.创建stream
 *      2.中间操作
 *      3.终止操作
 */
class TestStreamAPI {

    /**
     * 创建流
     */
    @Test
    void test1() {
        //1. 通过collection系列集合提供的stream() 或 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2. 通过Arrays中的静态方法stream() 获取数组流
        String[] strings = new String[10];
        Stream<String> stream2 = Arrays.stream(strings);

        //3. 通过Stream类中的静态方法 of()
        Stream<String> stream3 = Stream.of("11", "aa");

        //4. 创建无限流
        //迭代
        Stream<Integer> stream4 = Stream.iterate(0, x -> x+2);
        stream4.limit(10).forEach(System.out::println);
        //生成
        Stream<Double> stream5 = Stream.generate(() -> Math.random());
        stream5.limit(5).forEach(System.out::println);
    }

    List<Person> personList = new ArrayList<Person>() {{
        add(new Person(18, "1"));
        add(new Person(99, "1"));
        add(new Person(200, "1"));
        add(new Person(17, "4"));
        add(new Person(3, "5"));
        add(new Person(9, "6"));
        add(new Person(489, "7"));
        add(new Person(58, "8"));
        add(new Person(72, "9"));
        add(new Person(72, "10"));
        add(new Person(72, "11"));
        add(new Person(72, "12"));
    }};

    /**
     * 中间操作
     *      filter -- 接收lambda，从流中排除某些元素
     *      limit -- 截断流，使其元素不超过给定的值
     *      skip(n) -- 跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流，与limit互补
     *      distinct -- 筛选，通过流所生成元素的hashCode() 和 equals() 去除重复元素
     */
    @Test
    void test2() {
        //内部迭代：迭代操作由 Stream API 完成
        //中间操作 不会执行任何操作
        Stream<Person> stream1 = personList
                .stream()
                .filter(p -> {
                    System.out.println("过滤操作");
                    return p.getAge() > 100;
                });
        //终止操作 一次性执行全部内容，即 “惰性求值”
        stream1.forEach(System.out::println);

        System.out.println("-------------------------------------------------------");

        personList.stream()
//                .filter(person -> {
//                    System.out.println("短路");
//                    return true;
//                })
                .limit(1)
                .forEach(System.out::println);

        System.out.println("-------------------------------------------------------");

        personList.stream()
                .skip(2)
                .forEach(System.out::println);

        System.out.println("-------------------------------------------------------");

        personList.stream()
                .distinct()
                .forEach(System.out::println);

        System.out.println("-------------------------------------------------------");
    }

    /**
     * 映射
     *      map -- 接收lambda，将元素转换成其他形式或提取信息，接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     *      flatmap -- 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    void test3() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc");
        list.stream()
                .map(s -> s.toUpperCase())
                .forEach(System.out::println);

        System.out.println("-------------------------------------------------------");

        personList.stream()
                .map(Person::getAge)
                .forEach(System.out::println);

        System.out.println("-------------------------------------------------------");

        list.stream()
                .map(TestStreamAPI::filterCharacter);
//        List list1 = new ArrayList();
//        list1.add("asd");
//        list1.add(list);

        list.stream()
                .flatMap(TestStreamAPI::filterCharacter);
//        List list2 = new ArrayList();
//        list2.add("asd");
//        list2.addAll(list);

    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    /**
     * 排序
     * sorted() 自然排序
     * sorted(Comparator com) 定制排序
     */
    @Test
    void test4() {
        List<String> list = Arrays.asList("bbb", "aaa", "ccc");
        list.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("-------------------------------------------------------");

        personList.stream()
                .sorted(Comparator.comparingInt(Person::getAge))
                .forEach(System.out::println);
    }

    /**
     * 终止操作
     *      查找与匹配
     *      allMatch -- 检查是否匹配所有元素
     *      anyMatch -- 检查是否至少匹配一个元素
     *      noneMatch -- 检查是否没有匹配所有元素
     *      findFirst -- 返回第一个元素
     *      findAny -- 返回当前流中的任意元素
     *      count -- 返回流中元素的总个数
     *      max -- 返回流中最大值
     *      min -- 返回流中最小值
     */
    @Test
    void test5() {
        List<Person> personList = new ArrayList<>();
        Optional<Person> op = personList.stream()
                .findFirst();
    }

    /**
     * 归约 reduce 可以将流中元素反复结合起来，得到一个值
     * map-reduce 因Google用它来进行网络搜索而出名
     * 例如：统计热搜、猜你喜欢
     */
    @Test
    void test6() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);

        Optional<Integer> ageSum = personList.stream()
                .map(Person::getAge)
                .reduce(Integer::sum);

    }

    /**
     * 收集 collect -- 将流转换为其他形式，接收一个collector接口的实现，用于给stream中元素做汇总的方法
     */
    @Test
    void test7() {
        List<Integer> ageList1  = personList.stream().map(Person::getAge).collect(Collectors.toList());
        Set<Integer> ageSet = personList.stream().map(Person::getAge).collect(Collectors.toCollection(HashSet::new));
        Long count1 = personList.stream().count();
        Long count2 = personList.stream().collect(Collectors.counting());
        Map<Integer, Person> personMap = personList.stream().collect(Collectors.toMap(
                person -> person.getAge(),
                person -> person
        ));
    }

    /**
     * 分组、多级分组
     */
    @Test
    void test8() {
        Map<Integer, List<Person>> personMap1 = personList.stream()
                .collect(Collectors.groupingBy(Person::getAge));

        Map<Integer, Map<String, List<Person>>> personMap2 = personList.stream()
                .collect(Collectors.groupingBy(Person::getAge, Collectors.groupingBy(Person::getName)));
    }

}
