package com.fx.circle;

/**
 * @author: 梁峰源
 * @date: 2022/1/24 17:11
 * Description: 循环队列，没有固定的头
 */
@SuppressWarnings("unchecked")
public class CircleQueue<E> {

    private int size;
    private E[] elements;
    private int front;//记录队列首元素的位置
    private final int DEFAULT_CAPACITY = 10;//默认队列大小

    public CircleQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
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
     * 入队，循环队列需要取模
     */
    public void enQueue(E element) {
        ensureCapacity(size + 1);
        elements[(front + size) % elements.length] = element;
        size++;
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
            newElements[i] = elements[(i + front) % elements.length];
        }
        elements = newElements;
        //重置front的位置
        front = 0;
    }

    /**
     * 出队
     */
    public E deQueue() {
        E element = elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;
        return element;
    }

    /**
     * 获取队列的头元素
     */
    public E front() {
        return elements[front];
    }

    /**
     * 清空队列
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        front = 0;
        size = 0;
    }

    /**
     * 返回循环队列中的真实索引
     */
    private int index(int index) {
        index += front;
        return index - (index >= elements.length ? elements.length : 0);
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
