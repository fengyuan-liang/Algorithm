package com.fx.tree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: xxx
 * @date: 2022/3/14 20:12
 * Description: 二叉搜索树
 */
@SuppressWarnings("unchecked")
public class BinarySearchTree<E> extends BinaryTree<E> {


    public BinarySearchTree() {

    }

    public BinarySearchTree(Comparator<E> comparator) {
        super.comparator = comparator;
    }

    /**
     * 添加结点
     * @param element 待添加的元素
     */
    public void add(E element) {
        //非空检测
        elementNotNullCheck(element);
        //先判断是否为根结点，如果根结点为空则添加根结点
        if (root == null) {
            root = createNode(element,null);
            size++;
            return;
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
        Node<E> newNode = createNode(element,parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        //判断是否需要平衡这棵二叉树
        afterAdd(newNode);
    }

    /**
     * 创建结点
     * @param element 结点中的元素
     * @param parent 当前结点的父结点
     * @return 返回创建好的结点
     */
    protected Node<E> createNode(E element,Node<E> parent){
        return new Node<>(element,parent);
    }

    /**
     * 判断添加结点后是否需要平衡二叉树
     * @param node 新添加的结点
     */
    protected void afterAdd(Node<E> node){

    }

    /**
     * 删除结点时判断是否进行平衡
     * @param node 删除的结点
     */
    protected void afterRemove(Node<E> node){

    }


    /**
     * 对外暴露的删除方法
     */
    public void remove(E element) {
        remove(node(element));
    }

    /**
     * 根据结点删除该结点
     */
    private void remove(Node<E> node) {
        if (node == null) return;
        //优先处理度为2的结点
        if (node.hasTwoChildren()) {
            //找到其后继结点
            Node<E> successor = successor(node);
            //用后继结点的值覆盖度为2的结点的值
            node.element = successor.element;
            //因为度为2的结点的后继或者前驱结点一定是度为1或0，所以将删除结点交给后面的代码来做
            node = successor;
        }
        //删除度为1或者度为0的结点
        Node<E> replaceNode = node.left != null ? node.left : node.right;
        /*
         * 这里有三种情况，需要分类讨论
         *  1. node是度为1的结点
         *  2. node是叶子结点并且是根结点
         *  3. node是叶子结点
         */
        if (replaceNode != null) {
            //先修改node.parent的指向
            replaceNode.parent = node.parent;
            //修改parent的left、right指向
            if (node.parent == null) { //node是度为1的结点且是根结点
                root = replaceNode;
            } else if (node == node.parent.left) {
                node.parent.left = replaceNode;
            } else {
                node.parent.right = replaceNode;
            }
            //删除结点之后的处理
            afterRemove(replaceNode);
        } else if (node.parent == null) {
            //node是叶子结点并且是根结点,直接让该结点为null
            root = null;
            //删除结点之后的处理，这里不需要替代
            afterRemove(node);
        } else {
            //叶子结点
            //父结点的左子树
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                //父结点右子树
                node.parent.right = null;
            }
            //删除结点之后的处理，这里也不需要替代
            afterRemove(node);
        }
        size--;
    }

    /**
     * 根据传入的元素找到结点
     */
    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) return node;
            if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        //没找到
        return null;
    }

    public boolean contains(E e) {
        return node(e) != null;
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
     * 判断元素是否为空
     */
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }


    /**
     * 判断该树是否是一棵完全二叉树
     *
     * @return true是<br>false不是
     */
    public boolean isComplete() {
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        //将根结点入队
        queue.offer(root);
        //标记上一次访问的结点是否度为一且子树为左子树
        boolean flag = false;
        //遍历所有结点
        Node<E> node;
        while (!queue.isEmpty()) {
            //出队
            node = queue.poll();
            //上一次访问的结点左子树存在右子树不存在那么这次访问的结点必须是叶子结点
            if (flag && !node.isLeaf()) return false;
            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                return false;
            }
            if (node.right != null) {
                queue.offer(node.right);
            } else {
                flag = true;
            }
        }
        return true;
    }
}