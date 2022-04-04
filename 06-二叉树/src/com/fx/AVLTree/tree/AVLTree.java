package com.fx.AVLTree.tree;

import com.fx.AVLTree.Comparator;

/**
 * @author: 梁峰源
 * @date: 2022/3/28 21:10
 * Description:
 */
public class AVLTree<E> extends BinarySearchTree<E> {
    public AVLTree() {
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * AVL树结点
     *
     * @param <E>
     */
    private static class AVLNode<E> extends BinaryTree.Node<E> {
        int height;//AVL树有平衡因子的概念，需要有高度

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        //获取当前结点的平衡因子，即左子树高度 减去 右子树高度
        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        //更新当前结点的高度
        public void updateHeight(Node<E> node) {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            this.height = Math.max(leftHeight, rightHeight);
        }
    }

    /**
     * 创建AVL树结点
     *
     * @param element 结点中的元素
     * @param parent  当前结点的父结点
     * @return 返回AVL树的结点
     */
    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    /**
     * 重写平衡BST的逻辑
     *
     * @param node 新添加的结点，也就是导致失衡的结点
     */
    @Override
    protected void afterAdd(Node<E> node) {
        //在其祖父结点中寻到最近的失衡结点
        while ((node = node.parent) != null) {
            //判断当前结点是否平衡
            if (isBalanced(node)) {
                //平衡，更新该结点的高度
                updateHeight(node);
            } else {
                //不平衡，该结点为离添加结点最近的不平衡的结点
                reBalance(node);
                //该结点恢复平衡后整棵树也将恢复平衡，直接退出循环
                break;
            }
        }
    }

    /**
     * 让当前AVL树回复平衡
     *
     * @param g 离添加结点最近的不平衡的结点
     */
    private void reBalance(Node<E> g) {

    }

    /**
     * 判断当前结点是否平衡
     *
     * @param node 传入需要判断的结点
     * @return 返回结点的平衡情况 <br> tree 平衡 <br> false 不平衡
     */
    private boolean isBalanced(Node<E> node) {
        //先将传入的结点转为AVLNode，在进行判断，结点的平衡因子的绝对值小于等于1表示平衡
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    /**
     * 更新当前结点的高度
     *
     * @param node 带更新高度的结点
     */
    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight(node);
    }
}
