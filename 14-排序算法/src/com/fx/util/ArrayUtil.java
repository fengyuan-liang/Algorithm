package com.fx.util;

import java.util.Random;

/**
 * TODO
 *
 * @author liangfengyuan1024@gmail.com
 * @version 1.0.0
 * @since 2023/12/12 23:05
 */
public class ArrayUtil {
    private ArrayUtil() {
        throw new IllegalArgumentException("cannot create private construct");
    }

    public static int[] randomArray(int arrayLength, int rightBoundary) {
        Random random = new Random();
        int[] arr = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            arr[i] = random.nextInt(rightBoundary);
        }
        return arr;
    }
}
