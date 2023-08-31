package com.day;


import org.junit.jupiter.api.Test;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源 <fengyuan-liang@foxmail.com>
 * @since 2023/3/17 23:14
 */
public class day1 {
    @Test
    public void test01() {
        System.out.println(search(new int[]{2, 5}, 2));
    }

    public int search(int[] nums, int target) {
        return binaryFind(nums.length / 2, nums.length, nums, target);
    }

    private int binaryFind(int index, int endIndex, int[] nums, int target) {
        int end = index;
        // 递归出口
        if (index == 0 || (index == endIndex - 1 && nums.length > 2)) {
            if (nums[index] == target) {
                return index;
            } else {
                return -1;
            }
        }
        if (nums[index] < target) {
            // 往后边
            index = (index + endIndex) / 2;
            return binaryFind(index, endIndex, nums, target);
        } else if (nums[index] > target) {
            // 往左边
            index = index / 2;
            return binaryFind(index, end, nums, target);
        } else {
            return index;
        }
    }

    @Test
    public void Test02() {
        System.out.println((int) Math.ceil(12 / 3.0));
    }
}
