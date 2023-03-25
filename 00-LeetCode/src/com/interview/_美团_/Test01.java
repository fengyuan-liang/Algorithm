package com.interview._美团_;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 例如可能1号火车驶入了火车站中的休息区s，在驶出之前2号火车驶入了。那么在这种情况下，1号火车需要等待2号火车倒车出去后才能出去（显然被后面驶入的2号火车挡住了，这个休息区s只有一个出入口）。
 *
 * 出于好奇，小美统计了近些天的火车驶入驶出情况，开始统计和结束统计时休息区s中均是空的。由于中途疏忽，小美觉得自己好像弄错了几个驶入驶出顺序，想请你帮她验证一下。值得注意的是，小美虽然可能弄错了顺序，但对火车的记录是不重不漏的。
 *
 * 形式化地来形容休息区s，我们视其为一个容量无限大的空间，假设两列火车 i 和 j 同时处于休息区s中，驶入时刻Tin满足Tin(i)<Tin(j)，则驶出时间Tout必定满足Tout(i)>Tout(j)，即，先进后出。
 * in
 * 3
 * 3
 * 1 2 3
 * 1 2 3
 * 3
 * 1 2 3
 * 3 2 1
 * 3
 * 1 2 3
 * 3 1 2
 * out
 * Yes
 * Yes
 * No
 * reason
 * 对第一组数据：
 *
 * 每辆火车刚驶入便立刻驶出即可满足此记录。
 *
 * 对第二组数据：
 *
 * [ <- 休息区出口  （空 初始状态）
 *
 * [1  <- 休息区出口 （1号驶入）
 *
 * [1 2 <- 休息区出口 （2号驶入）
 *
 * [1 2 3 <- 休息区出口 （3号驶入）
 *
 * [1 2 <- 休息区出口 （3号驶出）
 *
 * [1 <- 休息区出口 （2号驶出）
 *
 * [ <- 休息区出口 （1号驶出）
 *
 * 记录可以被此种方案满足。
 *
 * 对第三组数据：
 *
 * [ <- 休息区出口  （空 初始状态）
 *
 * [1  <- 休息区出口 （1号驶入）
 *
 * [1 2 <- 休息区出口 （2号驶入）
 *
 * [1 2 3 <- 休息区出口 （3号驶入）
 *
 * [1 2 <- 休息区出口 （3号驶出）
 *
 * 发现1号被2号堵住，无法先于2号驶出。
 *
 * 可以证明，亦不存在其他驶入驶出方案使得第三组数据满足要求
 */
public class Test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int groupNum = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < groupNum; i++) {
            int stackCapacity = Integer.parseInt(sc.nextLine());
            String line = sc.nextLine();
            List<Integer> pushed = Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            line = sc.nextLine();
            List<Integer> popped = Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            Stack<Integer> stack = new Stack<>();
            for (int j = 0, k = 0; j < pushed.size(); j++) {
                stack.push(pushed.get(j));
                while (!stack.isEmpty() && Objects.equals(stack.peek(), popped.get(k))) {
                    stack.pop();
                    k++;
                }
            }
            if (stack.isEmpty()) {
                YES();
            }else {
                NO();
            }
        }
    }

    public static void YES() {
        System.out.println("Yes");
    }

    public static void NO() {
        System.out.println("No");
    }
}
