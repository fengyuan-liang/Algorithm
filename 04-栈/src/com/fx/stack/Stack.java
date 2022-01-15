package com.fx.stack;

import com.fx.list.ArrayList;
import com.fx.list.List;

/**
 * 设计思路：
 *  1. 不用继承，用组合，继承会得到父类所有的方法和属性，
 */
public class Stack<E>{
    private final List<E> list=new ArrayList<>();
    public int size(){
        return list.size();
    }

    /**
     * 判断元素是否为空
     */
    public boolean isEmpty(){
        return list.isEmpty();
    }

    /**
     * 入栈
     */
    public void push(E element){
        list.add(element);
    }

    /**
     * 出栈
     */
    public E pop(){
        return list.remove(list.size() - 1);
    }

    /**
     * 获取栈顶元素
     */
    public E top(){
        return list.get(list.size() - 1);
    }

    /**
     * 清空栈内元素
     */
    public void clear(){
        list.clear();
    }
}
