package com.fx.practice._02_栈_队列;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author: 梁峰源
 * @date: 2022/1/14 23:03
 * Description:https://leetcode-cn.com/problems/valid-parentheses/
 */
public class _20_有效的括号 {
    public static void main(String[] args) {
        String s = "****";
        System.out.println(isValid4(s));
    }

    public static boolean isValid4(String s) {
        Stack<Character> stack = new Stack<>();
        // 拿到字符数组
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(' || c == '{' || c == '[') {
                // 入栈
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                // 出栈
                if (stack.isEmpty()) return false;
                char pop = stack.pop();
                // 如果是 * ，可以匹配任意字符，直接跳过
                if (pop == '*') continue;
                if (c == ')' && pop != '(') {
                    return false;
                } else if (c == ']' && pop != '[') {
                    return false;
                } else if (c == '}' && pop != '{') {
                    return false;
                }
            } else {
                // char为 * 的情况，这种要单独进行处理
                // 如果栈为空，将*入栈
                if (stack.isEmpty()) {
                    stack.push(c);
                } else {
                    // 因为 * 可以匹配任意符号，所以直接出栈一个符号
                    stack.pop();
                }
            }
        }
        return stack.isEmpty() || stack.contains('*');
    }

    //使用hashMap优化
    public static boolean isValid3(String s) {
        Map<Character, Character> map = new HashMap<>();
        //然后用map做匹配，灵活一些
        Stack<Character> stack = new Stack<>();
        //遍历字符串
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            //匹配左边的字符
            if (c == '(' || c == '[' || c == '{') {
                //入栈
                stack.push(c);
            } else {
                //匹配右边的字符
                if (stack.isEmpty()) return false;
                //弹出栈顶元素并进行比较
                char left = stack.pop();
                switch (left) {
                    case '[':
                        if (c != ']') return false;
                        break;
                    case '(':
                        if (c != ')') return false;
                        break;
                    case '{':
                        if (c != '}') return false;
                        break;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 栈解决
     */
    public static boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        //遍历字符串
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            //匹配左边的字符
            if (c == '(' || c == '[' || c == '{') {
                //入栈
                stack.push(c);
            } else {
                //匹配右边的字符
                if (stack.isEmpty()) return false;
                //弹出栈顶元素并进行比较
                char left = stack.pop();
                switch (left) {
                    case '[':
                        if (c != ']') return false;
                        break;
                    case '(':
                        if (c != ')') return false;
                        break;
                    case '{':
                        if (c != '}') return false;
                        break;
                }
            }
        }
        return stack.isEmpty();
    }


    /**
     * 正则表达式匹配||String API
     */
    public static boolean isValid(String s) {
        String pattern = "(?:\\{.*}|\\[.*]|\\(.*\\))";
        while (s.matches(pattern)) {
            s = s.replace(pattern, "");
            System.out.println(s);
        }
        System.out.println(s);
        return s.isEmpty();
    }
}
