package com.fx.practice._04_backtracking;

import java.util.*;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源
 * @since 2022/11/1 0:27
 */
public class _17_电话号码的字母组合 {
    private static final Map<Integer, char[]> DEFAULT_MAP = new HashMap<>();

    public static void main(String[] args) {
        Integer value = getInt();
        initDefaultMap();
        System.out.println();
    }

    public static List<String> letterCombinations(String str1) {
        // 回溯要有出口
        if("".equals(digits)){
            return null;
        }
        for (int i = 0; i < digits.length(); i++) {

        }
        return null;
    }

    private static Integer getInt() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if (line.contains("digits = \"\"")) {
            System.out.println(new ArrayList<Void>());
            System.exit(1);
        }
        String str = line.split("\"")[1];
        return Integer.parseInt(str.trim());
    }

    private static void initDefaultMap() {
        int cnt = 3;
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
