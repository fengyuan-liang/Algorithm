package com.interview.ones;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源
 * @since 2022/11/8 20:17
 */
public class Test02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> collect = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int n = collect.get(0), m = collect.get(1), k = collect.get(1);
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        initMap(n, map);
        initMap(m, map2);
        for (int i = 0; i < k; i++) {
            String[] str = sc.nextLine().split(" ");
            Integer num1 = Integer.parseInt(str[0]), num2 = Integer.parseInt(str[1]);
            if(map.containsKey(num1)){
                map.put(num1, map.get(num1) + 1);
            }
            if(map.containsKey(num2)){
                map.put(num2, map.get(num2) + 1);
            }
        }
        // 行的最大值
        Optional<Integer> max = map.values().stream().max(Integer::compareTo);
        Optional<Integer> max2 = map2.values().stream().max(Integer::compareTo);
        System.out.println(max.get() + max2.get() - 1);
    }

    private static void initMap(int max, Map<Integer, Integer> map) {
        for (int i = 0; i <= max; i++) {
            map.put(i, 0);
        }
    }
}
