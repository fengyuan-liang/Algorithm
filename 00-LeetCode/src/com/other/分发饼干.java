package com.other;

import java.util.Arrays;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源 <fengyuan-liang@foxmail.com>
 * @since 2022/12/14 0:49
 */
public class 分发饼干 {
    public static void main(String[] args) {
        int[] g = new int[]{1, 2, 3};
        int[] s = new int[]{3};
        System.out.println(findContentChildren(g, s));
    }

    public static int findContentChildren(int[] g, int[] s) {
        // 将饼干排序
        Arrays.sort(g);
        Arrays.sort(s);
        int cnt = 0;
        int index = s.length - 1;
        // 贪心 尽可能用大尺寸饼干满足胃口最大的人
        for (int i = g.length - 1; i >= 0; i--) {
            if (index >= 0 && g[i] <= s[index]) {
                cnt++;
                index--;
            }
        }
        return cnt;
    }

}
