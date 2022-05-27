package com.chen.algorithm.day9;

import java.util.Stack;

/**
 * code05 得到最小值栈
 * 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能：
 * 1、pop、push、getMin操作的时间复杂度都是 O(1)。
 * 2、设计的栈类型可以使用现成的栈结构
 *
 * @author chenzihan
 * @date 2022/05/27
 */
public class Code05GetMinStack {
    private static class MyStack {
        Stack<Integer> stack;
        Stack<Integer> minStack;

        public void push(Integer num) {
            stack.push(num);
            if (minStack.isEmpty()) {
                minStack.push(num);
            } else {
                Integer peek = minStack.peek();
                if (peek < num) {
                    minStack.push(peek);
                } else {
                    minStack.push(num);
                }
            }
        }

        public Integer pop() {
            if (stack.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            minStack.pop();
            return stack.pop();
        }

        public Integer getMin() {
            if (stack.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            return minStack.peek();
        }

        public MyStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(9);
        myStack.push(3);
        System.out.println(myStack.getMin());
        myStack.pop();
        myStack.push(7);
        System.out.println(myStack.getMin());
        myStack.push(5);
        System.out.println(myStack.getMin());
        myStack.push(7);
        System.out.println(myStack.getMin());
        myStack.push(2);
        System.out.println(myStack.getMin());
        myStack.push(4);
        System.out.println(myStack.getMin());
        myStack.pop();
        myStack.pop();
        myStack.push(6);
        System.out.println(myStack.getMin());
    }

}
