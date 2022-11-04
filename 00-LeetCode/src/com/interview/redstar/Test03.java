package com.interview.redstar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源
 * @since 2022/11/1 19:45
 */
public class Test03 {
    public static void main(String[] args) {

    }
    public static int[] merge (int[] nums1, int m, int[] nums2, int n) {
        // write code here
        List<Integer> set = new ArrayList<>();
        for (int i : nums1) {
            if(i == 0){
                continue;
            }
            set.add(i);
        }
        for (int i : nums2) {
            if(i == 0){
                continue;
            }
            set.add(i);
        }
        int[] arr = new int[set.size()];
        AtomicInteger cnt = new AtomicInteger();
        // 先排序
        List<Integer> collect = set.stream().sorted(Integer::compareTo).collect(Collectors.toList());
        collect.forEach(e -> {
            arr[cnt.getAndIncrement()] = e;
        });
        return arr;
    }
}
