package com.interview._蚂蚁_;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源
 * @since 2022/10/27 20:19
 */
public class Test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String line2 = sc.nextLine();
        String[] strs = line.split(" ");
        String[] strs2 = line2.split(" ");
        int n = Integer.parseInt(strs[0]);
        int k = Integer.parseInt(strs[1]);
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // 小红攻击怪物
            map.put(i, Integer.parseInt(strs2[i]));
        }
        // 小红攻击怪物
        AtomicInteger cnt2 = new AtomicInteger();
        while (cnt2.get() > 0) {
            map.forEach((key, v) -> {
                if(v > 0){
                    cnt2.getAndIncrement();
                    v = k - v;
                }
            });
            cnt = cnt2.get();
        }
        System.out.println(cnt);
    }
}
