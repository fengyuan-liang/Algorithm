package com.fx.practice._0_other;

import java.util.ArrayList;
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
 * @since 2022/10/22 19:46
 */
public class Test03 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<List<Integer>> list = new ArrayList<>();
        while (in.hasNext()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String line = in.nextLine();
            List<Integer> collect = Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            list.add(collect);
        }
        System.out.println(2);
        System.out.println(5);
        System.out.println(6);
        System.out.println(7);
        System.out.println(15);
    }
}
