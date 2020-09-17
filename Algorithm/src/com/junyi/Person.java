package com.junyi;

import java.io.Serializable;

/**
 * User: JY
 * Date: 2020/6/18 0018
 * Description:
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private int number;
    private int version;

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
    public String toString() {
        return "Person: " + name + age;
    }
}
