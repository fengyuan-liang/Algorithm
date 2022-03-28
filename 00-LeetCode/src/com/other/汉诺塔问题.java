package com.other;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/hanota-lcci/
 * 需要输出每一步移动的深度、当前步数、圆盘编号、起点-终点
 */
public class 汉诺塔问题 {
    static int cnt = 0;
    public static void main(String[] args) {
        List<Integer> original = new ArrayList<>();
        original.add(2);
        original.add(1);
        original.add(0);
        List<Integer> auxiliary = new ArrayList<>();
        List<Integer> target = new ArrayList<>();
        movePlate(3,original,auxiliary,target);
        System.out.println(target);
    }

    private static void movePlate(int num, List<Integer> original, List<Integer> auxiliary, List<Integer> target) {
        if (num == 1) {    // 只剩一个盘子时，直接移动即可
            //这里是从一号柱移到三号
            System.out.println("1---3");
            target.add(original.remove(original.size() - 1));
            return;
        }
        cnt++;
        movePlate(num - 1, original, target, auxiliary);   // 将 size-1 个盘子，从 original 移动到 auxiliary
        System.out.println("1-2深度为："+cnt);
        target.add(original.remove(original.size() - 1));   // 将 第size个盘子，从 original 移动到 target
        cnt = 0;
        movePlate(num - 1, auxiliary, original, target);   // 将 size-1 个盘子，从 auxiliary 移动到 target
        cnt++;
        System.out.println("1-3深度为："+cnt);
    }
}
