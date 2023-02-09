package com.other;

import com.fx.practice.utils.LinkedListUtil;
import com.fx.practice.utils.ListNode;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源
 * @since 2022/8/7 19:45
 */
public class test{
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,2,3,4);
        list.stream().sorted((e1,e2)->e2-e1).forEach(System.out::println);
        ListNode listNode = LinkedListUtil.ofRandomLinkedList(10, 100);
        LinkedListUtil.print(listNode);
        LinkedListUtil.reversePrint(listNode);
    }
}
