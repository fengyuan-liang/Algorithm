package com.fx.practice._04_backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 找出所有相加之和为 `n` 的 `k` 个数的组合，且满足下列条件：
 * <p>
 * - 只使用数字1到9
 * - 每个数字 **最多使用一次**
 * 返回 *所有可能的有效组合的列表* 。该列表不能包含相同的组合两次，组合可以以任何顺序返回
 *
 * @author 梁峰源
 * @since 2022/10/18 0:30
 */
public class _216_组合总和_III {

    public static void main(String[] args) {
        List<List<Integer>> combine = combine(9, 3);
        System.out.println(combine);
    }

    public static List<List<Integer>> combine(int n, int k) {
        // 从1开始计数
        backtracking(n, k, 1, 0);
        return result;
    }

    private static final List<List<Integer>> result = new ArrayList<>(); // 用来存储每一条链路的结果
    private static final LinkedList<Integer> path = new LinkedList<>(); // 用来存储递归每一层的元素

    public static void backtracking(int n, int k, int startIndex, int sum) {
        if (sum > n) {
            return;
        }
        if (path.size() > k) {
            return;
        }
        // 递归要有递归出口
        // 以及是k个数了，判断是否满足要求
        if (sum == n && path.size() == k) {
            // 满足要求加入到结果集中
            result.add(new ArrayList<>(path));
            return;
        }

        // 开始横向递归
        for (int i = startIndex; i <= 9; i++) {
            // 将当前深度的结点记录
            path.add(i);
            sum += i;
            // 纵向递归调用
            backtracking(n, k, i + 1, sum);
            sum -= i;
            // 回溯，清空上一层的结点
            path.removeLast();
        }
    }
}
