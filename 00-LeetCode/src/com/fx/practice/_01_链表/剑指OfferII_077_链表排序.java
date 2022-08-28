package com.fx.practice._01_链表;

import com.fx.practice.utils.ListNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: 梁峰源
 * @date: 2022/1/8 22:28
 * Description:https://leetcode-cn.com/problems/7WHec2/
 * 该题是三个小题的组合：归并排序 + 双指针找单链表中点 + 合并两个排序链表。
 */
public class 剑指OfferII_077_链表排序 {
    public static void main(String[] args) {
        ListNode node = new ListNode(11);
        ListNode head = null;
        for (int i = 10; i > 0; i--) {
            node.next = new ListNode(i);
            if (head == null) {
                head = node;
            }
            node = node.next;
        }
        ListNode listNode = sortList(head);
        StringBuilder sbd = new StringBuilder();
        while (listNode != null) {
            sbd.append(listNode.val);
            listNode = listNode.next;
        }
        System.out.println(sbd.toString());
    }


    /**
     * 无耻的使用Java API
     */
    public static ListNode sortList(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode node = null;
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        List<ListNode> collect = list.stream()
                .sorted(Comparator.comparingInt(o -> o.val))
                .collect(Collectors.toList());
        for (ListNode listnode : collect) {
            if (node == null) {
                node = new ListNode(listnode.val);
                head = node;
            } else {
                node.next = new ListNode(listnode.val);
                node = node.next;
            }
        }
        return head;
    }
}
