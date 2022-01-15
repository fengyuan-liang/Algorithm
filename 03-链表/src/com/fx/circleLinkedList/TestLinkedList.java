package com.fx.circleLinkedList;

import com.fx.utils.TestUtils;
import org.junit.Test;

/**
 * @author: 梁峰源
 * @date: 2022/1/9 11:13
 * Description:
 */
public class TestLinkedList {
    @Test
    public void testDoubleCircleLinkedList(){
        DoubleCircleLinkedList<Integer> list=new DoubleCircleLinkedList<>();
        TestUtils.testList(list);
        System.out.println(list);
    }

    @Test
    public void testSingleCircleLinkList(){
        SingleCircleLinkList<Integer> list = new SingleCircleLinkList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        list.add(0,101);
        list.add(2,102);
        System.out.println(list);
    }
}
