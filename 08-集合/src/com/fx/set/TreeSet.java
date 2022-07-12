package com.fx.set;

import com.fx.Tree.BinaryTree;
import com.fx.Tree.RbTree;

/**
 * <p>
 *
 * </p>
 *
 * @since: 2022/7/12 13:29
 * @author: 梁峰源
 */
public class TreeSet<E> implements Set<E> {
    // 红黑树
    RbTree<E> rbTree = new RbTree<>();

    @Override
    public int size() {
        return rbTree.size();
    }

    @Override
    public boolean isEmpty() {
        return rbTree.isEmpty();
    }

    @Override
    public void clear() {
        rbTree.clear();
    }

    @Override
    public boolean contains(E element) {
        return rbTree.contains(element);
    }

    @Override
    public void add(E element) {
        // 红黑树本身如果是相同的会覆盖，默认就是去重的
        rbTree.add(element);
    }

    @Override
    public void remove(E element) {
        rbTree.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        // 中序遍历
        rbTree.inorderTraversal(new BinaryTree.Visitor<E>() {
            @Override
            protected boolean visit(E element) {
                return visitor.visit(element);
            }
        });
    }
}
