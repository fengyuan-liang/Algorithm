package com.other;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author liangfengyuan1024@gmail.com
 * @version 1.0.0
 * @since 2023/12/18 23:40
 */
public class _647回文子串_ {
    public static void main(String[] args) {
        System.out.println(countSubstrings("abcbc"));
    }

    public static int countSubstrings(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] charArray = s.toCharArray();
        int cnt = 0;
        for (int i = 0; i < charArray.length; i++) {
            // 如果出现了，则此子串结束
            if (map.containsKey(charArray[i])) {
                // 截取最长字串
                cnt++;
                // 清空map
                map.clear();
            }
            // 放入map中
            map.put(charArray[i], i);
        }
        if (cnt == 0 && map.size() == 1) {
            cnt = pow(charArray.length);
        } else {
            cnt = 1;
        }
        return cnt;
    }

    public static int pow(int num) {
        if (num  <= 1) {
            return num;
        }
        return pow(num -1) + num;
    }
}
