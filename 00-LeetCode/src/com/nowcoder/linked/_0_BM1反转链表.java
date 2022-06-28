package com.nowcoder.linked;

import com.nowcoder.utils.ListNode;
import com.nowcoder.utils.ListUtil;

import java.util.Stack;

/**
 * @Description:
 * @since: 2022/6/7 9:43
 * @author: 梁峰源
 */
public class _0_BM1反转链表 {
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode flag = head;
        for (int i = 1; i < 6; i++) {
            head.next = new ListNode(i);
            head = head.next;
        }
        ListUtil.printList(flag);
        ListNode listNode = ReverseList3(flag);
        System.out.println();
        ListUtil.printList(listNode);
    }

    /**
     * 双指针
     */
    public static ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = null, next = head.next;
        while (next != null) {
            head.next = pre;
            pre = head;
            head = next;
            next = next.next;
        }
        head.next = pre;
        return head;
    }

    public static ListNode ReverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = null, next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 栈实现
     */
    public static ListNode ReverseList3(ListNode head) {
        if (head == null || head.next == null) return head;
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode temp, pop = stack.pop();
        ListNode dummy = pop;
        while (!stack.isEmpty()){
            temp = stack.pop();
            pop.next = temp;
            pop = temp;
        }
        //注意这里，pop现在是第一个元素了，要让他指向null，不为会成为环
        pop.next = null;
        return dummy;
    }

    /**
     * 递归
     */
    public static ListNode ReverseList4(ListNode head){
        //终止条件
        if (head == null || head.next == null)
            return head;
        //保存当前节点的下一个结点
        ListNode next = head.next;
        //从当前节点的下一个结点开始递归调用
        ListNode reverse = ReverseList4(next);
        //reverse是反转之后的链表，因为函数reverseList
        // 表示的是对链表的反转，所以反转完之后next肯定
        // 是链表reverse的尾结点，然后我们再把当前节点
        //head挂到next节点的后面就完成了链表的反转。
        next.next = head;
        //这里head相当于变成了尾结点，尾结点都是为空的，
        //否则会构成环
        head.next = null;
        return reverse;
    }
}
