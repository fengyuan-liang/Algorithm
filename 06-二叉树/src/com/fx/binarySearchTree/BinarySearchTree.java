package com.fx.binarySearchTree;

import com.fx.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: 梁峰源
 * @date: 2022/3/14 20:12
 * Description: 二叉搜索树
 */
@SuppressWarnings("unchecked")
public class BinarySearchTree<E> implements BinaryTreeInfo {
    //结点以内部类的形式存在
    private static class Node<E> {
        E element;//当前结点保存的元素
        Node<E> left;//左结点
        Node<E> right;//右结点
        Node<E> parent;//父结点

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }

    private int size;//当前树结点个数
    private Node<E> root;//根结点
    private Comparator<E> comparator;//比较器

    public BinarySearchTree() {

    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void add(E element) {
        //非空检测
        elementNotNullCheck(element);
        //先判断是否为根结点
        if (root == null) {
            root = new Node<>(element, null);
        }
        //非根结点，非递归进行添加
        Node<E> node = root;//用来标记移动的结点
        Node<E> parent = root;//保存当前结点的父结点，默认根结点就是父结点
        //根据比较规则找到待添加元素的位置
        int cmp = 0;
        while (node != null) {
            //比较值
            cmp = compare(element, node.element);
            //保存当前结点的父结点
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                return;
            }
        }
        //添加元素
        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
    }

    public void remove(E element) {

    }

    public boolean contains(E e1, E e2) {
        return true;
    }

    /**
     * 规定传入对象的比较规则
     *
     * @param e1 第一个对象
     * @param e2 第二个对象
     * @return 0表示相等，大于0表示 e1 > e2,小于0表示 e2 < e1
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((java.lang.Comparable<E>) e1).compareTo(e2);
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

    }


    /**
     * 判断元素是否为空
     */
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }
    //遍历
    //遍历接口
    public interface Visitor<E>{
        void visit(E element);
    }
    /**
     * 前序遍历
     */
    public void preorderTraversal(Visitor<E> visitor) {
        preorderTraversal(root,visitor);
    }
    private void preorderTraversal(Node<E> node,Visitor<E> visitor) {
        if(node == null || visitor == null) return;
        //回调
        visitor.visit(node.element);
        preorderTraversal(node.left,visitor);
        preorderTraversal(node.right,visitor);
    }

    /**
     * 栈实现前序遍历
     */
    private final Stack<Node<E>> stack = new Stack<>();

    public void preorderTraversalByStack(Visitor<E> visitor) {
        //将根元素入栈
        preorderTraversalByStack(root,visitor);
    }

    private void preorderTraversalByStack(Node<E> popNode,Visitor<E> visitor) {
        if(popNode == null || visitor == null) throw new IllegalArgumentException("Visitor不能为空");
        while (popNode != null) {
            //回调
            visitor.visit(popNode.element);
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
        inorderTraversal(root,visitor);
    }

    private void inorderTraversal(Node<E> node,Visitor<E> visitor) {
        if (node == null || visitor == null) return;
        inorderTraversal(node.left,visitor);
        //回调
        visitor.visit(node.element);
        inorderTraversal(node.right,visitor);
    }

    /**
     * 中序栈实现
     */
    public void inorderTraversalByStack(Visitor<E> visitor) {
        inorderTraversalByStack(root,visitor);
    }

    private void inorderTraversalByStack(Node<E> popNode,Visitor<E> visitor) {
        if(popNode == null || visitor == null) throw new IllegalArgumentException("Visitor不能为空");
        //只要左结点存在就一直入栈
        while (true) {
            if(popNode != null){
                stack.push(popNode);
                popNode = popNode.left;
            }else {
                if(stack.isEmpty()) return;
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
        postorderTraversal(root,visitor);
    }
    public void postorderTraversal(Node<E> node,Visitor<E> visitor) {
        if (node == null || visitor == null) return;
        postorderTraversal(node.left,visitor);
        postorderTraversal(node.right,visitor);
        System.out.println(node.element);
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
        while (!stack.isEmpty()){

            //如果是叶子结点
            if(isLeafNode(stack.peek()) || isPeekStack(preNode)){
                popNode = stack.pop();
                System.out.println(popNode.element);
            }else {
                if(popNode.right != null) stack.push(popNode.right);
                if(popNode.left != null) stack.push(popNode.left);
                preNode = popNode;
                popNode = stack.peek();
            }
        }
    }
    private boolean isLeafNode(Node<E> node){
        System.out.println(node.element);
        return node.left == null && node.right == null;
    }
    private boolean isPeekStack(Node<E> node){
        return stack.peek().left == node || stack.peek().right == node;
    }



    /**
     * 层次遍历接口暴露
     * @param visitor 逻辑接口
     */
    public void leverOrderTraversal(Visitor<E> visitor){
        if(visitor == null || root == null) throw new IllegalArgumentException("Visitor不能为空");
        Queue<Node<E>> queue = new LinkedList<>();
        //将根结点入队
        queue.offer(root);
        while (!queue.isEmpty()){
            //出队
            Node<E> node = queue.poll();
            //回调，将处理遍历数据的业务交给调用者
            visitor.visit(node.element);
            if(node.left !=null) queue.offer(node.left);
            if(node.right !=null) queue.offer(node.right);
        }
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
        return ((Node<E>) node).element;
    }
}
