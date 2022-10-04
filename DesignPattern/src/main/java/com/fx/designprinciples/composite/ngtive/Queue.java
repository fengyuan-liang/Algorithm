package com.fx.designprinciples.composite.ngtive;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源
 * @since 2022/10/3 15:04
 */
public class Queue<E> implements Serializable {
    LinkedList<E> list = new LinkedList<>();

    /**
     * 入队
     */
    public void enQueue(E element) {
        list.add(element);
    }

    /**
     * 出队
     */
    public E deQueue() {
        return list.remove(0);
    }
}
