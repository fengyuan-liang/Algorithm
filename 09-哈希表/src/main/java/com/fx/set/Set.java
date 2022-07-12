package com.fx.set;

/**
 * <p>
 *
 * </p>
 *
 * @since: 2022/7/12 22:44
 * @author: 梁峰源
 */
public interface Set<E>{
    int size();
    boolean isEmpty();
    void clear();
    boolean contains(E element);
    void add(E element);
    void remove(E element);
    void traversal(Visitor<E> visitor);
    abstract class Visitor<E>{
        boolean stop;
        public abstract boolean visit(E element);
    }
}
