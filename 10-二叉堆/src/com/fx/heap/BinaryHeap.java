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
public class BinaryHeap<E> extends AbstractHeap<E> implements Heap<E>, BinaryTreeInfo {

    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public BinaryHeap(Comparator<E> comparator) {
        super(comparator);
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap() {
        this(null);
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
        return null;
    }

    /**
     * 下滤
     *
     * @param index 结点的索引
     */
    private void siftDown(int index) {
        // 不能是叶子结点（必须要有子结点）
        int half = size >> 1;
        while(index < half) {
            // index 的节点有两种情况
            // 1. 只有左子节点 2. 同时有左右子节点
            // 默认跟左子节点进行比较
            int childIndex = (index << 1) + 1;

        }
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
