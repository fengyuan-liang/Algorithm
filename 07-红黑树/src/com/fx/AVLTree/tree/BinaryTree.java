package com.fx.AVLTree.tree;

import com.fx.AVLTree.Comparator;
import com.fx.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: 梁峰源
 * @date: 2022/3/23 23:21
 * Description:
 */
@SuppressWarnings("unchecked")
public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;//当前树结点个数
    protected Node<E> root;//根结点
    protected Comparator<E> comparator;//比较器
    //结点以内部类的形式存在
    protected static class Node<E> {
        E element;//当前结点保存的元素
        Node<E> left;//左结点
        Node<E> right;//右结点
        Node<E> parent;//父结点
        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
        public boolean isLeaf() {
            return left == null && right == null;
        }
        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
        public boolean isLeftChild(){
            return parent != null && this == parent.left;
        }
        public boolean isRightChild(){
            return parent != null && this == parent.right;
        }

    }


    /**
     * 返回树结点个数
     */
    public int size() {
        return size;
    }
    /**
     * 判断树是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * 清空当前树
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * 递归输出树的高度
     *
     * @return 返回树的高度
     */
    public int height() {
        return height(root);
    }

    private int height(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * 非递归返回树的高度
     *
     * @return 树的高度
     */
    public int heightByLevelOrder() {
        Queue<Node<E>> queue = new LinkedList<>();
        //将根结点入队
        queue.offer(root);
        //记录树的高度
        int height = 0;
        //记录每一层的元素数量
        int levelSize = 1;
        while (!queue.isEmpty()) {
            //出队
            Node<E> node = queue.poll();
            //当前层元素减少
            levelSize--;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
            //访问下一层
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    /**
     * 遍历接口
     *
     * @param <E> visit 返回true表示停止遍历<br>返回false表示不停止，继续遍历
     */
    public abstract static class Visitor<E> {
        /**
         * stop用来标记递归遍历中是否需要停止遍历
         */
        boolean stop = false;
        abstract boolean visit(E element);
    }

    /**
     * 前序遍历
     */
    public void preorderTraversal(Visitor<E> visitor) {
        if (visitor == null) throw new IllegalArgumentException("visitor不能为空");
        preorderTraversal(root, visitor);
    }
    private void preorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null) return;
        //回调
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        preorderTraversal(node.left, visitor);
        preorderTraversal(node.right, visitor);
    }
    /**
     * 栈实现前序遍历
     */
    private final Stack<Node<E>> stack = new Stack<>();
    public void preorderTraversalByStack(Visitor<E> visitor) {
        if (root == null) throw new IllegalArgumentException("Visitor不能为空");
        //将根元素入栈
        preorderTraversalByStack(root, visitor);
    }
    private void preorderTraversalByStack(Node<E> popNode, Visitor<E> visitor) {
        while (popNode != null) {
            //回调
            if (visitor.stop) return;
            visitor.stop = visitor.visit(popNode.element);
            //将其右结点入栈
            if (popNode.right != null) {
                stack.push(popNode.right);
            }
            //将其左结点入栈
            if (popNode.left != null) {
                stack.push(popNode.left);
            }
            //将栈顶元素出栈
            popNode = stack.isEmpty() ? null : stack.pop();
        }
    }
    /**
     * 中序遍历（递归实现）
     */
    public void inorderTraversal(Visitor<E> visitor) {
        if (visitor == null) throw new IllegalArgumentException("visitor不能为空");
        inorderTraversal(root, visitor);
    }
    private void inorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        inorderTraversal(node.left, visitor);
        //回调
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inorderTraversal(node.right, visitor);
    }
    /**
     * 中序栈实现
     */
    public void inorderTraversalByStack(Visitor<E> visitor) {
        if (root == null || visitor == null) throw new IllegalArgumentException("Visitor不能为空");
        inorderTraversalByStack(root, visitor);
    }
    private void inorderTraversalByStack(Node<E> popNode, Visitor<E> visitor) {
        //只要左结点存在就一直入栈
        while (true) {
            if (popNode != null) {
                stack.push(popNode);
                popNode = popNode.left;
            } else {
                if (stack.isEmpty()) return;
                popNode = stack.pop();
                //回调
                visitor.visit(popNode.element);
                popNode = popNode.right;
            }
        }
    }
    /**
     * 后序遍历
     */
    public void postorderTraversal(Visitor<E> visitor) {
        if (visitor == null) throw new IllegalArgumentException("visitor不能为空");
        postorderTraversal(root, visitor);
    }
    public void postorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        postorderTraversal(node.left, visitor);
        postorderTraversal(node.right, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }
    /**
     * 后序遍历非递归实现
     */
    public void postorderTraversalByStack() {
        postorderTraversalByStack(root);
    }
    public void postorderTraversalByStack(Node<E> popNode) {
        //先将根结点入栈
        stack.push(popNode);
        Node<E> preNode = popNode;//记录一下上一次访问的结点
        while (!stack.isEmpty()) {
            //如果是叶子结点
            if (isLeafNode(stack.peek()) || isPeekStack(preNode)) {
                popNode = stack.pop();
                System.out.println(popNode.element);
            } else {
                if (popNode.right != null) stack.push(popNode.right);
                if (popNode.left != null) stack.push(popNode.left);
                preNode = popNode;
                popNode = stack.peek();
            }
        }
    }
    private boolean isLeafNode(Node<E> node) {
        System.out.println(node.element);
        return node.left == null && node.right == null;
    }
    private boolean isPeekStack(Node<E> node) {
        return stack.peek().left == node || stack.peek().right == node;
    }
    /**
     * 层序遍历接口暴露
     *
     * @param visitor 逻辑接口
     */
    public void leverOrderTraversal(Visitor<E> visitor) {
        if (visitor == null || root == null) throw new IllegalArgumentException("Visitor不能为空");
        Queue<Node<E>> queue = new LinkedList<>();
        //将根结点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            //出队
            Node<E> node = queue.poll();
            //回调，将处理遍历数据的业务交给调用者
            if (visitor.visit(node.element)) return;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }


    /**
     * 找到当前结点的前驱结点
     */
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) throw new IllegalArgumentException("node不能为空");
        //前驱结点在左子树当中(left.right.right.......)
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }
        //从祖父结点里面找
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        /*
         * 这里有两种情况
         *  1. node.parent == null
         *  2. node = node.parent.right;
         */
        return node.parent;
    }
    /**
     * 找到其后继结点
     */
    protected Node<E> successor(Node<E> node) {
        if (node == null) throw new IllegalArgumentException("node不能为空");
        Node<E> p = node.right;
        //第一种情况，其后继结点为node.right.left.left...
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        //从祖父结点里面找
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        /*
         * 来到这里有两种情况
         *  1. node.right = null
         *  2. node = node.parent.left;
         */
        return node.parent;
    }


    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return node;
    }
}
