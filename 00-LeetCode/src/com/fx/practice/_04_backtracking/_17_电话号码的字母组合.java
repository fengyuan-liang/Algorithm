package com.fx.practice._04_backtracking;

import java.util.*;

/**
 * see: <a href="https://leetcode.cn/problems/letter-combinations-of-a-phone-number/">17. 电话号码的字母组合</a>
 *
 * @author 梁峰源
 * @since 2022/11/1 0:27
 */
public class _17_电话号码的字母组合 {
    private static final Map<Integer, char[]> DEFAULT_MAP = new HashMap<>();

    static {
        initDefaultMap();
    }

    public static void main(String[] args) {
        letterCombinations("");
    }

    public static List<String> letterCombinations(String digits) {
        // 回溯要有出口
        if("".equals(digits)){
            return null;
        }
        for (int i = 0; i < digits.length(); i++) {

        }
        return null;
    }

    private static List<String> backTracking(int n, int k, int startIndex) {
        return null;
    }



    private static Integer[] getInt(String line) {
        if (line.contains("digits = \"\"")) {
            System.out.println(new ArrayList<Void>());
            System.exit(1);
        }
        String str = line.split("\"")[1];
        char[] chars = str.toCharArray();
        Integer[] arr = new Integer[str.length()];
        // 按照字符分割
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i]);
            arr[i] = Integer.parseInt(String.valueOf(chars[i]));
        }
        return arr;
    }

    private static void initDefaultMap() {
        int cnt;
        int cha = 'a';
        char[] chars;
        for (int i = 2; i <= 9; i++) {
            if (i == 9 || i == 7) {
                cnt = 4;
                chars = new char[4];
            } else {
                cnt = 3;
                chars = new char[3];
            }
            for (int j = 0; j < cnt; j++) {
                chars[j] = (char) cha++;
            }
            DEFAULT_MAP.put(i, chars);
        }
    }
}
