package com.fx.heap;

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
public class BinaryHeap<E> extends AbstractHeap<E> implements Heap<E> {

    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap() {
        this(null, null);
    }

    public BinaryHeap(Comparator<E> comparator) {
        this(null, comparator);
    }

    public BinaryHeap(E[] elements) {
        this(elements, null);
    }

    @SuppressWarnings("unchecked")
    public BinaryHeap(E[] elements, Comparator<E> comparator) {
        super(comparator);
        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            this.elements = (E[]) new Object[Math.max(elements.length, DEFAULT_CAPACITY)];
            // 深拷贝
            System.arraycopy(elements, 0, this.elements, 0, elements.length);
            size = elements.length;
            // 批量建堆
            heapify();
        }
    }


    @Override
    public void clear() {
        Arrays.fill(elements, null);
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
        checkEmpty();
        // 用最后一个结点覆盖根结点
        E root = elements[0];
        // size也要减一
        elements[0] = elements[size - 1];
        elements[size - 1] = null;
        size--;
        // 进行下滤
        siftDown(0);
        return root;
    }

    /**
     * 下滤
     *
     * @param index 结点的索引
     */
    private void siftDown(int index) {
        // 不能是叶子结点（必须要有子结点）
        int half = size >> 1;
        E element = elements[index];
        // 必须只有非叶子结点才能进入循环（第一个叶子结点的索引==非叶子结点的数量）
        // 更具完全二叉树的性质，第一个叶子结点的索引为：floor(size / 2)
        while (index < half) {
            // index 的节点有两种情况
            // 1. 只有左子节点 2. 同时有左右子节点
            // 默认跟左子节点进行比较
            int childIndex = (index << 1) + 1;
            E childElement = elements[childIndex];
            // 右子节点
            int rightIndex = childIndex + 1;
            // 选出左右子节点中最大的
            if (rightIndex < size && compare(elements[rightIndex], elements[childIndex]) > 0) {
                childIndex = rightIndex;
                childElement = elements[rightIndex];
            }
            if (compare(element, childElement) >= 0) {
                break;
            }
            // 将子结点存放到index位置
            elements[index] = childElement;
            index = childIndex;
        }
        elements[index] = element;
    }

    @Override
    public E replace(E element) {
        checkElementNotNull(element);
        E root = null;
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    /**
     * 让index上的元素进行上滤
     *
     * @param index 元素在数组中的索引
     */
    private void siftUp(int index) {
        E element = elements[index];
        while (index > 0) {
            int parentIndex = (index - 1) >> 1;
            E parent = elements[parentIndex];
            if (compare(element, parent) <= 0) break;
            // 将父元素存储在index位置
            elements[index] = parent;
            // 重新赋值index
            index = parentIndex;
        }
        elements[index] = element;
    }

    private void heapify() {
        // 自下而上的下滤
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }


    private void checkEmpty() {
        if (size <= 0) {
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

}
