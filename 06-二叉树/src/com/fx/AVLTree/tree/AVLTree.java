package com.fx.AVLTree.tree;

import com.fx.AVLTree.Comparator;

/**
 * @author: 梁峰源
 * @date: 2022/3/28 21:10
 * Description:
 */
public class AVLTree<E> extends BinarySearchTree<E>{
    public AVLTree(){}
    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }
}
