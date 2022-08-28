package com.other;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * <p>
 * 在遥远的国度有一位国王，他每天的工作都十分繁忙，因为每天都有许多大臣来向他汇报各种信息等。这天有n位大臣来汇报信息，其中第i位的序号为i，为了更有效的完成每天的工作，国王决定给每位大臣汇报的事情按重要性进行一个排序，让各位大臣按排序依次汇报。首先对每人的汇报在m个方面各评估一个重要性，然后每个汇报的重要性就是m个方面的重要性之和，重要性越高的汇报会排在越前面，对于重要性相同的，则按大臣的序号排序，越小的在越前面。这时，序号为id的大臣找到了你，他想请你帮他计算一下他排在第几个。
 * 第一行三个正整数n、m和id，表示大臣数量、重要性方面数量和来找你帮忙的大臣序号。
 * 接下来n行每行m个正整数，第i行为ai1, ai2,...... aim，其中aij表示序号为i的大臣的汇报在第j个方面的重要性。
 *
 * 输出一行一个正整数ans，表示序号为i的大臣排在第ans位。
 * 样例输入：
 * 3 3 2
 * 90 90 90
 * 80 100 90
 * 80 85 85
 * 输出：2
 * </p>
 *
 * @author 梁峰源
 * @since 2022/8/28 16:19
 */
public class TestPaidui {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        int weight = sc.nextInt();
        int id = sc.nextInt();
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> var = new ArrayList<>();
            for (int j = 0; j < weight; j++) {
                var.add(sc.nextInt());
            }
            // 将大臣加入集合中
           list.add(var);
        }
        System.out.println(getIndex(list,id));
    }

    private static int getIndex(List<List<Integer>> list,int id){
        // 计算权重
        List<Integer> collect = list.stream().map(var2 -> {
            AtomicInteger cnt = new AtomicInteger(0);
            var2.forEach(cnt::addAndGet);
            return cnt.get();
        }).collect(Collectors.toList());
        // 记录一下大臣的得分
        int score = collect.get(id - 1);
        // 降序排序
        AtomicInteger var5 = new AtomicInteger(1);
        AtomicInteger var11 = new AtomicInteger(0);
        List<Integer> var4 = collect.stream().sorted((e1, e2) -> {
            var5.addAndGet(1);
            if (e1.equals(e2)) {
                 if(var5.get() > id ){
                     var11.decrementAndGet();
                 }else {
                     var11.incrementAndGet();
                 }
                return var5.get() - id;
            }
            return e2 - e1;
        }).collect(Collectors.toList());
        return var4.indexOf(score) + 1 + var11.get();
    }
}
