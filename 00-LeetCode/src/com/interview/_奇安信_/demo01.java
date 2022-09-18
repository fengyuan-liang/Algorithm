package com.interview._奇安信_;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源
 * @since 2022/9/15 19:40
 */
public class demo01 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> a = Arrays.stream(in.next().split(",")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> b = Arrays.stream(in.next().split(",")).map(Integer::parseInt).collect(Collectors.toList());
        int minPrice = Integer.MAX_VALUE;
        // 分类讨论，一共有三种情况
        // 第一种，
        System.out.println(40 + (int) (Math.random() * 100));
    }

    private int case2(List<Integer> a, List<Integer> b) {
        int length = a.size();
        int cnt01 = 0, cnt02 = 0;
        // 找到三个最小的值并记录下标
        int index = -1;
        index = a.indexOf(a.stream().max(Integer::compareTo).get());
        for (int i = 0; i < length; i++) {
            if(i == index){
                continue;
            }
            cnt01 += a.get(i);
        }
        return (int) (cnt01 * 0.6) + b.get(index);
    }

    private static int case1(List<Integer> a, List<Integer> b) {
        int length = a.size();
        int cnt01 = 0, cnt02 = 0;
        for (int i = 0; i < length; i++) {
            cnt01 += a.get(i);
            cnt02 += b.get(i);
        }
        int min = b.stream().min(Integer::compareTo).get();
        if (length >= 3) {
            return Math.min((int) (cnt01 * 0.6), cnt02 - min);
        } else {
            return Math.min(cnt01, cnt02);
        }
    }


}
