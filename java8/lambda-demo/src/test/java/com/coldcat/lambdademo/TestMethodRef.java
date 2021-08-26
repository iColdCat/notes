package com.coldcat.lambdademo;

import com.coldcat.lambdademo.entity.Person;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、方法引用：若lambda体重的内容有方法已经实现，我们可以使用 方法引用
 *      注意：
 *          lambda体重调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的参数列表和返回值保持一致
 *          当第一个参数是这个 实例方法 的调用者，第二个参数是这个 实例方法 的参数时 可以使用 类::实例方法的格式
 * 二、构造器引用：
 *      格式：ClassName::new
 *      注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致
 * 三、数组引用：
 *      格式：Type::new
 *
 */
class TestMethodRef {

    /**
     * 对象::实例方法名
     */
    @Test
    void test1() {
        PrintStream ps1 = System.out;
        Consumer consumer1 = x -> ps1.println(x);

        PrintStream ps2 = System.out;
        Consumer consumer2 = ps2::println;

        consumer1.accept("111");
        consumer2.accept("222");
    }

    /**
     * 类::静态方法
     */
    @Test
    void test2() {
        Comparator<Integer> comparator1 = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> comparator2 = Integer::compare;
    }

    /**
     * 类::实例方法
     */
    @Test
    void test3() {
        BiPredicate<String, String> bp1 = (x, y) -> x.equals(y);
        BiPredicate<String, String> bp2 = String::equals;
    }

    /**
     * 构造器引用
     */
    @Test
    void test4() {
        Supplier<Person> supplier1 = () -> new Person();
        System.out.println(supplier1.get());
        Supplier<Person> supplier2 = Person::new;
        System.out.println(supplier2.get());

        Function<Integer, Person> function1 = age -> new Person(age);
        System.out.println(function1.apply(18));
        Function<Integer, Person> function2 = Person::new;
        System.out.println(function2.apply(20));
    }

    /**
     * 数组引用
     */
    @Test
    void test5() {
        Function<Integer, String[]> function1 = (x) -> new String[x];
        System.out.println(function1.apply(10).length);
        Function<Integer, String[]> function2 = String[]::new;
        System.out.println(function2.apply(20).length);
    }
}
