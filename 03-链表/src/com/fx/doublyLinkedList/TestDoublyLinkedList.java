package com.fx.doublyLinkedList;


import com.fx.linkedList.List;
import org.testng.annotations.Test;

/**
 *
 */
public class TestDoublyLinkedList {
    @Test
    public void test01(){
        List<Integer> list = new LinkList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        for (int i = 0; i < 100; i++) {
            list.remove(0);
        }
        System.out.println(list.toString());
    }
}
