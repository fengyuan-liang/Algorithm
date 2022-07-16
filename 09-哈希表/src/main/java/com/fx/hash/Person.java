package com.fx.hash;

import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @since: 2022/7/15 12:03
 * @author: 梁峰源
 */
public class Person {
    private int age;
    private float height;
    private String name;

    public Person(int age, float height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        // 是一样的对象，直接返回
        if (this == o) return true;
        // 为空或者类文件不一样
        if (o == null || getClass() != o.getClass()) return false;
        // 比较成员变量，成员变量一样即相等
        Person person = (Person) o;
        return age == person.age &&
                Float.compare(person.height, height) == 0 &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        int hashCode = Integer.hashCode(age);
        hashCode = hashCode * 31 + Float.hashCode(height);
        hashCode = hashCode * 31 + (name == null ? 0 : name.hashCode());
        return hashCode;
    }
}
