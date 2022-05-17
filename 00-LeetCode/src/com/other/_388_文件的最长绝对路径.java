package com.other;

import org.junit.Test;

import java.util.Arrays;

/**
 * 388. 文件的最长绝对路径
 * https://leetcode-cn.com/problems/longest-absolute-file-path/
 */
public class _388_文件的最长绝对路径 {

    @Test
    public void test03(){
        int i = lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext");
        System.out.println(i);
    }

    public int lengthLongestPath(String input) {
        if(input==null||input.length()==0) return 0;
        String[] words=input.split("\n");
        int[] pathLens=new int[words.length+1];//pathLens[i]存放地i层次的最后面的元素的路径长度
        pathLens[0]=-1;//层次最少是1，为了统一dp操作（具体看下面循环体），取pathLens[0]=-1
        int ans=0;
        //自左向右，dp
        for(String word:words){
            int level=word.lastIndexOf('\t')+1+1;//层次计算
            int nameLen=word.length()-(level-1);//计算名字长度
            //word的父文件夹必定目前是level-1层次的最后一个，因此pathLens[level-1]就是父文件夹路径长度
            //这个word必然是目前本层次的最后一个，因此需要刷新pathLens[level],+1是因为要加一个'\'
            pathLens[level]=pathLens[level-1]+1+nameLen;
            //如果是文件，还需要用路径长度刷新ans
            if(word.contains(".")) ans=Math.max(ans,pathLens[level]);
        }
        return ans;
    }

    @Test
    public void test02() {
        System.out.println("file.ext".length());
    }

    @Test
    public void test01() {
        String str = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        //先按照\n切割字符串
        String[] words = str.split("\n");
        System.out.println(Arrays.toString(words));
        //创建dp数组
        int[][] arr = new int[2][words.length];
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
        System.out.println(Arrays.deepToString(arr));
        int largeLen = 0;
        for (int i = 0; i < arr.length; i++) {
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
