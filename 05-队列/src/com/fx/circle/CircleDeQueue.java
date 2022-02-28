package com.fx.circle;

/**
 * @author: 梁峰源
 * @date: 2022/1/24 21:22
 * Description: 循环双端队列
 */
@SuppressWarnings("unchecked")
public class CircleDeQueue<E> {
    private int size;
    private E[] elements;
    private int front;//记录队列首元素的位置
    private static final int DEFAULT_CAPACITY = 10;//默认队列大小

    public CircleDeQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * 从尾部出队
     */
    public E deQueueRear() {
        int rearIndex = index(size - 1);
        E element = elements[rearIndex];
        elements[rearIndex] = null;
        size--;
        return element;
    }

    /**
     * 从尾部入队
     */
    public void enQueueRear(E element) {
        ensureCapacity(size + 1);
        elements[index(size)] = element;
        size++;
    }

    /**
     * 从头部出队
     */
    public E deQueueFront() {
        E element = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return element;
    }

    /**
     * 从头部入队
     */
    public void enQueueFront(E element) {
        ensureCapacity(size + 1);
        front = index(-1);
        elements[front] = element;
        size++;
    }

    /**
     * 获得头部的元素
     */
    public E front() {
        return elements[front];
    }

    /**
     * 获取尾部的元素
     */
    public E rear() {
        return elements[index(size - 1)];
    }


    /**
     * 元素的数量
     */
    public int size() {
        return size;
    }

    /**
     * 判断队列是否空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回循环队列中的真实索引
     */
    private int index(int index) {
        index += front;
        if (index < 0) {
            index += elements.length;
        }
        return index - (index >= elements.length ? elements.length : 0);
    }

    /**
     * 确保容量充足，不足扩容1.5倍
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (capacity < oldCapacity) return;
        //扩容1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[index(i)];
        }
        elements = newElements;
        //重置front的位置
        front = 0;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("capacity=").append(elements.length)
                .append(" size=").append(size)
                .append(" front=").append(front)
                .append(", [");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }

}
