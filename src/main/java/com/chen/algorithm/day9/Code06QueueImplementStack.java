package com.chen.algorithm.day9;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * code06 队列实现栈
 * 准备两个队列，当调用 pop 时，将一个队列中的元素全部移动到另一个队列，只留下最后一个元素进行处理，然后将两个队列的指针交换
 *
 * @author chenzihan
 * @date 2022/05/27
 */
public class Code06QueueImplementStack {
    private static class MyStack<T> {
        private Queue<T> queue;
        private Queue<T> help;

        public MyStack() {
            this.queue = new LinkedList<>();
            this.help = new LinkedList<>();
        }

        public void push(T t) {
            queue.offer(t);
        }

        public T poll() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T result = queue.poll();
            Queue<T> tmp = queue;
            queue = help;
            help = tmp;
            return result;
        }

        public T peek() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T result = queue.poll();
            help.offer(result);
            Queue<T> tmp = queue;
            queue = help;
            help = tmp;
            return result;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        MyStack<Integer> myStack = new MyStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("Oops");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.poll().equals(test.pop())) {
                        System.out.println("Oops");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }

        System.out.println("test finish!");

    }
}
