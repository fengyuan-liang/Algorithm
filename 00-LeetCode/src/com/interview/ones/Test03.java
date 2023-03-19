package com.interview.ones;


import java.util.*;
import java.util.stream.Collectors;

public class Test03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] lineArr = s.split(" ");
        int n = Integer.parseInt(lineArr[0]);
        int k = Integer.parseInt(lineArr[1]);
        arr = Arrays.stream(sc.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        System.out.println(combine(n, k)
                .stream()
                .map(arr -> arr.stream().reduce(0, Integer::sum) / arr.size())
                .reduce(0, Integer::sum) / 0.4
        );
    }

    public static List<List<Integer>> combine(int n, int k) {
        // 从1开始的
        backtracking(n, k, 0);
        return result;
    }

    static List<List<Integer>> result = new ArrayList<>();
    static LinkedList<Integer> path = new LinkedList<>();

    static List<Integer> arr = new ArrayList<>();

    private static void backtracking(int n, int k, int startIndex) {
        // 递归要有递归出口
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < n; i++) {
            // 添加逻辑
            path.add(arr.get(i));
            // 递归调用，并且传递下一层递归的起始的元素
            backtracking(n, k, i + 1);
            // 回溯，将集合清空
            path.removeLast();
        }
    }
}
