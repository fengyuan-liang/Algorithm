package com.fx.queue;

import com.fx.doublyLinkedList.LinkedList;
import com.fx.doublyLinkedList.List;

/**
 * @author: 梁峰源
 * @date: 2022/1/16 12:54
 * Description:双端队列，即可以在队列两端进行添加和删除操作的队列
 */
public class DeQue<E> {;
    private final List<E> list = new LinkedList<>();
    /**
     * 元素的数量
     */
    public int size(){
        return list.size();
    }

    public  boolean isEmpty(){
        return list.isEmpty();
    }

    /**
     * 队头入队
     */
    public void enQueueFront(E element){
        list.add(0,element);
    }

    /**
     * 队尾入队
     */
    public void enQueueRear(E element){
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
     * 获取队列的尾元素
     */
    public E rear(){
        return list.get(list.size() - 1);
    }

    /**
     * 清空队列
     */
    public void clear(){
        list.clear();
    }
}
