package com.other;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author liangfengyuan1024@gmail.com
 * @version 1.0.0
 * @since 2023/12/18 23:19
 */
public class _5最长回文子串_ {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("abcacbccc"));
    }


    public static String longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] charArray = s.toCharArray();
        String maxStr = "";
        int leftIndex = 0; // 用来记录左边指针的位置
        for (int i = 0; i < charArray.length; i++) {
            // 如果出现了，则此子串结束
            if (map.containsKey(charArray[i]) && s.substring(leftIndex, i).length() > maxStr.length()) {
                // 截取最长字串
                maxStr = s.substring(leftIndex, i);
                leftIndex = i;
                // 清空map
                map.clear();
            }
            // 放入map中
            map.put(charArray[i], i);
        }

        return maxStr;
    }
}
