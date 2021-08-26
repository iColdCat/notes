package com.coldcat.lambdademo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java8 内置了四大函数式接口
 *
 * Consumer<T> : 消费性接口
 *      void accept(T t)
 *
 * Supplier<T> : 供给型接口
 *      T get()
 *
 * Function<T, R> : 函数型接口
 *      R apply(T t)
 *
 * Predicate<T> : 断言型接口
 *      boolean test(T t)
 */
class TestFunctionalInterface {

    /**
     * Consumer<T> : 消费性接口
     */
    @Test
    void test1() {
        happy(100000, m -> System.out.println("您在洗浴中心消费了" + m + "元"));
    }
    public void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    /**
     * Supplier<T> : 供给型接口
     */
    @Test
    void test2() {
        List<Integer> list = getNumList(10, () -> (int)(Math.random()*100));
        System.out.println(list);
    }
    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<num; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    /**
     * Function<T, R> : 函数型接口
     */
    @Test
    void test3() {
        String oldStr = "\t\t\t\t\t   132";
        System.out.println(strHandler(oldStr, str -> str.trim()));
    }
    public String strHandler(String oldStr, Function<String, String> fun) {
        return fun.apply(oldStr);
    }


    /**
     * Predicate<T> : 断言型接口
     */
    @Test
    void test4() {
        List<String> list = Arrays.asList("111", "1234", "12345");
        List<String> newList = filterStr(list, s -> s.length()>3);
        System.out.println(newList);
    }
    public List<String> filterStr(List<String> strings, Predicate<String> predicate) {
        List<String> newList = new ArrayList<>();
        for (String str : strings) {
            if (predicate.test(str)) {
                newList.add(str);
            }
        }
        return newList;
    }

}
