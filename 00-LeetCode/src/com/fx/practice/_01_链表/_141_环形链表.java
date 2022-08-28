package com.fx.practice._01_链表;

import com.fx.practice.utils.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: 梁峰源
 * @date: 2022/1/7 12:04
 * Description:https://leetcode-cn.com/problems/linked-list-cycle/
 */
public class _141_环形链表 {

    /**
     * 哈希表
     * 时间复杂度：o(n)
     * 空间复杂度：o(n)n 为结点个数
     */
    public boolean hasCycle2(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 快慢指针
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast == null || fast.next == null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
