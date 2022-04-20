package com.other;

import org.junit.Test;

/**
 * 388. 文件的最长绝对路径
 * https://leetcode-cn.com/problems/longest-absolute-file-path/
 */
public class _388_文件的最长绝对路径 {
    public int lengthLongestPath(String input) {
        return 0;
    }

    @Test
    public void test01() {
        String str = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        //先按照\n切割字符串
        String[] words = str.split("\n");
        //创建dp数组
        int[][] arr = new int[2][words.length + 1];
        //遍历字符串数组
        for (int cnt = 0; cnt < words.length; cnt++) {
            int index = words[cnt].lastIndexOf("\t");
            arr[0][cnt] = index + 1; //表示包含多少个制表符
            if (arr[0][cnt] == 0) {
                arr[1][cnt] = words[cnt].length() + arr[0][cnt] * 2;
            } else {
                //n个空格需要加上之前最近的n-1个空格的字符，以此类推
                arr[1][cnt] = findIndex(arr,cnt,arr[0][cnt]) + words[cnt].length() + arr[0][cnt] * 2 + 2 * cnt;
            }
        }
        int largeLen = 0;
        for (int i = 0; i <= arr.length; i++) {
            if(arr[1][i] > largeLen)
                largeLen = arr[1][i];
        }
        System.out.println(largeLen);
    }

    /**
     * 寻找比当前层数小一层的字符串的长度
     * @param arr dp数组
     * @param cnt 计数器
     * @param index 表示层数
     * @return 返回找到字符串的长度
     */
    private int findIndex(int[][] arr,int cnt,int index){
        while(--cnt >= 0){
            if(arr[0][cnt] < index){
                return arr[1][cnt];
            }
        }
        return 0;
    }
}
