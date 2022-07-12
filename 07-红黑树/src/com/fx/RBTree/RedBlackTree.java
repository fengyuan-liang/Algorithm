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
            if (color == RED) {
                str = "R_";
            }
            return str + element.toString();
        }
    }

    /**
     * @param node 新添加的结点
     */
    @Override
    protected void afterAdd(Node<E> node) {
        // 先取出父结点
        Node<E> parent = node.parent;
        // 添加的是根结点(将其染成黑色并返回)或者上溢到根结点
        if (parent == null) {
            black(node);
            return;
        }
        // 如果是前四种情况，即父结点为黑色结点，不用处理
        if (isBlack(parent)) return;
        // 取出uncle结点
        // 取出祖父结点
        Node<E> grand = parent.parent;
        Node<E> uncle = parent.sibling();
        // 如果叔父结点是红色【B树结点上溢，只需要染色】
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            // 把祖父结点当做是新添加的结点
            // 递归调用
            afterAdd(red(grand));
            return;
        }
        /*
         * 叔父结点不是红色，有四种情况
         * LL/RR: parent染成BLACK，grand染成RED - grand进行单旋操作
         * LR/RL: 自己染成black，grand染成red，再双旋
         */
        if (parent.isLeftChild()) { // L
            red(grand);
            if (node.isLeftChild()) { // LL
                black(parent);
            } else { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { //R
            red(grand);
            if (node.isLeftChild()) { // RL
                black(node);
                rotateRight(parent);
            } else { // RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        // 如果删除的节点是红色
        // 或者 用以取代删除节点的子节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<E> parent = node.parent;
        // 删除的是根节点
        if (parent == null) return;

        // 删除的是黑色叶子节点【下溢】
        // 判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;
        if (left) { // 被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else { // 被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }

//    @Override
//    protected void afterRemove(Node<E> node) {
//        // 如果删除的结点是红色，不需要做任何处理
//        if (isRed(node)) return;
//        // 用以取代的node结点是红色
//        if (isRed(replaceNode)) {
//            // 直接染色就可以了
//            black(replaceNode);
//            return;
//        }
//        // 删除的是黑色叶子结点
//        // 拿到parent
//        Node<E> parent = node.parent;
//        // 如果是根结点不做处理
//        if (parent == null) return;
//        // 这里判断node结点是左还是右，需要看父结点清空了谁
//        boolean left = parent.left == null || node.isLeftChild();
//        Node<E> sibling = left ? parent.right : parent.left;
//        if (left) { // 被删除的结点在左边，兄弟结点在右边
//            /*
//             * sibling 染成 BLACK，parent 染成 RED，进行旋转
//             * 于是又回到 sibling 是 BLACK 的情况
//             */
//            if (isRed(sibling)) { // 兄弟节点是红色
//                black(sibling);
//                red(parent);
//                rotateLeft(parent);
//                // 更换兄弟
//                sibling = parent.right;
//            }
//
//            // 兄弟节点必然是黑色
//            if (isBlack(sibling.left) && isBlack(sibling.right)) {
//                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
//                boolean parentBlack = isBlack(parent);
//                black(parent);
//                red(sibling);
//                if (parentBlack) {
//                    afterRemove(parent);
//                }
//            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
//                // 兄弟节点的左边是黑色，兄弟要先旋转
//                if (isBlack(sibling.right)) {
//                    rotateRight(sibling);
//                    sibling = parent.right;
//                }
//
//                color(sibling, colorOf(parent));
//                black(sibling.right);
//                black(parent);
//                rotateLeft(parent);
//            }
//        } else { // 被删除的结点在右边，兄弟结点在左边
//            /*
//             * sibling 染成 BLACK，parent 染成 RED，进行旋转
//             * 于是又回到 sibling 是 BLACK 的情况
//             */
//            if (isRed(sibling)) {
//                black(sibling);
//                red(parent);
//                rotateRight(parent);
//                // 更换兄弟
//                sibling = parent.left;
//            }
//            // 兄弟结点必然是黑色
//            if (isBlack(sibling.left) && isBlack(sibling.right)) {
//                // 兄弟结点没有红色子结点，父结点要向下跟兄弟结点合并，其实就是父结点染红，兄弟结点染黑
//                boolean parentIsBlack = isBlack(parent);
//                red(parent);
//                black(sibling);
//                // 如果下溢会导致父结点空，可以当做父结点被删除来看待，递归调用即可
//                if (parentIsBlack) afterRemove(parent, null);
//            } else { // 兄弟结点一定有红色的子结点
//                // 兄弟结点左边是黑色，兄弟要先旋转
//                if (isBlack(sibling.left)) {
//                    rotateLeft(sibling);
//                    // 兄弟结点需要重新赋值
//                    sibling = parent.left;
//                }
//                // 兄弟结点染成父结点的颜色
//                color(sibling, colorOf(parent));
//                // 将父结点的左右子结点都染成黑色
//                black(sibling.left);
//                black(parent);
//                // 对父结点进行右旋转
//                rotateRight(parent);
//            }
//        }
//    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    /**
     * 将元素染色
     *
     * @param node  带染色的结点
     * @param color 需要染的颜色
     * @return 将染色的结点返回
     */
    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) return node;
        ((RBNode<E>) node).color = color;
        return node;
    }

    //染成红色
    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    //染成黑色
    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    //查看当前结点颜色
    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

}
