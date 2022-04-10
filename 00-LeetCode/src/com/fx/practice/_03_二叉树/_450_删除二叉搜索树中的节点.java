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
    }

    /*找前驱，在这题里面不需要考虑找父结点的情况，并且可以直接返回前驱结点里面的数字*/
    public TreeNode predecessor(TreeNode root) {
        //前驱就是左子树里的最大值
        root = root.left;
        while (root.right != null) root = root.right;
        return root;
    }

    /*找后继，在这题里面不需要考虑找父结点的情况，并且可以直接返回后继结点里面的数字*/
    public TreeNode successor(TreeNode root) {
        root = root.right;
        //后继就是右子树里的最小值
        while (root.left != null) root = root.left;
        return root;
    }
    /*这里不采取递归的方式*/
    public TreeNode deleteNode(TreeNode root, int key) {
        //先找到要删除的结点
        TreeNode node = node(root, key);
        //没找到
        if(node == null) return null;
        /*
         * 执行删除逻辑,一共有三种情况的结点，但是由于删除结点为2的结点就是删除其前驱或后继，所以只有两种删除情况
         *  1. 度为2的结点，找到后继，用后继结点的值覆盖该结点，然后将问题转为删除后继元素
         *  2. 度为1的结点，如果左子树不为空，让父结点指向该结点的指针指向左结点，反之指向有结点
         *  3. 度为0的结点，直接让结点为null
         */
        //如果该结点的度为2，转为删除后继的问题
        if(node.left != null && node.right !=null){
            TreeNode successor = successor(node);
            //用后继结点的值覆盖度为2的结点的值
            node.val = successor.val;
            //因为度为2的结点的后继或者前驱结点一定是度为1或0，所以将删除结点交给后面的代码来做
            node = successor;
        }

        //删除度为1或者度为0的结点
        TreeNode replaceNode = node.left != null ? node.left : node.right;
        
        if(node.left == null){
            //该结点只有右子树，让父结点指向其右子树
            TreeNode parent = parent(root, node);
            if(parent.left == null) parent.right = node.right;
            else parent.left = node.right;
        }else if(node.right == null){
            //该结点只有右子树，让父结点指向其右子树
            TreeNode parent = parent(root, node);
            if(parent.left == null) parent.right = node.left;
            else parent.left = node.left;
        }
        return null;
    }

    /**
     * 根据传入的元素找到结点
     */
    private TreeNode node(TreeNode root,int element) {
        TreeNode node = root;
        while (node != null) {
            //找到了，返回该结点
            if (node.val == element) return node;
            if (node.val > element) {
                //从右子树中找
                node = node.right;
            } else {
                //从左子树中找
                node = node.left;
            }
        }
        //没找到
        return null;
    }
    /**
     * 根据传入的元素找到该结点的父结点
     */
    private TreeNode parent(TreeNode parent,TreeNode node) {
        while (node != null) {
            //找到了，返回该结点
            if (parent.left == node || parent.right == node) return parent;
            if (node.val > parent.val) {
                //从右子树中找
                parent = node;
                node = node.right;
            } else {
                //从左子树中找
                parent = node;
                node = node.left;
            }
        }
        return parent;
    }

    /**
     * 层序遍历  未解决
     */
    public static TreeNode deleteNode2(TreeNode root, int key) {
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
