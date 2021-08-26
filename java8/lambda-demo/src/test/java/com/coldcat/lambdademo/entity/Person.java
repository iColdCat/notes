package com.coldcat.lambdademo.entity;

import lombok.Data;

/**
 * @ProjectName : lambda-demo
 * @FileName : Person
 * @Package : com.coldcat.lambdademo.entity
 * @Author : tanyuhang
 * @CreateDate : 2021-08-23
 */
@Data
public class Person {

    private Integer age;

    private String name;

    public Person() {}

    public Person(Integer age) {
        this.age = age;
    }
    public Person(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

}
