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

    /**
     *
     * @param node 新添加的结点
     */
    @Override
    protected void afterAdd(Node<E> node) {
        // 先取出父结点
        Node<E> parent = node.parent;
        // 添加的是根结点，将其染成黑色并返回
        if(parent == null){
            black(node);
            return;
        }
        // 如果是前四种情况，即父结点为黑色结点，不用处理
        if(isBlack(parent)) return;
        // 取出uncle结点
        // 取出祖父结点
        Node<E> grand = parent.parent;
        Node<E> uncle = parent.sibling();
        // 如果叔父结点是红色
        if(isRed(uncle)){
            black(parent);
            black(uncle);
            // 把祖父结点当做是新添加的结点
            // 递归调用
            afterAdd(red(grand));
            return;
        }
        /*
         * 叔父结点不是红色，有四种情况
         * LL/RR:- parent染成BLACK，grand染成RED - grand进行单旋操作
         * LR/RL: 自己染成black，grand染成red，再双旋
         */
        if(parent.isLeftChild()){ // L
            red(grand);
            if(node.isLeftChild()){ // LL
                black(parent);
            }else { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        }else { //R
            red(grand);
            if(node.isLeftChild()){ // RL
                black(node);
                rotateRight(parent);
            }else { // RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        super.afterRemove(node);
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
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
    private boolean isRed(Node<E> node){
        return colorOf(node) == RED;
    }

}
