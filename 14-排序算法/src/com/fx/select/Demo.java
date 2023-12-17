package com.fx.select;

import com.fx.util.ArrayUtil;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author liangfengyuan1024@gmail.com
 * @version 1.0.0
 * @since 2023/12/17 21:54
 */
public class Demo {
    public static void main(String[] args) {
        int[] array = ArrayUtil.randomArray(10, 100);
        selectSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void selectSort(int[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException();
        }
        for (int end = arr.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int begin = 0; begin <= end; begin++) {
                if (arr[maxIndex] <= arr[begin]) {
                    maxIndex = begin;
                }
            }
            // 和最后一个元素交换位置
            int temp = arr[maxIndex];
            arr[maxIndex] = arr[end];
            arr[end] = temp;
        }
    }
}
