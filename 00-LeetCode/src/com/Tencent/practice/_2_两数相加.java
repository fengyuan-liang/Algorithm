package com.Tencent.practice;


import com.fx.practice._01_链表.ListNode;

import java.util.Objects;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 */
public class _2_两数相加 {
    public static void main(String[] args) {
        ListNode l1 = null,l2 = null;
        for (int i = 0; i < 9; i++) {
            l1 = new ListNode(i);
            if( l2 == null) l2 = l1;
            System.out.print(l1.val);
            l1 = l1.next;

        }
        while (l2 != null){
            System.out.println(l2.val);
            l2 = l2.next;
        }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;//标记一下链表的头和尾
        int sum, num1, num2;//当前位数的和、两个链表当前结点保存的数
        int carry = 0;//进位
        while (!Objects.isNull(l1) || !Objects.isNull(l2)) {
            num1 = l1 == null ? 0 : l1.val;
            num2 = l2 == null ? 0 : l2.val;
            sum = (num1 + num2 + carry) % 10;
            carry = (num1 + num2 + carry) / 10;
            if (head == null) {
                tail = new ListNode(sum);
                head = tail;
            } else {
                tail.next = new ListNode(sum);
                tail = tail.next;
            }
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry > 0) tail.next = new ListNode(carry);
        return head;
    }
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        // 课程 A -》 链表 -> 基础篇
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;

            int total = x + y + carry;
            curr.next = new ListNode(total % 10);
            // bug 修复：视频中忘了移动 curr 指针了
            curr = curr.next;
            carry = total / 10;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry != 0) curr.next = new ListNode(carry);
        return dummy.next;
    }
}

