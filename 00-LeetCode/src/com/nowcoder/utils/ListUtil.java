package com.nowcoder.utils;

/**
 * @Description:
 * @since: 2022/6/7 9:49
 * @author: 梁峰源
 */
public class ListUtil {
    public static void  printList(ListNode node){
        if(node == null) throw new RuntimeException("node为空");
        while (node != null){
            System.out.print(node.val + "\t");
            node = node.next;
        }
    }
    public static void  printlnList(ListNode node){
        if(node == null) throw new RuntimeException("node为空");
        while (node.next != null){
            System.out.println(node.val);
            node = node.next;
        }
    }
}
