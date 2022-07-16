package com.fx.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @since: 2022/7/15 12:04
 * @author: 梁峰源
 */
public class HashPerson {
    public static void main(String[] args) {
        Person p1 = new Person(18, 1.67f, "张三");
        Person p2 = new Person(18, 1.67f, "张三");
        System.out.println(p1.hashCode());// 460141958
        System.out.println(p2.hashCode());// 1163157884
        Map<Object,Object> map = new HashMap<>();
        map.put(p1,"abc");
        map.put(p2,"def");
    }
}
