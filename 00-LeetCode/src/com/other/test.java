package com.other;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源
 * @since 2022/8/7 19:45
 */
public class test{
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,2,3,4);
        list.stream().sorted((e1,e2)->e2-e1).forEach(System.out::println);
    }
}
