package com.fx.queue;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源 <fengyuan-liang@foxmail.com>
 * @since 2023/6/13 12:44
 */
public interface Queue<E> {
    int size(); // 元素的数量

    boolean isEmpty(); // 是否为空

    void enQueue(E element); // 入队

    E deQueue(); // 出队

    E front(); // 获取队列的头元素

    void clear(); // 清空
}
