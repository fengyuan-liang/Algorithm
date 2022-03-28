package com.fx.practice._03_二叉树;


import com.fx.practice.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 226. 翻转二叉树 https://leetcode-cn.com/problems/invert-binary-tree/
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点
 * 思路：使用层序遍历
 */
public class _226_翻转二叉树 {
    /**
     * 前序遍历
     */
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        //交换左右子树
        TreeNode tmp = new TreeNode();
        tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    /**
     * 中序遍历
     */
    public TreeNode invertTree2(TreeNode root) {
        if(root == null) return null;
        //交换左右子树
        TreeNode tmp;
        invertTree(root.left);
        tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        //注意这里已经将左右子树交换了
        invertTree(root.left);
        return root;
    }

    /**
     * 层序遍历
     */
    public TreeNode invertTree3(TreeNode root) {
        if(root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        //先将根结点入队
        queue.offer(root);
        TreeNode node,tmp;
        while (!queue.isEmpty()){
            //出队
            node = queue.poll();
            //交换元素
            tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            //将其左右子树分别入队
            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
        return root;
    }
}
