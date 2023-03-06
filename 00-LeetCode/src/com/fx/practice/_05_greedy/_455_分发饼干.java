package com.fx.practice._05_greedy;

/**
 * https://leetcode.cn/problems/assign-cookies/
 *
 * @author 梁峰源 <fengyuan-liang@foxmail.com>
 * @since 2023/3/5 23:21
 */
public class _455_分发饼干 {
    public static void main(String[] args) {
        int[] g = new int[]{1, 2};
        int[] s = new int[]{1, 2, 3};
        System.out.println(findContentChildren(g, s));
    }

    public static int findContentChildren(int[] g, int[] s) {
        bubbleSort(g);
        bubbleSort(s);
        int cnt = 0;
        int n1 = 0, n2 = 0;
        while (n1 < g.length && n2 < s.length) {
            if (g[n1] <= s[n2]) {
                //满足
                n1++;
                n2++;
                cnt++;
            } else {
                n2++;
            }
        }
        return cnt;
    }

    private static void bubbleSort(int[] arr) {
        int tmp;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }
}
