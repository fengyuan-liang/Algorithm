package com.fx.practice._0_other;

import java.util.Arrays;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源 <fengyuan-liang@foxmail.com>
 * @since 2023/7/16 20:35
 */
public class _736每日温度 {

    public static void main(String[] args) {
        int[] arr = {73, 74, 75, 71, 69, 72, 76, 73};

        System.out.println(Arrays.toString(dailyTemperatures(arr)));
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        int[] arr = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            // 如果跟前一个元素一样，直接使用
            if (i > 1 && temperatures[i] == temperatures[i - 1]) {
                arr[i] = Math.max(arr[i - 1] - 1, 0);
                continue;
            }
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    arr[i] = j - i;
                    break;
                }
            }
        }
        return arr;
    }
}
