package com.interview2.tx;

import java.util.*;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源 <fengyuan-liang@foxmail.com>
 * @since 2022/11/18 15:56
 */
public class Test02 {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new TreeMap<>();
        map.put(1, 1);
        map.put(4, 1);
        map.put(3, 1);
        map.put(2, 1);
        System.out.println(map);
        Set<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(12);
        set.add(3);
        set.add(6);
        set.remove(12);
        for (Integer integer : set) {
            System.out.println(integer);
        }
        System.out.println(set);

    }
}
