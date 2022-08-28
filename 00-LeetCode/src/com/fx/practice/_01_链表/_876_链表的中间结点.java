package com.fx.practice._01_链表;

import com.fx.practice.utils.ListNode;

/**
 * @author: 梁峰源
 * @date: 2022/1/7 20:23
 * Description:https://leetcode-cn.com/problems/middle-of-the-linked-list/
 */
public class _876_链表的中间结点 {

    /**
     * 快慢指针
     */
    public ListNode middleNode2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 暴力破解
     */
    public ListNode middleNode(ListNode head) {
        int size = 1;
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
            size++;
        }
        if (size % 2 == 0) {
            size = size / 2 + 1;
            for (int i = 0; i < size - 1; i++) {
                head = head.next;
            }
        } else {
            size = size / 2;
            for (int i = 0; i < size; i++) {
                head = head.next;
            }
        }
        return head;
    }
}
