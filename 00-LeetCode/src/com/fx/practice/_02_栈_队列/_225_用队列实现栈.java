package com.fx.practice._02_栈_队列;

import java.util.ArrayDeque;

/**
 * @author: 梁峰源
 * @date: 2022/1/25 11:07
 * Description:
 */
public class _225_用队列实现栈 {
    ArrayDeque<Integer> queue;
    public _225_用队列实现栈() {
        queue = new ArrayDeque<>();//双端队列
    }

    public static void main(String[] args) {
//        _225_用队列实现栈 e = new _225_用队列实现栈();
//        for (int i = 0; i < 5; i++) {
//            e.push(i);
//        }
//        while (!e.empty()){
//            System.out.print(e.pop());
//        }
        int i=0;
        if(i==0){
            throw new RuntimeException("");
        }else {
            System.out.println("");
        }
        System.out.println("1111");

    }

    public void push(int x) {
        queue.offerFirst(x);
    }

    public int pop() {
        return queue.pollFirst();
    }

    public int top() {
        return queue.peekFirst();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
