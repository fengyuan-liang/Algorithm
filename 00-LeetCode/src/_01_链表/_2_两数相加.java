package _01_链表;


import sun.security.provider.Sun;

/**
 * @author: 梁峰源
 * @date: 2022/1/8 14:32
 * Description:https://leetcode-cn.com/problems/add-two-numbers/
 *  Integer.MAX_VALUE=2147483647
 *  Long.MAX_VALUE=9223372036854775807
 */
public class _2_两数相加 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(1);
        ListNode head = l2;
        for (int i = 0; i < 9; i++) {
            l2.next=new ListNode(9);
            l2=l2.next;
        }
        ListNode listNode = addTwoNumbers2(l1, head);
        StringBuilder sbd = new StringBuilder();
        while (listNode != null) {
            sbd.append(listNode.val);
            listNode = listNode.next;
        }
        System.out.println(sbd.toString());
    }

    /**
     * 链表+初等数学
     */
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode head = null,tail=null;
        int carry=0;//进位
        int num1,num2,sum;
        while (l1!=null || l2!=null){
            num1= l1 !=null ? l1.val : 0;
            num2= l2 !=null ? l2.val : 0;
            sum=(num1+num2+carry)%10;
            if(head == null){
                head = tail =new ListNode(sum);
            }else {
                tail.next = new ListNode(sum);
                tail = tail.next;
            }
            carry=(num1+num2+carry)/10;
            if(l1 != null){
                l1=l1.next;
            }
            if(l2 != null){
                l2=l2.next;
            }
        }
        if(carry>0) tail.next = new ListNode(carry);
        return head;
    }


    /**
     * 这段代码让我想到了刚学c语言的时候也是这样做的，笑死，那个时候这个题叫大数相加
     */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        StringBuilder sbd1 = new StringBuilder();
        StringBuilder sbd2 = new StringBuilder();
        while (l1 != null) {
            sbd1.append(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            sbd2.append(l2.val);
            l2 = l2.next;
        }
        long num1, num2;
        if (sbd1.toString().equals("")) {
            num1 = 0L;
        } else {
            num1 = Long.parseLong(sbd1.reverse().toString());
        }
        if (sbd1.toString().equals("")) {
            num2 = 0L;
        } else {
            num2 = Long.parseLong(sbd2.reverse().toString());
        }
        //反转两个字符串
        Long total = num1 + num2;
        char[] arr = (total + "").toCharArray();
        ListNode list = new ListNode(Integer.parseInt(String.valueOf(arr[arr.length - 1])));
        ListNode head = list;
        //倒序数组
        for (int i = arr.length - 2; i >= 0; i--) {
            list.next = new ListNode(Integer.parseInt(String.valueOf(arr[i])));
            list = list.next;
        }
        return head;
    }
}
