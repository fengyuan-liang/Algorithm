package com.fx.practice._03_二叉树;

import com.fx.practice.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: 梁峰源
 * @date: 2022/3/23 11:33
 * Description:
 */
public class _102_二叉树的层序遍历 {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        TreeNode left = node.left = new TreeNode(3);
        TreeNode right = node.right = new TreeNode(6);
        left.left = new TreeNode(2);
        left.right = new TreeNode(4);
        right.right = new TreeNode(7);
        List<List<Integer>> lists = new _102_二叉树的层序遍历().levelOrder(node);
        lists.forEach(System.out::println);
    }

    Queue<TreeNode> queue = new LinkedList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        //先将根结点入队
        queue.offer(root);
        TreeNode node;
        List<List<Integer>> mainList = new ArrayList<>();
        List<Integer> list;
        while(!queue.isEmpty()){
            //用list存起来
            list = new ArrayList<>();
            int cnt = queue.size();
            while (cnt > 0){
                //先出队
                node = queue.poll();
                list.add(node.val);
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
                cnt--;
            }
            mainList.add(list);
        }
        return mainList;
    }
}
