package com.fx.practice._01_链表;

import com.fx.practice.utils.LinkedListUtil;
import com.fx.practice.utils.ListNode;

/**
 * @author: 梁峰源
 * @date: 2022/1/6 21:56
 * Description:https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class _206_反转链表 {

    public static void main(String[] args) {
        ListNode head = LinkedListUtil.ofRandomLinkedList(5, 100);
        LinkedListUtil.print(head);
        ListNode listNode = reverseList(head);
        LinkedListUtil.print(listNode);
    }

    /**
     * 非递归（迭代法）
     */
    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = null;
        ListNode temp;
        while (head != null) {
            temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }

    /**
     * 递归实现
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newNode;
    }
}
