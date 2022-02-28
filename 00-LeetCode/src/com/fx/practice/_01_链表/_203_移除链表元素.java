package com.fx.practice._01_链表;

/**
 * @author: 梁峰源
 * @date: 2022/1/7 12:56
 * Description:https://leetcode-cn.com/problems/remove-linked-list-elements/
 */
public class _203_移除链表元素 {
    /**
     * 非递归（迭代）
     */
    public ListNode removeElements2(ListNode head, int val) {
        ListNode dummyHead=new ListNode(0);
        dummyHead.next=head;
        ListNode prev=dummyHead;
        while (prev.next != null){
            if(prev.next.val==val){
                prev.next=prev.next.next;
            }else {
                prev=prev.next;
            }
        }
        return dummyHead.next;
    }

    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val==val){
            head=head.next;
        }
        if(head==null) return null;
        //确保当前结点后还有结点
        ListNode newHead=head;
        while (head.next!=null){
            if(head.next.val==val){
                head.next=head.next.next;
            }else {
                head=head.next;
            }
        }
        return  newHead;
    }
}
