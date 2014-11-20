package com.jd.common.export.demo;

/**
 * Created by IntelliJ IDEA.
 * User: bianfeng
 * Date: 12-2-26
 * Time: 下午4:54
 * To change this template use File | Settings | File Templates.
 */
public class User {
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
