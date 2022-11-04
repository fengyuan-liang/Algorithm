package com.interview.redstar;

import java.util.*;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源
 * @since 2022/11/1 19:34
 */
public class Test02 {
    public static void main(String[] args) {
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param arr int整型二维数组
     * @return int整型二维数组
     */
    public static int[][] merge(int[][] arr) {
        // write code here
        List<List<Integer>> list = new ArrayList<>();
        for (int[] ints : arr) {
            List<Integer> var = new ArrayList<>();
            var.add(ints[0]);
            var.add(ints[1]);
            list.add(var);
        }
        //Iterator<Map<Integer, Integer>> iterator = list.iterator();
        for (int i = 0; i < list.size() - 1; i++) {
            if(list.get(i).get(1) > list.get(i + 1).get(0)){
                // 覆盖
                list.get(i).set(1,list.get(i + 1).get(1));
                // 将下一个移动
                list.remove(i + 1);
            }
        }
        int[][] var2 = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            var2[i][0] = list.get(i).get(0);
            var2[i][1] = list.get(i).get(1);
        }
        return var2;
    }

    private static void travel(int[][] arr, int cnt) {
        for (int i = cnt; i < arr.length; i++) {
            if (cnt == arr.length - 1) {
                arr[cnt] = null;
                break;
            }
            arr[i] = arr[i + 1];
        }
    }
}
