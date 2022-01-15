package com.fx.stack;


import org.junit.Test;

/**
 * @author: 梁峰源
 * @date: 2022/1/9 17:30
 * Description:
 */
public class Main {
    @Test
    public void test01(){
        System.out.println("{[]}".matches("(?:\\{.*}|\\[.*]|\\(.*\\))"));
    }
}
