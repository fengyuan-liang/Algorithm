package com.nowcoder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class _3_整数列表求三的倍数 {
    public static void main(String[] args) {
        int num = getNum(5);
        System.out.println(num);
    }

    private static int getNum(int n) {
        // 先生成整数列表
        int cnt = 1;//计数器
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        int size;
        // 当集合中只有一个元素时退出循环
        while ((size = list.size()) != 1) {
            // 遍历集合
            Iterator<Integer> iterator = list.iterator();
            while (iterator.hasNext()){
                iterator.next();
                if(cnt ++ % 3 ==0){
                    iterator.remove();
                }
            }
        }
        return list.get(0);
    }
}
