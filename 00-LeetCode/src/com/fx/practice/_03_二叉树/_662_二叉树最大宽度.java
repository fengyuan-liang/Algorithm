package com.fx.practice._03_二叉树;

import com.fx.practice.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 *
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-width-of-binary-tree
 */
public class _662_二叉树最大宽度 {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        TreeNode left = node.left = new TreeNode(3);
        TreeNode right = node.right = new TreeNode(6);
        left.left = new TreeNode(2);
        left.right = new TreeNode(4);
        right.right = new TreeNode(7);
        int sum = new _662_二叉树最大宽度().widthOfBinaryTree(node);
        sum = Math.max((sum - 1), 0);
        System.out.println(2 * sum);
    }

    Queue<TreeNode> queue = new LinkedList<>();
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        queue.offer(root);
        TreeNode node;
        int cnt;//用来记录每层的元素个数
        int sum = 0;//用来记录总层数
        while(!queue.isEmpty()){
            //先出队
            cnt = queue.size();
            System.out.println(cnt);
            sum++;
            while (cnt > 0){
                node = queue.poll();
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
                cnt--;
            }
        }
        return sum;
    }
}
