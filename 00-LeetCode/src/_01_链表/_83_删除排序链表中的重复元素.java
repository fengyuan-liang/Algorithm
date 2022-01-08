package _01_链表;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: 梁峰源
 * @date: 2022/1/7 19:25
 * Description:https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 */
public class _83_删除排序链表中的重复元素 {

    public ListNode deleteDuplicates2(ListNode head) {
        if(head==null) return null;
        ListNode newHead=head;
        ListNode nextNode=head.next;
        while (nextNode!=null){
            if(nextNode.val==head.val){
                //跳过这个结点
                head.next=head.next.next;
            }else {
                head=head.next;
            }
            nextNode=head.next;
        }
        return newHead;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if(head==null) return null;
        Set<Integer> set=new HashSet<>();
        ListNode newHead=head;
        //先添加第一个元素的value
        set.add(head.val);
        while (head.next!=null){
            if(!set.add(head.next.val)){
                //跳过这个元素
                head.next=head.next.next;
            }else {
                head=head.next;
            }
        }
        return newHead;
    }
}
