package com.other;

/**
 * <p>
 *
 * </p>
 *
 * @since: 2022/7/15 9:01
 * @author: 梁峰源
 */
public class _1747_任意子数组最大值 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, -3, 2, 3, -4};
        System.out.println(maxAbsoluteSum(arr));
    }

    public static int maxAbsoluteSum(int[] nums) {
        int maxNum = 0;
        for (int i = 0; i < nums.length; i++) {
            int cnt = 0;
            for (int j = i; j < nums.length; j++) {
                cnt = cnt + nums[j];
                maxNum = Math.max(maxNum, Math.abs(cnt));
            }
        }
        return Math.abs(maxNum);
    }
}
