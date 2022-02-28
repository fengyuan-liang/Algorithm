package com.fx;

import com.fx.circle.CircleDeQueue;
import com.fx.circle.CircleQueue;
import com.fx.queue.DeQue;
import com.fx.queue.Queue;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * @author: 梁峰源
 * @date: 2022/1/15 22:55
 * Description:
 */
public class TestMain {

    @Test
    public void test04(){
        CircleDeQueue<Integer> queue = new CircleDeQueue<>();
        // 头10 9 8 7 6 5 4 3 2 1 100 101 102 103 104 105 106 107 108 109尾
        //capacity=22 size=20 front=19,
        // [7, 6, 5, 4, 3, 2, 1, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, null, null, 10, 9, 8]
        for (int i = 0; i < 10; i++) {
            queue.enQueueFront(i + 1);
            queue.enQueueRear(i + 100);
        }
        //7, 6, 5, 4, 3, 2, 1, 100, 101, 102, 103, 104, 105, 106, null, null, null, null, null, null, null, null
        for (int i = 0; i < 3; i++) {
            queue.deQueueFront();
            queue.deQueueRear();
        }
        //7, 6, 5, 4, 3, 2, 1, 100, 101, 102, 103, 104, 105, 106, null, null, null, null, null, null, null, null
        queue.enQueueFront(11);
        queue.enQueueFront(12);
        System.out.println(queue);
        while(!queue.isEmpty()){
            System.out.print(queue.deQueueFront()+" ");
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

    @Test
    public void test00() throws InterruptedException {
//        for (int i = 0; i < 1000; i++) {
//            System.out.println(LocalDateTime.now());
//            Thread.sleep(1);
//            //yyyy-MM-ddTHH:mm:ss.SSS
//        }
    // 针对某个值 时有时无的情况,可用 [] 括起来,标识
//        DateTimeFormatter settleTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSS]");
//        String date = "2022-02-23T17:07:38";
//        String date2 = "2021-02-26T07:35:23.121";
//
////        TemporalAccessor parse = settleTimeFormatter.parse(date);
////        TemporalAccessor parse2 = settleTimeFormatter.parse(date2);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
//        LocalDateTime borrowTime = LocalDateTime.parse(date2, settleTimeFormatter);
//        System.out.println(borrowTime);
//        System.out.println(parse);
//        System.out.println(parse2);
        long milli = 1645682330403L;
        LocalDateTime localDateTime = new Date(milli).toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(Instant.ofEpochMilli(milli), ZoneOffset.of("+8"));
        System.out.println(localDateTime1);
    }
}
