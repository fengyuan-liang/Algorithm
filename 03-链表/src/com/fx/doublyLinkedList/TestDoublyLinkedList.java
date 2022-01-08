package com.fx.doublyLinkedList;

import org.junit.Test;

/**
 *
 */
public class TestDoublyLinkedList {
    @Test
    public void test01(){
        LinkList<Integer> list = new LinkList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        list.remove(99);
        System.out.println(list.toString());
    }
}
