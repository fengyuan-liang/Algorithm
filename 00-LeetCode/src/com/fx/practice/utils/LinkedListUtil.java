package com.fx.practice.utils;

import java.util.Random;
import java.util.function.Consumer;

/**
 * <p>
 * 封装常用的链表工具
 * </p>
 *
 * @author 梁峰源
 * @since 2022/9/29 23:39
 */
public class LinkedListUtil {
    private LinkedListUtil() {
    }

    /**
     * 获得一个随机的链表
     *
     * @param length            链表长度
     * @param randomNumberRange 链表中元素取值右边界
     * @return 返回这个链表的头结点
     */
    public static ListNode ofRandomLinkedList(int length, int randomNumberRange) {
        Random random = new Random();
        ListNode head = new ListNode(random.nextInt(randomNumberRange));
        ListNode next = head;
        for (int i = 0; i <= length; ++i) {
            next.next = new ListNode(random.nextInt(randomNumberRange));
            next = next.next;
        }
        return head;

    }

    /**
     * 遍历链表
     *
     * @param head     链表的头结点
     * @param consumer 消费接口
     */
    public static void forEach(ListNode head, Consumer<ListNode> consumer) {
        while (head != null) {
            consumer.accept(head);
            head = head.next;
        }
    }

    /**
     * 翻转链表后进行遍历，并返回每一个结点
     *
     * @param head     链表的头结点
     * @param consumer 消费接口
     */
    public static void reverseForEach(ListNode head, Consumer<ListNode> consumer) {
        if (head == null || head.next == null) return;
        // 迭代翻转链表
        ListNode pre = null, next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        // 此时pre就是头结点
        forEach(pre, consumer);
    }

    /**
     * 打印链表
     *
     * @param head 头结点
     */
    public static void print(ListNode head) {
        forEach(head, node -> {
            if (node.next == null) {
                System.out.println(node.val);
            } else {
                System.out.print(node.val + ", ");
            }
        });
    }

    /**
     * 翻转打印链表
     *
     * @param head 头结点
     */
    public static void reversePrint(ListNode head) {
        reverseForEach(head, node -> {
            if (node.next == null) {
                System.out.println(node.val);
            } else {
                System.out.print(node.val + ", ");
            }
        });
    }
}
