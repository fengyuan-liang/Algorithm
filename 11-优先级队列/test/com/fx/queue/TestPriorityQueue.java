package com.fx.queue;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源 <fengyuan-liang@foxmail.com>
 * @since 2023/6/13 21:11
 */
public class TestPriorityQueue {

    public static void main(String[] args) {
        PriorityQueue<Person> priorityQueue = new PriorityQueue<>((p1, p2) -> p1.age - p2.age);
        priorityQueue.enQueue(new Person(22, "张三"));
        priorityQueue.enQueue(new Person(18, "李四"));
        priorityQueue.enQueue(new Person(25, "王五"));
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.deQueue());
        }
    }
}
