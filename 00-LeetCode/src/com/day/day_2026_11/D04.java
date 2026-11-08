package com.day.day_2026_11;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 *
 *
 * @author Eureka <liangfengyuan1024@gmail.com>
 * @since 2026/11/7 15:26
 */
public class D04 {

    @Test
    public void test01() {
        NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(numArray.sumRange(0, 2)); // return 1 ((-2) + 0 + 3)
        System.out.println(numArray.sumRange(2, 5)); // return -1 (3 + (-5) + 2 + (-1))
        System.out.println(numArray.sumRange(0, 5)); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
    }

    class NumArray {
        private int[] s;

        public NumArray(int[] nums) {
            s = new int[nums.length + 1];
            s[0] = 0;
            for (int i = 0; i < nums.length; i++) {
                s[i + 1] = s[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return s[right + 1] - s[left];
        }
    }

    @Test
    public void test02() {
        System.out.println(numSubarraysWithSum(new int[]{1, 0, 1, 0, 1}, 2));
    }


    public int numSubarraysWithSum(int[] nums, int goal) {
        int left = 0, right = 0;
        int cnt = nums[0];
        int result = 0;
        for (; left == right && right == nums.length - 1; ) {
            if (cnt <= goal) {
                right++;
                cnt += nums[right];
            } else {
                left++;
                cnt -= nums[left];
            }
            if (cnt == goal) {
                result++;
            }
        }
        return result;
    }

    @Test
    public void test03() {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, 1, 2, 0, 5}, 3)));
    }


    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>(); // 存下标
        for (int i = 0; i < n; i++) {
            // 队头过期
            if (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }
            // 队尾维护单调递减
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }
            dq.offerLast(i);
            // 窗口形成后记录
            if (i >= k - 1) {
                res[i - k + 1] = nums[dq.peekFirst()];
            }
        }
        return res;
    }


    @Test
    public void test04() {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }


    public String minWindow(String s, String t) {
        return null;
    }


    @Test
    public void test05() {
        System.out.println(Arrays.toString(removeDuplicates(new int[]{1, 1, 1, 2, 2, 3})));
    }


    public int[] removeDuplicates(int[] nums) {
        // 入栈两个元素
        int stackSize = 2;
        for (int i = 2; i < nums.length; i++) {
            // 跟栈底每一层进行比较
            if (nums[stackSize - 2] != nums[i]) {
                nums[stackSize] = nums[i];
                stackSize++;
            }
        }
        return Arrays.copyOfRange(nums, 0, stackSize);
    }

    Map<Integer, Integer> memo = new HashMap<>();
    public int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        Integer count = memo.get(n);
        if (Objects.nonNull(count)) {
            return count;
        }
        int result = climbStairs(n - 1) + climbStairs(n - 2);
        memo.put(n, result);
        return result;
    }
}
