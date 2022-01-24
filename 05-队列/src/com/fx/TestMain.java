package com.fx;

import com.fx.circle.CircleDeQueue;
import com.fx.circle.CircleQueue;
import com.fx.queue.DeQue;
import com.fx.queue.Queue;
import org.junit.Test;

/**
 * @author: 梁峰源
 * @date: 2022/1/15 22:55
 * Description:
 */
public class TestMain {

    @Test
    public void test04(){
        CircleDeQueue<Integer> queue =new CircleDeQueue<>();
        // 头4 3 2 1 尾
        for (int i = 0; i < 10; i++) {
            queue.enQueueFront(i + 1);
            queue.enQueueRear(i + 100);
        }
        //null, null, null, null, null, 5, 6, 7, 8, 9
        for (int i = 0; i < 3; i++) {
            queue.deQueueFront();
            queue.deQueueRear();
        }
        queue.enQueueFront(11);
        queue.enQueueFront(12);
        System.out.println(queue);
        while(!queue.isEmpty()){
            System.out.println(queue.deQueueFront());
        }
    }

    @Test//循环队列测试
    public void test03(){
        CircleQueue<Integer> queue =new CircleQueue<>();
        // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        for (int i = 0; i < 11; i++) {
            queue.enQueue(i);
        }
        // null, null, null, null, null, 5, 6, 7, 8, 9
        for (int i = 0; i < 5; i++) {
            queue.deQueue();
        }
        System.out.println(queue);
        while(!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }
    }

    @Test
    public void test02(){
        DeQue<Integer> deQue = new DeQue<>();
        deQue.enQueueFront(0);
        deQue.enQueueFront(1);
        deQue.enQueueFront(2);
        deQue.enQueueRear(3);
        deQue.enQueueRear(4);
        deQue.enQueueRear(5);
        /*5 4 3 0 1 2*/
        while(!deQue.isEmpty()){
            System.out.println(deQue.deQueue());
        }
    }

    @Test
    public void test01(){
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < 10; i++) {
            //入队
            queue.enQueue(i);
        }
        while(!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }
    }
}
