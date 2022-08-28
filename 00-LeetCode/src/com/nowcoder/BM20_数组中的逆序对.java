package com.nowcoder;

/**
 * <p>
 *
 * </p>
 *
 * @since: 2022/7/16 18:46
 * @author: 梁峰源
 */
public class BM20_数组中的逆序对 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 0};
        System.out.println(InversePairs(arr));
    }

    public static int InversePairs(int[] array) {
        if (array.length <= 0) return 0;
        int len = array.length;
        // 用一个数组记录一个每个数字的逆序对个数
        int[][] dp = new int[len][1];
        for (int i = 0; i < len; ++i) {
            for (int j = 0; j < i; ++j) {
                if (array[j] > array[i]) {
                    System.out.println("array[j]:" + array[j] + ",array[i]:" + array[i]);
                    System.out.println("dp[i][0]:" + dp[i][0]);
                    dp[i][0] = dp[j][0] + 1;

                }
            }
        }
        return dp[len - 1][0] % 1000000007;
    }
}

