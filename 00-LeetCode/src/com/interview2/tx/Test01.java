package com.interview2.tx;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums
 * 找到一个具有最大和的连续子数组（子数组最少包含一个元素）
 * 返回其最大和。nums = [-2,1,-3,4,-1,2,1,-5,4]
 *
 * @author 梁峰源
 * @since 2022/11/7 12:44
 */
public class Test01 {
    // 记录子序列中的最大值
    private static int cnt = Integer.MIN_VALUE;
    private static int deep = 0; // 记录现在往下遍历的深度
    private static Integer[] maxArr = {};

    public static void main(String[] args) {
        Integer[] arr = {
                -2, 1, -3, 4, -1, 2, 1, -5, 4
        };
        // 调用回溯函数，当开始从第一个元素开始，即index=0
        backTraking(arr, 0, arr.length);
        // 输出最长的数组
        System.out.println(maxArr);
    }

    private static void backTraking(Integer[] subArr, int index, int length) {
        // 1. 出口为子数组中没有元素
        if (index >= length - 1) {
            return;
        }
        // 2. 具体的计算逻辑，计算子数组中所有数字的和，需要跳过index的位置
        // 每一次只计算当前深度的子数组的和
        int count = (int) Arrays.stream(subArr).skip(index).limit(deep).count();
        // 如果这个数组比cnt大，则交换最大值， 并记录现在最大的子数组
        if (count > cnt) {
            cnt = count;
            maxArr = subArr;
        }
        // 3. 构造循环从每一个元素开始往下遍历
        for (int i = 0; i < length; i++) {
            // 从第一个元素开始，每个元素都和其他元素进行累加，穷举所有结果
            backTraking(subArr, i, length);
            // 回溯，撤销之前的修改
            deep--;
        }
    }
}
