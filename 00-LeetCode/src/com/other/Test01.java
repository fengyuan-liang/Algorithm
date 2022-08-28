package com.other;

/**
 * 示例 1：
 * <p>
 * 输入：[1,4,3,2]
 * <p>
 * 输出：6
 * <p>
 * 解释：抓取 2 号物品 (金额 = 4) ，然后抓取 4 号物品 (金额 = 2)。
 * <p>
 * 抓取到的最高金额 = 4 + 2 = 6 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：[2,7,1,3,5]
 * <p>
 * 输出：12
 * <p>
 * 解释：抓取 2 号物品 (金额 = 7), 抓取 5 号物品 (金额 = 5)。
 * <p>
 * 抓取到的最高金额 = 7 + 5 = 12 。
 *
 * @author 梁峰源
 * @since 2022/8/7 19:38
 */
public class Test01 {
    public static void main(String[] args) {

    }

    int getMaxSum(int[] arr) {
        int var1 = 0, var2 = 0, maxSum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] > maxSum) {
                    var1 = i;
                    var2 = j;
                }
            }
        }

        return arr[var1] + arr[var2];
    }


}
