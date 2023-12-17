package com.fx.bubbleSort;

import com.fx.util.ArrayUtil;

import java.util.Arrays;

/**
 * bubble sort
 * Stability & In-place
 *
 * @author liangfengyuan1024@gmail.com
 * @version 1.0.0
 * @since 2023/12/12 23:04
 */
public class Test01 {
    public static void main(String[] args) {
        int[] array = ArrayUtil.randomArray(10, 10);
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            boolean sorted = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    array[j] = array[j] ^ array[j + 1];
                    array[j + 1] = array[j] ^ array[j + 1];
                    array[j] = array[j] ^ array[j + 1];
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }
        }
    }
}
