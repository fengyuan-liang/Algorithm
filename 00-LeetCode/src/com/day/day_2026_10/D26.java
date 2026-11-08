package com.day.day_2026_10;

/**
 *
 *
 * @author Eureka <liangfengyuan1024@gmail.com>
 * @since 2026/10/26 23:16
 */
public class D26 {
    public static void main(String[] args) {
        System.out.println(new D26().longestPalindrome("babad"));
        System.out.println(new D26().longestPalindrome("cbbd"));
        System.out.println(new D26().longestPalindrome("a"));
        System.out.println(new D26().longestPalindrome("ac"));
    }

    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        String maxStr = "";
        for (int i = 0; i < chars.length; i++) {
            {
                // 枚举奇数串
                String str1 = expandAroundCenter(chars,s, i, i);
                if (str1.length() > maxStr.length()) {
                    maxStr = str1;
                }
            }
            {
                // 枚举偶数串
                String str1 = expandAroundCenter(chars,s, i, i+1);
                if (str1.length() > maxStr.length()) {
                    maxStr = str1;
                }
            }
        }
        return maxStr;
    }

    private String expandAroundCenter(char[] chars, String s, int left, int right) {
        // 2. 相反双指针 时间复杂度O(n^2)
        while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
            left--;
            right++;
        }
        return s.substring(left+1, right);
    }

    private boolean isLoop(char[] chars, int left, int right) {
        // 1. 相向双指针 时间复杂度O(n^3)
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
