package com.day;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author liangfengyuan1024@gmail.com
 * @version 1.0.0
 * @since 2024/3/7 22:19
 */
public class day2 {
    public static void main(String[] args) {
        List<Integer> ts = newArrayList();
    }

    private static  <T extends Object & Serializable & Comparable<T>> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public int[] divisibilityArray(String word, int m) {
        int[] result = new int[word.length()];
        int cnt = 0;
        for (int i = 0; i < word.length(); i++) {
            if (Integer.parseInt(word.substring(0, i+1)) % m == 0) {
                result[cnt++] = 1;
            } else {
                result[cnt++] = 0;
            }
        }
        return result;
    }
}
