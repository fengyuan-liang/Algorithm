package com.fx.practice._01_链表;


import com.fx.practice.utils.ListNode;

/**
 * @author: 梁峰源
 * @date: 2022/1/6 0:36
 * Description:https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 */
public class _237_删除链表中的节点 {

    public void deleteNode(ListNode node) {
        node.val=node.next.val;
        node.next=node.next.next;
    }
}
