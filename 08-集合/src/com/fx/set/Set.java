package com.fx.set;

/**
 * <p>
 * 集合接口
 * </p>
 *
 * @since: 2022/6/29 9:19
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
