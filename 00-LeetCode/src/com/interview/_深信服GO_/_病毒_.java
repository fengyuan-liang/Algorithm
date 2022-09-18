package com.interview._深信服GO_;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源
 * @since 2022/9/16 20:39
 */
public class _病毒_ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = sc.nextInt(),var = sc.nextInt();
        sc.next();
        List<Integer> list = Arrays.stream(sc.next().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int count = 0;
        for (int var1 : list) {
        	if(Math.abs(var1 - cnt) <= var){
                count++;
            }
        }
        System.out.println(count);
    }
}
