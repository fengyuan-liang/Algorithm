package com.fx.behaviorPattern.strategyPattern;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: 梁峰源
 * @date: 2021/11/4 21:41
 * Description:在JDK中的Comparator中运用到了策略接口（策略模式）
 */
public class JdkStrategy {
    public static void main(String[] args) {
        Integer[] data={9,1,2,8,4,3};
        //需求，实现升序排列，返回-1放左边，1放右边，0保持不变
        //实现了策略接口的对象
        Comparator<Integer> comparator= (o1, o2) -> {
            if(o1>o2) {
                return 1;//自定义策略
            }else if(o1<o2){
                return -1;
            }else {
                return 0;
            }
        };
        Arrays.sort(data,comparator);
        System.out.println(Arrays.toString(data));
    }
}
