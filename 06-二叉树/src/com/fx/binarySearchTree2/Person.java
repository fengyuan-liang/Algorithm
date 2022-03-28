package com.fx.binarySearchTree2;

/**
 * @author: 梁峰源
 * @date: 2022/3/14 21:59
 * Description:
 */
public class Person implements java.lang.Comparable<Person> {
    private int age;
    public Person(int age){
        this.age = age;
    }

    public int getAge(){
        return age;
    }

    /**
     * 比较规则，我们可以指定返回值大于1表示当前类在比较中是大的，小于0表示是小的，0表示相等
     */
    @Override
    public int compareTo(Person person) {
//        if(age > person.age) return 1;
//        if(age < person.age) return -1;
//        return 0;
        return age - person.age;
    }

    @Override
    public String toString() {
        return "age=" + age;
    }
}
