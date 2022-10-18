package com.fx.practice._04_backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * <p>
 * 你可以按 任何顺序 返回答案
 * </p>
 *
 * @author 梁峰源
 * @since 2022/9/14 23:45
 */
public class _77_组合 {
    public static void main(String[] args) {
        _77_组合 obj = new _77_组合();
        List<List<Integer>> combine = obj.combine(4, 4);
        System.out.println(combine);
    }

    public List<List<Integer>> combine(int n, int k) {
        // 从1开始的
        backtracking(n, k, 1);
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    void backtracking(int n, int k, int startIndex) {
        // 递归要有递归出口
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= n; i++) {
            // 添加逻辑
            path.add(i);
            // 递归调用，并且传递下一层递归的起始的元素
            backtracking(n, k, i + 1);
            // 回溯，将集合清空
            path.removeLast();
        }
    }
}
