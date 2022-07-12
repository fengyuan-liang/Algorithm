package com.fx.linkedList;

import org.junit.Test;


/**
 * @author: 梁峰源
 * @date: 2022/1/5 23:55
 * Description:
 */
public class TestLinkList {
    @Test
    public void test2(){
        List<Integer> list=new LinkedList2<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        list.remove(21);
        System.out.println(list.toString());
        System.out.println(list.contains(null));
        System.out.println(list.contains(21));
        System.out.println(list.isEmpty());
        System.out.println(list.indexOf(21));
        System.out.println(list.indexOf(20));
        list.clear();
        System.out.println(list.toString());
    }


    @Test
    public void test01(){
        List<Integer> list=new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            list.add(i);
        }
        list.remove(21);
        list.add(0,100);
        System.out.println(list.toString());
    }


    @Test
    public void test03() {
        List<Integer> list = new LinkedList2<>();
        list.add(10);
        list.add(10);
        list.add(10);
        System.out.println(list.toString());
    }
}
