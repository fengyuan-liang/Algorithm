package com.fx.RBTree;

import com.fx.tree.BBST;
import com.fx.tree.Comparator;

/**
 * @Description: 红黑树
 * @date: 2022/5/17 15:47
 * @author: 梁峰源
 */
public class RedBlackTree<E> extends BBST<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RedBlackTree() {
    }

    public RedBlackTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 红黑树结点
     *
     * @param <E>
     */
    private static class RBNode<E> extends Node<E> {
        boolean color;
        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }
        @Override
        public String toString() {
            String str = "";
            if(color == RED){
                str = "R_";
            }
            return str + element.toString();
        }
    }

    @Override
    protected void afterAdd(Node<E> node) {
        super.afterAdd(node);
    }

    @Override
    protected void afterRemove(Node<E> node) {
        super.afterRemove(node);
    }

    /**
     * 将元素染色
     * @param node 带染色的结点
     * @param color 需要染的颜色
     * @return 将染色的结点返回
     */
    private Node<E> color(Node<E> node,boolean color){
        if(node == null) return node;
        ((RBNode<E>)node).color = color;
        return node;
    }
    //染成红色
    private Node<E> red(Node<E> node){
        return color(node,RED);
    }
    //染成黑色
    private Node<E> black(Node<E> node){
        return color(node,BLACK);
    }
    //查看当前结点颜色
    private boolean colorOf(Node<E> node){
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }
    private boolean isBlack(Node<E> node){
        return colorOf(node) == BLACK;
    }
    private boolean isRea(Node<E> node){
        return colorOf(node) == RED;
    }

}
