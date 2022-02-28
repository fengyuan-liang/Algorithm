package com.fx.queue;

import com.fx.doublyLinkedList.LinkedList;
import com.fx.doublyLinkedList.List;

/**
 * @author: 梁峰源
 * @date: 2022/1/15 22:55
 * Description:队列，底层实现优先使用双向链表，因为队列频繁在头尾操作
 */
public class Queue<E> {

    private final List<E> list=new LinkedList<>();

    /**
     * 元素的数量
     */
    public int size(){
        return list.size();
    }

    /**
     * 判断队列是否空
     */
    public  boolean isEmpty(){
        return list.isEmpty();
    }

    /**
     * 入队
     */
    public void enQueue(E element){
        list.add(element);
    }

    /**
     * 出队
     */
    public E deQueue(){
        return list.remove(0);
    }

    /**
     * 获取队列的头元素
     */
    public E front(){
        return list.get(0);
    }

    /**
     * 清空队列
     */
    public void clear(){
        list.clear();
    }
}
