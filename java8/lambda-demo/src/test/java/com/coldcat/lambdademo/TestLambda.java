package com.coldcat.lambdademo;

import org.junit.jupiter.api.Test;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * java8中总引入了一个新的操作符 “->” 该操作符称为箭头操作符或lambda操作符，箭头操作符将lambda表达式拆分成两部分
 * 左侧：lambda表达式中的参数列表
 * 右侧：lambda表达式中所需执行的功能，即lambda体
 *
 * lambda表达式需要 函数式接口 的支持
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口
 * 可以使用注解 @FunctionalInterface 修饰 可以检查是否是函数式接口
 */
class TestLambda {

    //无参数 无返回值 一行语句
    @Test
    void test1() {
        Runnable r1 = new Runnable() {
            @Override
            public void run(){
                System.out.println("hello world!");
            }
        };
        r1.run();

        Runnable r2 = () -> System.out.println("hello lambda");
        r2.run();
    }



    //无参数 无返回值 多行语句
    @Test
    void test2() {
        Runnable r1 = new Runnable() {
            @Override
            public void run(){
                System.out.println("凑数");
                System.out.println("hello world!");
            }
        };
        r1.run();

        Runnable r2 = () -> {
            System.out.println("凑数");
            System.out.println("hello lambda");
        };
        r2.run();
    }

    //无参数 有返回值 一行语句
    @Test
    void test3() {
        Supplier<Integer> supplier = () -> (int)(Math.random() * 100);
        Integer num = supplier.get();
        System.out.println(num);
    }

    //无参数 有返回值 多行语句
    @Test
    void test4() {
        Supplier<Integer> supplier = () -> {
            System.out.println("凑数");
            return (int)(Math.random() * 100);
        };
        Integer num = supplier.get();
        System.out.println(num);
    }

    //一个参数 无返回值 一行语句
    @Test
    void test5() {
        Consumer<Double> consumer = money -> System.out.println("您在洗浴中心消费了" + money + "元");
        consumer.accept(10000.00);
    }

    //一个参数 无返回值 多行语句
    @Test
    void test6() {
        Consumer<Double> consumer = money -> {
            System.out.println("凑数");
            System.out.println("您在洗浴中心消费了" + money + "元");
        };
        consumer.accept(10000.00);
    }

    //一个参数 有返回值 一行语句
    @Test
    void test7() {
        Function<String, Integer> function1 = str -> str.length();
        System.out.println(function1.apply("lambda"));

        Function<String, String> function2 = str -> str.trim();
        System.out.println(function2.apply("                lambda                "));
    }

    //一个参数 有返回值 多行语句
    @Test
    void test8() {
        Function<String, Integer> function1 = str -> {
            System.out.println("凑数");
            return str.length();
        };
        System.out.println(function1.apply("lambda"));
    }

    //多个参数 无返回值 一行语句
    @Test
    void test9() {
        BiConsumer<String, Double> biConsumer = (str, money) -> System.out.println("您于" + str + "在洗浴中心消费了" + money + "元");
        biConsumer.accept("2021年8月26日", 500.00);
    }

    //多个参数 无返回值 多行语句
    @Test
    void test10() {
        BiConsumer<String, Double> biConsumer = (str, money) -> {
            System.out.println("凑数");
            System.out.println("您于" + str + "在洗浴中心消费了" + money + "元");
        };
        biConsumer.accept("2021年8月26日", 500.00);
    }

    //多个参数 有返回值 一行语句
    @Test
    void test11() {
        BiFunction<String, String, Integer> biFunction = (str1, str2) -> str1.length() + str2.length();
        System.out.println(biFunction.apply("lam", "bda"));
    }

    //多个参数 有返回值 多行语句
    @Test
    void test12() {
        BiFunction<String, String, Integer> biFunction = (str1, str2) -> {
            System.out.println("凑数");
            return str1.length() + str2.length();
        };
        System.out.println(biFunction.apply("lam", "bda"));
    }

}
