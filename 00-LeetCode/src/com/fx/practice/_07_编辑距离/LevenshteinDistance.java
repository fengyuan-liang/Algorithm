package com.fx.practice._07_编辑距离;


/**
 * @author: 梁峰源
 * @date: 2022/4/15 20:45
 * @Description: 莱文斯坦距离算法实现
 */
public class LevenshteinDistance {

    public static void main(String[] args) {
        System.out.println("莱文斯坦距离:"+ editDistance("sun", "son"));
    }

    /**
     * 传入待计算莱文斯坦距离的两个字符串
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 返回两个字符串的莱文斯坦距离
     */
    public static int editDistance(String str1, String str2) {
        //创建一个二维数组，因为二维表需要多存一位，所以这里需要多申请一个空间
        int[][] distances = new int[str1.length() + 1][str2.length() + 1];
        //初始化列
        for (int i = 1; i <= str1.length(); i++) {
            distances[i][0] = i;
        }
        //初始化行
        for (int i = 0; i <= str2.length(); i++) {
            distances[0][i] = i;
        }
        //可以按行遍历也可以按列遍历，这里按列遍历
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                //第一次循环 从str1中取出第0个字符和str2中的每个字符比较
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    //这里可以取左上角的值，因为一定是最小的
                    distances[i][j] = minimum(distances[i][j - 1] + 1, distances[i - 1][j - 1], distances[i - 1][j] + 1);
                } else {
                    //全部加一取最小值等价于取最小值再加一
                    distances[i][j] = minimum(distances[i][j - 1], distances[i - 1][j], distances[i - 1][j - 1]) + 1;
                }
            }
        }
        //将得到的二维数组打印出来
//        show(distances,str2,str1);
        //返回莱文斯坦距离
        return distances[str1.length()][str2.length()];
    }

    /**
     * 返回三个数字中的最小值，依次输入数字的顺序为：左、左上、上
     */
    private static int minimum(int i, int j, int k) {
        return Math.min(i, Math.min(j, k));
    }

    /**
     * 将二维数组打印出来
     */
    private static void show(int[][] distances,String str2,String str1) {
        //输出第一个字符串
        char[] c2 = str2.toCharArray();
        System.out.print("*\t*\t");
        for(char c : c2){
            System.out.print(c+"\t");
        }
        System.out.print("\t\n*");
        char[] c1 = str1.toCharArray();
        int cnt = 0;
        boolean flag = false;
        for (int[] d : distances) {
            if(flag){
                System.out.print(c1[cnt++]+"\t");
            }else {
                flag = true;
                System.out.print("\t");
            }
            for (int i : d) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }
}
