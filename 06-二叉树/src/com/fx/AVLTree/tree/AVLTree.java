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
    private static class AVLNode<E> extends Node<E> {
        int height = 1;//AVL树有平衡因子的概念，需要有高度，默认高度为1

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 获取当前结点的平衡因子，即左子树高度 减去 右子树高度
         *
         * @return 返回平衡因子
         */
        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        /**
         * 更新当前结点的高度
         *
         * @param node 待更新高度的结点
         */
        public void updateHeight(Node<E> node) {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            this.height = 1 + Math.max(leftHeight, rightHeight);
        }

        /**
         * 获得左右子树中高度较高的结点
         *
         * @return 返回较高的结点
         */
        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            //如果左右子树高度相等，返回与该结点同侧的结点
            return isLeftChild() ? left : right;
        }

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }
            return element + "-P(" + parentString + ")" + ",H(" + height + ")";
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
     * 删除元素进行平衡
     * @param node 删除的结点
     */
    @Override
    protected void afterRemove(Node<E> node) {
        //在其祖父结点中寻到最近的失衡结点
        while ((node = node.parent) != null) {
            //判断当前结点是否平衡
            if (isBalanced(node)) {
                //平衡，更新该结点的高度
                updateHeight(node);
            } else {
                //不平衡，该结点为离添加结点最近的不平衡的结点
                reBalance(node);
            }
        }
    }

    /**
     * 进行统一旋转处理
     *
     * @param r 根结点
     */
    private void rotate(Node<E> r,  //子树的根结点
                        Node<E> a,
                        Node<E> b,
                        Node<E> c,
                        Node<E> d,
                        Node<E> e,
                        Node<E> f,
                        Node<E> g) {
        //让d成为这棵树的根结点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            //r没有父结点
            root = d;
        }
        //处理d的左子树，即a-b-c结点
        b.right = c;
        if (c != null) {
            c.parent = b;
        }
        b.left = a;
        if (a != null) {
            a.parent = b;
        }
        //更新b结点的高度
        updateHeight(b);
        //处理d的右子树，即e-f-g结点
        f.right = g;
        if (g != null) {
            g.parent = f;
        }
        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        //更新f结点的高度
        updateHeight(f);
        //现在处理b-d-f结点
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
        //d的左右子树结点高度都更新了，d的高度也需要更新
        updateHeight(d);
    }

    /**
     * 让当前AVL树恢复平衡
     *
     * @param grand 离添加结点最近的不平衡的结点
     */
    private void reBalance(Node<E> grand) {
        //分别获得parent结点和node结点
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        //先判断LL右旋转的情况
        if (parent.isLeftChild()) { //L
            if (node.isLeftChild()) {  //LL
//                rotateRight(grand);
                rotate(grand,node.left,node,node.right,parent,parent.right,grand,grand.right);
            } else {  //LR
//                rotateLeft(parent);
//                rotateRight(grand);
                rotate(grand,parent.left,parent,node.left,node,node.right,grand,grand.right);
            }
        } else {  //R
            if (node.isLeftChild()) {  //RL
//                rotateRight(parent);
//                rotateLeft(grand);
                rotate(grand,grand.left,grand,node.left,node,node.right,parent,parent.right);
            } else {  //RR
//                rotateLeft(grand);
                rotate(grand,grand.left,grand,parent.left,parent,node.left,node,node.right);
            }
        }
    }

    /**
     * 对该元素进行左旋转
     *
     * @param grand 待旋转的结点
     */
    private void rotateLeft(Node<E> grand) {
        //获得parent结点
        Node<E> parent = grand.right;
        //将parent的左子结点取出
        Node<E> leftChild = parent.left;
        //左旋
        grand.right = leftChild;
        parent.left = grand;
        //旋转之后让parent结点成为根结点并更新grand、parent、child结点的高度
        afterRotate(grand, parent, leftChild);
    }

    /**
     * 对该元素进行右旋转
     *
     * @param grand 待旋转的结点
     */
    private void rotateRight(Node<E> grand) {
        //获得parent结点,即grand结点的左结点
        Node<E> parent = grand.left;
        //获得parent结点的右子结点，方便后面更新高度
        Node<E> rightChild = parent.right;
        //右旋
        grand.left = rightChild;
        parent.right = grand;
        //旋转之后让parent结点成为根结点并更新grand、parent、child结点的高度
        afterRotate(grand, parent, rightChild);
    }

    /**
     * 旋转之后让parent结点成为根结点并更新grand、parent、child结点的高度
     */
    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        /*
         * 让parent结点成为当前子树的根结点
         * 这里有两步：
         *  1. 让parent的父结点指向grand的父结点
         *  2. 让grand父结点本来指向grand的指针指向parent,这里顺便更新了parent结点的父结点
         **/
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            //当前结点没有父结点，即grand结点就是root结点
            root = parent;
        }
        /*
         * 一共需要更新三个结点的parent，grand、parent和leftChild结点
         * grand结点在上面第二步中已经更新了，所以这里我们还需要更新parent结点和leftChild结点的parent结点
         **/
        if (child != null) {
            child.parent = grand;
        }
        //更新grand的parent结点
        grand.parent = parent;
        //更新高度，先比较矮的结点再更新较高的结点
        updateHeight(grand);
        updateHeight(parent);
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
