package com.fx.tree;

/**
 * <p>
 * 带旋转功能的bst
 * </p>
 *
 * @since: 2022/6/28 21:30
 * @author: 梁峰源
 */
public class BBST<E> extends BinarySearchTree<E> {

    public BBST() {
    }

    public BBST(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 进行统一旋转处理
     *
     * @param r 根结点
     */
    protected void rotate(Node<E> r,  //子树的根结点
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
        //处理d的右子树，即e-f-g结点
        f.right = g;
        if (g != null) {
            g.parent = f;
        }
        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        //现在处理b-d-f结点
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
    }

    /**
     * 对该元素进行左旋转
     *
     * @param grand 待旋转的结点
     */
    protected void rotateLeft(Node<E> grand) {
        if(null == grand) return;
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
    protected void rotateRight(BinaryTree.Node<E> grand) {
        //获得parent结点,即grand结点的左结点
        BinaryTree.Node<E> parent = grand.left;
        //获得parent结点的右子结点，方便后面更新高度
        BinaryTree.Node<E> rightChild = parent.right;
        //右旋
        grand.left = rightChild;
        parent.right = grand;
        //旋转之后让parent结点成为根结点并更新grand、parent、child结点的高度
        afterRotate(grand, parent, rightChild);
    }

    /**
     * 旋转之后让parent结点成为根结点并更新grand、parent、child结点的高度
     */
    protected void afterRotate(BinaryTree.Node<E> grand, BinaryTree.Node<E> parent, BinaryTree.Node<E> child) {
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

    }
}
