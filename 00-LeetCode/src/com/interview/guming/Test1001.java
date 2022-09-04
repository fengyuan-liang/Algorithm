package com.interview.guming;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源
 * @since 2022/9/1 20:30
 */
public class Test1001 {
    public static void main(String[] args) {
        // 保存十个数字
        // 头结点
        Node first = new Node(0);
        Node pre = first;
        System.out.print("生成结点：");
        for (int i = 1; i < 10; i++) {
            int var = (int) (Math.random() * 10);
            System.out.print("\t" + var);
            pre.next = new Node(var);
            pre = pre.next;
        }
        System.out.print("\n顺序打印结点");
        // 打印奇数结点
        pre = first;
        while (pre != null) {
            if(pre.data % 2 == 1){
                System.out.print("\t" + pre.data);
            }
            pre = pre.next;
        }
        System.out.println();
        System.out.print("逆序打印结点");
        // 逆序打印偶数结点
        reverser(first);
    }

    private static void reverser(Node node) {
        if (node == null) return;
        // 递归调用
        reverser(node.next);
        // 判断结点是否为偶数
        if(node.data % 2 == 0){
            // 打印数字
            System.out.print("\t" + node.data);
        }
    }


    static class Node {
        // 下一个结点
        Node next;
        int data;

        public Node(int data) {
            this.data = data;
        }
    }
}
