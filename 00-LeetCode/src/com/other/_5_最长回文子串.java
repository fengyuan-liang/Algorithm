package com.other;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * https://leetcode.cn/problems/longest-palindromic-substring/
 *
 * @since: 2022/5/31 9:54
 * @author: 梁峰源
 */
public class _5_最长回文子串 {
    static int  count= 0;
    public static void main(String[] args) throws InterruptedException {
        System.out.println("1...");
        Thread.sleep(10000);
        System.out.println("2...");
        Thread.sleep(10000);
        System.out.println("3...");
        System.gc();
        Thread.sleep(Integer.MAX_VALUE);
    }



    public static String longestPalindrome2(String s) {
        return s.substring(s.indexOf("("), s.lastIndexOf(")") + 1);
    }

    /**
     * 暴力破解
     */
    public static String longestPalindrome(String s) {
        int left = 0, right = 0, len = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                System.out.println(s.substring(i, j + 1));
                if (match2(chars, i, j)) {
                    left = i;
                    right = j;
                }
            }
        }
        return left == right ? s.substring(0, 1) : s.substring(left, right + 1);
    }

    private static boolean match(char[] chars, int left, int right) {
        if (chars[left] != chars[right]) return false;
        if (chars[left] == chars[right] && right - left <= 1) return true;
        //递归比较中间是否相等
        return match(chars, left + 1, right - 1);
    }

    private static boolean match2(char[] chars, int left, int right) {
        if (right <= left) return false;
        if ((chars[left] == 40 && chars[right] == 41
                || chars[left] == 41 && chars[right] == 40)
                && right - left == 1 ) return true;
        //递归比较中间是否相等
        return match2(chars, left + 1, right - 1);
    }

    public String longestPalindromeByOfficial(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
