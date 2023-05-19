package com.fx.heap;

import com.fx.printer.BinaryTreeInfo;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <p>
 * 默认为最大堆（大顶堆）
 * </p>
 *
 * @author 梁峰源 <fengyuan-liang@foxmail.com>
 * @since 2023/5/18 21:42
 */
public class BinaryHeap<E> implements Heap<E>, BinaryTreeInfo {

    private E[] elements;
    private int size;
    private Comparator<E> comparator;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public BinaryHeap(Comparator<E> comparator) {
        this.comparator = comparator;
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public BinaryHeap() {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        Arrays.fill(elements, null);
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;

    }

    @Override
    public void add(E element) {
        checkElementNotNull(element);
        ensureCapacity(size + 1);
        elements[size++] = element;
        siftUp(size - 1);
    }

    @Override
    public E get() {
        checkEmpty();
        return elements[0];
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E replace(E element) {
        return null;
    }

    /**
     * 让index上的元素进行上滤
     *
     * @param index 元素在数组中的索引
     */
    private void siftUp(int index) {
        while (index > 0) {
            // 检查父结点是否小于该元素
            // 父结点编号为`floor(i - 1) / 2`
            int parentIndex = (index - 1) >> 1;
            if (compare(elements[index], elements[parentIndex]) <= 0) {
                return;
            }
            // 交换元素
            E tmp = elements[parentIndex];
            elements[parentIndex] = elements[index];
            elements[index] = tmp;
            index = parentIndex;
        }
    }

    @SuppressWarnings("unchecked")
    private int compare(E e1, E e2) {
        return comparator != null ? comparator.compare(e1, e2) : ((Comparable<E>) e1).compareTo(e2);
    }

    private void checkEmpty() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("heap is empty");
        }
    }

    private void checkElementNotNull(E element) {
        if (element == null) {
            throw new IllegalArgumentException("there element not null");
        }
    }

    /**
     * 保证集合容量足够
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (capacity < oldCapacity) return;
        // 扩容1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        elements = Arrays.copyOf(elements, newCapacity);
    }

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        Integer index = (Integer) node;
        int leftIndex = (index << 1) + 1;
        return leftIndex >= size ? null : leftIndex;
    }

    @Override
    public Object right(Object node) {
        Integer index = (Integer) node;
        int rightIndex = (index << 1) + 2;
        return rightIndex >= size ? null : rightIndex;
    }

    @Override
    public Object string(Object node) {
        return elements[(Integer) node];
    }
}
