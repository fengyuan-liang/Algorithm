package com.fx.practice._03_二叉树;

import com.fx.practice.utils.TreeNode;

/**
 * @author: 梁峰源
 * @date: 2022/4/8 22:21
 * @Description:
 */
public class _110_平衡二叉树 {
    int leftHeight = 1;
    int rightHeight = 1;
    int currentHeight = 1;
    boolean result = true;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(new _110_平衡二叉树().isBalanced(root));
    }

    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        return Math.abs(Height(root.left) - Height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    private int Height(TreeNode node){
        if(node == null) return 0;
        leftHeight = Height(node.left);
        rightHeight = Height(node.right);
        return Math.max(leftHeight,rightHeight) + 1;
    }
}
