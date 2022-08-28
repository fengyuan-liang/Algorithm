package com.nowcoder;

import java.util.*;

/**
 * <p>
 *
 * </p>
 *
 * @since: 2022/7/16 19:49
 * @author: 梁峰源
 */
public class Test {
    public static void main(String[] args) {
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        String str = "(())";
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (c == '(') {
                if (!right.isEmpty()) {
                    right.pop();
                }else {
                    left.push(c);
                }
            } else if (c == '*' && !left.isEmpty()) {
                left.pop();
            } else if (c == ')') {
                if(!left.isEmpty()){
                    left.pop();
                }else {
                    right.push(c);
                }
            }
        }
    }


    public class ListNode {
        public int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null) return null;
        boolean flag = false;
        while (pHead.val == pHead.next.val) {
            pHead = pHead.next;
            flag = true;
        }
        if (flag) pHead = pHead.next;
        ListNode preNode = pHead, tmpNode, nextNode = pHead.next;
//         while(nextNode != null){
//             // 记录一下上一个结点

//             if(nextNode.var == preNode.var){
//                 // 先记录一下出现重复的结点
//                 preNode = nextNode;
//                 while(nextNode.var == preNode)
//             }else{
//                 nextNode = nextNode.next;
//             }
//         }
        Stack<String> left = new Stack<>();
        Stack<String> right = new Stack<>();
        return null;
    }


}
