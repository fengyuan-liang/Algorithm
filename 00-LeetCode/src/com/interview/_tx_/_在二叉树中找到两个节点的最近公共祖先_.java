package com.interview._tx_;

import java.util.Scanner;

/**
 * 给定一棵二叉树以及这棵树上的两个节点 o1 和 o2，请找到 o1 和 o2 的最近公共祖先节点。
 *
 * @author 梁峰源
 * @link https://www.nowcoder.com/questionTerminal/c75deef6d4bf40249c785f240dad4247
 * @since 2022/11/4 23:36
 */
public class _在二叉树中找到两个节点的最近公共祖先_ {

    private static final int[] arr = new int[50_0000];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int totalNodeNum = Integer.parseInt(str.split(" ")[0]), root = Integer.parseInt(str.split(" ")[1]);
        arr[root] = 0;
        int node1 = 0, node2 = 0;
        while (totalNodeNum > 0 && sc.hasNext()) {
            // 第一个元素是后面元素的父结点
            String[] strs = sc.nextLine().split(" ");
            if (strs.length == 3) {
                int father = Integer.parseInt(strs[0]);
                int left = Integer.parseInt(strs[1]);
                int right = Integer.parseInt(strs[2]);
                // 只需要管左子树和右子树的结点
                if (left != 0) {
                    arr[left] = father;
                    totalNodeNum--;
                }
                if (right != 0) {
                    arr[right] = father;
                    totalNodeNum--;
                }
            } else {
                node1 = Integer.parseInt(strs[0]);
                node2 = Integer.parseInt(strs[1]);
                totalNodeNum--;
            }
        }
        // 拿到要寻找公共结点的两个结点
        // 先计算结点往上遍历一共需要多少步
        while (arr[node1] != 0) {
            int var = node2;
            while (arr[var] != 0) {
                if (arr[node1] == arr[var]) {
                    System.out.println(arr[node1]);
                    System.exit(1);
                }
            }
        }
    }
}
