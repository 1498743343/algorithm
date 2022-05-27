package com.chen.algorithm.day9;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * code03 使用双端队列实现栈和队列
 *
 * @author chenzihan
 * @date 2022/05/27
 */
public class Code03DoubleEndsQueueToStackAndQueue {

    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (notEqual(myStack.pop(), stack.pop())) {
                            System.out.println("oops!");
                        }
                    }
                }
                int numq = (int) (Math.random() * value);
                if (queue.isEmpty()) {
                    myQueue.push(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.push(numq);
                        queue.offer(numq);
                    } else {
                        if (notEqual(myQueue.poll(), queue.poll())) {
                            System.out.println("oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }

    private static class MyQueue<T> {
        private DoubleEndsQueue<T> queue;

        public MyQueue() {
            queue = new DoubleEndsQueue<>();
        }

        public void push(T value) {
            queue.addToTail(value);
        }

        public T poll() {
            return queue.popFromHead();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    private static class MyStack<T> {
        private DoubleEndsQueue<T> queue;

        public MyStack() {
            queue = new DoubleEndsQueue<>();
        }

        public void push(T value) {
            queue.addToHead(value);
        }

        public T pop() {
            return queue.popFromHead();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }


    private static class DoubleEndsQueue<T> {
        Node<T> head;
        Node<T> tail;
        int size;

        public DoubleEndsQueue() {
            this.size = 0;
        }

        /**
         * 从头部添加元素
         *
         * @param value 值
         */
        public void addToHead(T value) {
            Node<T> node = new Node<>(value);
            if (head == null) {
                tail = node;
            } else {
                node.next = head;
                head.last = node;
            }
            head = node;
            size++;
        }

        /**
         * 从尾部添加元素
         *
         * @param value 值
         */
        public void addToTail(T value) {
            Node<T> node = new Node<>(value);
            if (head == null) {
                head = node;
            } else {
                node.last = tail;
                tail.next = node;
            }
            tail = node;
        }

        /**
         * 从头部弹出元素
         *
         * @return {@link T}
         */
        public T popFromHead() {
            if (head == null) {
                return null;
            }
            Node<T> current = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.last = null;
                // 这一步应该是无效的，因为 current 节点应该是不可达的，会被回收
                current.next = null;
            }
            return current.value;
        }

        public T popFromBottom() {
            if (head == null) {
                return null;
            }
            Node<T> current = tail;
            if (head == tail) {
                head = null;
                tail = null;
            }
            return current.value;
        }

        public boolean isEmpty() {
            return head == null;
        }

    }

    private static class Node<T> {
        T value;
        Node<T> last;
        Node<T> next;

        public Node(T t) {
            this.value = t;
        }
    }

    public static boolean notEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 != null) {
            return true;
        }
        if (o1 != null && o2 == null) {
            return true;
        }
        if (o1 == null) {
            return false;
        }
        return !o1.equals(o2);
    }

}
