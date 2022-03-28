package com.fx.practice._03_二叉树;

import com.fx.practice.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-node-in-a-bst
 */
public class _450_删除二叉搜索树中的节点 {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        TreeNode left = node.left = new TreeNode(3);
        TreeNode right = node.right = new TreeNode(6);
        left.left = new TreeNode(2);
        left.right = new TreeNode(4);
        right.right = new TreeNode(7);
        leverOrderTraversal(deleteNode(node,5));
    }


    public static TreeNode deleteNodeByInorderTraversal(TreeNode root, int key) {
        return null;
    }

    /**
     * 层序遍历  未解决
     */
    public static TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(root.val == key && root.right == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        //根结点如栈
        queue.offer(root);
        TreeNode node = root,preNode;
        while(!queue.isEmpty()){
            //对元素的操作
            preNode = node; //保留上一次的结点
            node = queue.poll();
            //进行判断
            if(node.val == key){
                if(node.right != null){
                    node.right.left = node.left;
                }
                if(node != root){
                    if(preNode.left != null && preNode.left == node){
                        preNode.left = node.right;
                    }else if(preNode.right != null){
                        preNode.right = node.right;
                    }
                }else{
                    root = node.right;
                }
            }
            //将其左右子树入队,这里要收集null值
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return root;

    }

    public static void leverOrderTraversal(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        //将根结点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            //出队
            TreeNode node = queue.poll();

            //回调，将处理遍历数据的业务交给调用者
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }
}
