package com.other;

/**
 * @author: 梁峰源
 * @date: 2022/4/18 22:40
 * @Description: TODO
 */
public class _72_编辑距离 {
    public static void main(String[] args) {
        _72_编辑距离 v = new _72_编辑距离();
        int i = v.minDistance("horse","ros");
        System.out.println(i);
    }

    public int minDistance(String word1, String word2) {
        //先定义一个二维数组
        int[][] arr = new int[word1.length() + 1][word2.length() + 1];
        //初始化数组的行和列
        for (int i = 1; i <= word2.length(); i++) {
            arr[0][i] = i;
        }
        for (int i = 0; i <= word1.length(); i++) {
            arr[i][0] = i;
        }
        //扫描数组并进行动态规划
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    arr[i][j] = arr[i - 1][j - 1];
                } else {
                    arr[i][j] = minimum(arr[i - 1][j], arr[i - 1][j - 1], arr[i][j - 1]) + 1;
                }
            }
        }
        return arr[word1.length()][word2.length()];
    }

    private int minimum(int i, int j, int k) {
        return Math.min(i, Math.min(j, k));
    }
}
