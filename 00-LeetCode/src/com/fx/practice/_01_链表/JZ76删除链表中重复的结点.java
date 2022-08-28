package com.fx.practice._01_链表;

import com.fx.practice.utils.ListNode;

/**
 * @author: 梁峰源
 * @date: 2022/1/7 19:25
 * https://www.nowcoder.com/practice/fc533c45b73a41b0b44ccba763f866ef?tpId=13&tqId=11209&ru=/exam/oj
 */
public class JZ76删除链表中重复的结点 {
    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(4);
        node.next.next.next.next.next = new ListNode(5);
        ListNode listNode = deleteDuplicates(node);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        // dummy用来标记头结点之前的结点，防止前面元素相同的情况
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // pre用来标记一定不会重复的元素
        ListNode pre = dummy;
        // last用来标记当前正在移动并且进行比较的指针
        ListNode last = dummy.next;
        while (last != null) {
            if (last.next != null && last.val == last.next.val) {
                // 说明结点相同，跳过所有相同的结点
                while (last.next != null && last.val == last.next.val) {
                    last = last.next;
                }
                // 让前一个一定不会重复的指针指向现在的last
                pre.next = last.next;
            } else {
                // 这里表示结点不同，正常往后移动
                pre = pre.next;
            }
            last = last.next;
        }
        return dummy.next;
    }
}
