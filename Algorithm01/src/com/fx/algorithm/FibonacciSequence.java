package com.fx.algorithm;

import java.util.Scanner;

/**
 * FibonacciSequence:从第3项开始，每一项都等于前两项之和组成的数列
 * 例如：1、1、2、3、5、8、13、21、34 ...
 * 题目：求FibonacciSequence第n项的值
 */
public class FibonacciSequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        do {
            System.out.println("请输入：");
            n=sc.nextInt();
        }while (n<3);
        System.out.println(fib1(n));
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    private static int fib2(int n){
        if(n <= 1) return n;
        int first=0;
        int second=1;
        int sum;
        for (int i = 0; i < n-1; i++) {
            sum=first + second;
            first = second;
            second = sum;
        }
        return second;
    }

    /**
     * 时间复杂度：O(2^n)
     * 空间复杂度：O(1)
     */
    private static int fib1(int n) {
        if(n<=1) return n;
        return fib1(n-1)+fib1(n-2);
    }
}
