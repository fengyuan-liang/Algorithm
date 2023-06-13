package com.fx.queue;

import com.fx.heap.BinaryHeap;

import java.util.Comparator;

/**
 * 使用堆来实现优先级队列
 *
 * @author 梁峰源 <fengyuan-liang@foxmail.com>
 * @since 2023/6/13 12:40
 */
public class PriorityQueue<E> implements Queue<E> {

    private final BinaryHeap<E> binaryHeap;

    public PriorityQueue() {
        binaryHeap = new BinaryHeap<>();
    }

    public PriorityQueue(Comparator<E> comparator) {
        binaryHeap = new BinaryHeap<>(comparator);
    }


    @Override
    public int size() {
        return binaryHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void enQueue(E element) {
        binaryHeap.add(element);
    }

    @Override
    public E deQueue() {
        return binaryHeap.remove();
    }

    @Override
    public E front() {
        return binaryHeap.get();
    }

    @Override
    public void clear() {
        binaryHeap.clear();
    }
}
