package com.nowcoder;

/**
 * 给出一个长度为 n 的，仅包含字符 '(' 和 ')' 的字符串，计算最长的格式正确的括号子串的长度。
 * <p>
 * 例1: 对于字符串 "(()" 来说，最长的格式正确的子串是 "()" ，长度为 2 .
 * 例2：对于字符串 ")()())" , 来说, 最长的格式正确的子串是 "()()" ，长度为 4 .
 *
 * @since: 2022/5/31 11:03
 * @author: 梁峰源
 */
public class _2_寻找匹配的括号 {

    public int longestValidParentheses(String s) {
        if (s.length() == 0) {
            return 0;
        }
        char[] st = s.toCharArray();
        int[] dp = new int[st.length];
        int pre = 0;
        int res = 0;
        for (int i = 1; i < st.length; i++) {
            if (st[i] == ')') {
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && st[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
