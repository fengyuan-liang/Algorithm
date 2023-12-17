package com.fx.util;

import java.util.Arrays;

/**
 * TODO
 *
 * @author liangfengyuan1024@gmail.com
 * @version 1.0.0
 * @since 2023/12/12 23:10
 */
public class TestArrayUtil {
    public static void main(String[] args) {
        test01();
    }

    private static void test01() {
        int[] ints = ArrayUtil.randomArray(10, 10);
        System.out.println(Arrays.toString(ints));
    }
}
