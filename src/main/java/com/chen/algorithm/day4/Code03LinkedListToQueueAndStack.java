package com.chen.algorithm.day4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * code03使用单向链表实现队列和栈
 *
 * @author chenzihan
 * @date 2022/05/09
 */
public class Code03LinkedListToQueueAndStack {
    public static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V value) {
            this.value = value;
        }
    }

    /**
     * 单向链表组成队列，先入先出
     *
     * @author chenzihan
     * @date 2022/05/09
     */
    public static class MyQueue<V> {
        private Node<V> head;
        private Node<V> tail;
        private int size;

        public MyQueue() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        /**
         * 在队列中添加元素
         *
         * @param value 价值
         */
        public void offer(V value) {
            Node<V> node = new Node<>(value);
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
            size++;
        }

        /**
         * 让第一个入队的元素出队列
         *
         * @return {@link V}
         */
        public V poll() {
            V result = null;
            if (head != null) {
                result = head.value;
                head = head.next;
            }
            size--;
            return result;
        }

        /**
         * 检查第一个入队的元素，但不让它出队
         *
         * @return {@link V}
         */
        public V peek() {
            V result = null;
            if (head != null) {
                result = head.value;
            }
            return result;
        }
    }

    public static class MyStack<V> {
        private Node<V> head;
        private Node<V> tail;
        private int size;

        public MyStack() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        /**
         * 向栈顶压入元素
         *
         * @param v v
         */
        public void push(V v) {
            Node<V> node = new Node<>(v);
            if (head == null) {
                tail = node;
            } else {
                node.next = head;
            }
            head = node;
            size++;
        }

        /**
         * 弹出栈顶元素
         *
         * @return {@link V}
         */
        public V pop() {
            V result = null;
            if (head != null) {
                result = head.value;
                head = head.next;
                size--;
            }
            return result;
        }

        /**
         * 检查栈顶元素，但不弹出
         *
         * @return {@link V}
         */
        public V peek() {
            return head != null ? head.value : null;
        }
    }

    public static void testQueue(int maxValue, int tryTimes) {
        MyQueue<Integer> myQueue = new MyQueue<>();
        Queue<Integer> queue = new LinkedList<>();
        System.out.println("测试开始");
        for (int i = 0; i < tryTimes; i++) {
            if (myQueue.isEmpty() != queue.isEmpty()) {
                System.out.println("测试有误");
            }
            if (queue.size() != myQueue.size()) {
                System.out.println("测试有误");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (Math.random() * (maxValue + 1));
                queue.offer(num);
                myQueue.offer(num);
            } else if (decide < 0.66) {
                if (!queue.isEmpty()) {
                    Integer num1 = queue.poll();
                    Integer num2 = myQueue.poll();
                    if (!num1.equals(num2)) {
                        System.out.println("测试有误");
                    }
                }
            } else {
                if (!queue.isEmpty()) {
                    Integer num1 = queue.peek();
                    Integer num2 = myQueue.peek();
                    if (!num1.equals(num2)) {
                        System.out.println("测试有误");
                    }
                }
            }
            if (queue.size() != myQueue.size) {
                System.out.println("测试有误");
            }
            while (!queue.isEmpty()) {
                Integer num1 = queue.poll();
                Integer num2 = myQueue.poll();
                if (!num1.equals(num2)) {
                    System.out.println("测试有误");
                }
            }
        }
        System.out.println("测试结束");
    }

    public static void testStack(int maxValue, int tryTimes) {
        MyStack<Integer> myStack = new MyStack<>();
        Stack<Integer> stack = new Stack<>();
        System.out.println("测试开始");
        for (int i = 0; i < tryTimes; i++) {
            if (myStack.isEmpty() != stack.isEmpty()) {
                System.out.println("测试有误");
            }
            if (stack.size() != myStack.size()) {
                System.out.println("测试有误");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (Math.random() * (maxValue + 1));
                stack.push(num);
                myStack.push(num);
            } else if (decide < 0.66) {
                if (!stack.isEmpty()) {
                    Integer num1 = stack.pop();
                    Integer num2 = myStack.pop();
                    if (!num1.equals(num2)) {
                        System.out.println("测试有误");
                    }
                }
            } else {
                if (!stack.isEmpty()) {
                    Integer num1 = stack.peek();
                    Integer num2 = myStack.peek();
                    if (!num1.equals(num2)) {
                        System.out.println("测试有误");
                    }
                }
            }
            if (stack.size() != myStack.size) {
                System.out.println("测试有误");
            }
            while (!stack.isEmpty()) {
                Integer num1 = stack.pop();
                Integer num2 = myStack.pop();
                if (!num1.equals(num2)) {
                    System.out.println("测试有误");
                }
            }
        }
        System.out.println("测试结束");
    }

    public static void main(String[] args) {
        int maxValue = 1000000;
        int tryTimes = 10000000;
//        testQueue(maxValue, tryTimes);
        testStack(maxValue, tryTimes);
    }


}
