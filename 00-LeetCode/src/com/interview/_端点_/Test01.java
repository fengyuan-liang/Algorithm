package com.interview._端点_;

import java.util.Stack;

/**
 * // 题目描述:
 * // 	给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列。
 * // 括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法。
 * <p>
 * // 数据范围：字符串长度 1≤n≤10000
 * <p>
 * // 要求：空间复杂度 O(n)，时间复杂度 O(n)
 * <p>
 * // 示例1:
 * //   输入:	"["
 * //   输出:	false
 * <p>
 * // 示例2:
 * //   输入:	"{}[]"
 * //   输出:	true
 *
 * @author 梁峰源 <fengyuan-liang@foxmail.com>
 * @since 2022/12/28 20:56
 */
public class Test01 {
    public static void main(String[] args) {
        System.out.println(judge("["));
        System.out.println(judge("{[]"));
        System.out.println(judge("[()]"));
    }

    private static boolean judge(String str) {
        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            // 如果是左半边括号则入栈
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                // 右半边括号，弹出栈内一个符号进行消除
                if ((c == ')' && stack.pop() != '(')
                        || (c == '}' && stack.pop() != '{')
                        || (c == ']' && stack.pop() != '[')) {
                   return Boolean.FALSE;
                }
            }
        }
        return stack.isEmpty();
    }
}
