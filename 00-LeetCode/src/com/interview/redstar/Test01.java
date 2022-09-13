package com.interview.redstar;

import java.util.Random;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源
 * @since 2022/9/7 21:23
 */
public class Test01 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 运动员可得到的最高分
     * @param energy int整型 运动员体力值
     * @param actions int整型二维数组 二维数组i为动作号 actions[i][0]为动作i+1消耗体力,actions[i][1]为动作i+1得分
     * @return int整型
     */
    public int maxScore (int energy, int[][] actions) {
        // write code here
        // 定义一个dp数组
        int max = 0;
        for (int i = actions[0].length - 1; i > 0; i--) {
            int cnt = actions[i][0];
            int var = 0;
            for (int[] ints : actions) {
                if (cnt + actions[i][0] <= energy) {
                    cnt += actions[i][0];
                    var = var + actions[i][1] + ints[1];
                }
            }
            if(max < var){
                max = var;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            System.out.println(random.nextInt(2));
        }
    }
}


















