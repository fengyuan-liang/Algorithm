package com.day;

import java.util.*;

/**
 * @author Eureka <liangfengyuan1024@gmail.com>
 * @since 2025/4/13 10:54
 */
public class D20250413 {
    public static void main(String[] args) {
        D20250413 instance = new D20250413();
        System.out.println(instance.smallestPalindrome("z"));
        System.out.println(instance.smallestPalindrome("babab"));
    }

    public String smallestPalindrome(String s) {
        // Step 1: 统计每个字符的频率
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Step 2: 按字典序排序字符
        PriorityQueue<Character> priorityQueue = new PriorityQueue<>(frequencyMap.keySet());

        // Step 3: 构造回文的左半部分
        StringBuilder leftHalf = new StringBuilder();
        Character center = null; // 用于存放中心字符（如果有）

        while (!priorityQueue.isEmpty()) {
            char c = priorityQueue.poll();
            int count = frequencyMap.get(c);

            if (count % 2 == 1) {
                // 如果字符出现奇数次，记录为候选中心字符
                center = c;
            }

            // 将字符的一半加入左半部分
            for (int i = 0; i < count / 2; i++) {
                leftHalf.append(c);
            }
        }

        // Step 4: 构造回文字符串
        StringBuilder rightHalf = new StringBuilder(leftHalf).reverse(); // 右半部分是左半部分的反转
        StringBuilder result = new StringBuilder();

        result.append(leftHalf); // 左半部分
        if (center != null) {
            result.append(center); // 中心字符（如果有）
        }
        result.append(rightHalf); // 右半部分

        return result.toString();
    }

}
