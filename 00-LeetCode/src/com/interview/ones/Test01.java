package com.interview.ones;

import java.util.Scanner;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源
 * @since 2022/11/8 19:02
 */
public class Test01 {
    private static final int magicNum = (int) Math.pow(10, 9) + 7;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        // 暴力破解
        long cnt = 1;
        for (long i = 1; i < n; i++) {
            if (i % 2 == 0) {
                cnt *= 2;
            } else {
                cnt += 1 % magicNum;
            }
            cnt %= magicNum;
        }
        System.out.println(cnt);
    }
}
