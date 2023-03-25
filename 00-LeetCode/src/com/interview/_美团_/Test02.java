package com.interview._美团_;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 小美因乐于助人的突出表现获得了老师的嘉奖。老师允许小美从一堆n个编号分别为1,2,...,n的糖果中选择任意多个糖果作为奖励（每种编号的糖果各一个），但为了防止小美一次吃太多糖果有害身体健康，老师设定了一个限制：如果选择了编号为 i 的糖果，那么就不能选择编号为 i-1, i-2, i+1, i+2的四个糖果了。在小美看来，每个糖果都有一个对应的美味值，小美想让她选出的糖果的美味值之和最大！作为小美的好朋友，请你帮帮她！
 * in
 * 7
 * 3 1 2 7 10 2 4
 * out
 * 14
 * reason
 * 最优的方案是选择编号为1，4，7的糖果
 * 如果选了编号为5的美味值为10的那颗糖果，最多能获得的美味值仅为13，不如上述方案优
 */
public class Test02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = Integer.parseInt(sc.nextLine());
        List<Integer> list = Arrays.stream(sc.nextLine().split(" "))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());
        int max = Integer.MIN_VALUE;
        int swap = 0;
        for (int i = 0; i < cnt; i++) {
            for (int j = i; j < cnt && cnt - j > 0; j+=3) {
                swap += list.get(j);
            }
            if (swap > max) {
                max = swap;
            }
            swap = 0;
        }
        System.out.println(list);
        System.out.println(max);
    }
}
