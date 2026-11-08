package com.interview._滴滴_;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 *
 * @author Eureka <liangfengyuan1024@gmail.com>
 * @since 2026/9/7 23:19
 */

public class Test20260907 {

    public static void main(String[] args) {

    }

    public String largestNumber(int[] nums) {
        return Arrays.stream(nums)
                .mapToObj(Integer::toString)
                .sorted((a, b) -> (b + a).compareTo(a + b))
                .collect(Collectors.joining())
                .replaceAll("^0+", "0");
    }
}



