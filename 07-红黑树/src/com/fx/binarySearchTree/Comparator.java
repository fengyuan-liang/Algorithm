package com.fx.binarySearchTree;

/**
 * 比较器，可以在类外面自定义比较规则
 * @param <E>
 */
public interface Comparator<E> {
    int compare(E e1,E e2);
}
