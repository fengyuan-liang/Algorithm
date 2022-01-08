package com.fx.algorithm;

import java.util.Scanner;

/**
 * @author: 梁峰源
 * @date: 2021/10/4 14:51
 * Description:
 */
public class Hanoi {
    //用于记录移动的次数
    static int m = 0;
    //展示函数
    public static void move(int disk, char M, char N) {
        System.out.println("第" + (++m) + "次操作，将" +
                disk + "号盘从" + M + "移动到" + N);
    }
    public static void hanoi(int n, char A, char B, char C) {
        if(n == 1) {
            move(n, A, C);
        }else {
            hanoi(n - 1, A, C, B);
            move(n, A, C);
            hanoi(n - 1, B, A, C);
        }
    }

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner in = new Scanner(System.in);
        System.out.println("请您输入hanoi的个数：");
        int a = in.nextInt();
        hanoi(a, 'A', 'B', 'C');
        System.out.println("总共使用" + m + "次");
    }
}

