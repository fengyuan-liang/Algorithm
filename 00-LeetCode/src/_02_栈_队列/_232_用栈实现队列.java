package _02_栈_队列;

import java.util.Stack;

/**
 * @author: 梁峰源
 * @date: 2022/1/15 23:48
 * Description:https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * 思路：先准备两个栈，inStack、outStack
 * 入队时：push到inStack中
 * 出队时：
 * outStack为空，将inStack所有元素逐一弹出，push到outStack，outStack弹出栈顶元素
 * outStack不空，outStack弹出栈顶元素
 */
public class _232_用栈实现队列 {

    private final Stack<Integer> inStack = new Stack<>();
    private final Stack<Integer> outStack = new Stack<>();

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        checkOutStack();
        return outStack.pop();
    }

    //获取队头元素
    public int peek() {
        checkOutStack();
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void checkOutStack() {
        if (outStack.isEmpty()) {
            //将inStack所有元素逐一弹出，push到outStack
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }
}
