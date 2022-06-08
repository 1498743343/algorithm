package com.chen.algorithm.day12;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * code02堆
 * <p>
 * 1）堆结构就是用数组实现的完全二叉树结构
 * 2）完全二叉树中如果每棵子树的最大值都在顶部就是大根堆
 * 3）完全二叉树中如果每棵子树的最小值都在顶部就是小根堆
 * 4）堆结构的heapInsert与heapify操作
 * 5）堆结构的增大和减少
 * 6）优先级队列结构，就是堆结构
 *
 * @author chenzihan
 * @date 2022/06/08
 */
public class Code02Heap {
    public static class MyHeap {
        public int[] heap;
        public final int limit;
        public int heapSize;
        public final Comparator<Integer> comparator;

        public MyHeap(int limit) {
            this.limit = limit;
            this.heap = new int[limit];
            this.heapSize = 0;
            this.comparator = Comparator.comparingInt(num -> num);
        }

        public MyHeap(int limit, Comparator<Integer> comparator) {
            this.limit = limit;
            this.heap = new int[limit];
            this.heapSize = 0;
            this.comparator = comparator;
        }

        /**
         * 堆插入
         * 从堆底部插入，然后将插入元素上浮至合适的位置，重新形成大根堆或小根堆
         * 两种条件停止上浮：1. index 来到 0 位置，说明已经是头部节点了 2.干不掉父节点，说明已经是合理位置
         *
         * @param num 插入元素
         */
        private void heapInsert(int num) {
            int index = heapSize;
            heap[heapSize++] = num;
            // 当 index == 0 时，相当于自己和自己比较，所以不满足条件
            while (comparator.compare(heap[index], heap[((index - 1) / 2)]) < 0) {
                swap(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        /**
         * 将堆的头部元素下沉至合理的位置，重新形成大根堆或小根堆
         * 当前元素和子节点的 compare > 0 的元素
         */
        private void heapify() {
            int index = 0;
            // 先默认 comparatorIndex 为当前节点的左子节点
            int comparatorIndex = 1;
            // 如果左子节点已经超过 heapSize - 1，说明当前节点没有子节点了
            while (comparatorIndex < heapSize) {
                // 存在右子节点，并且右子节点比左子节点排序靠前，
                if (comparatorIndex < heapSize - 1 && comparator.compare(heap[comparatorIndex], heap[comparatorIndex + 1]) > 0) {
                    comparatorIndex++;
                }
                // index 节点排序和左右子节点中排序靠前的节点相等或者更靠前，那么就让 comparatorIndex = index
                if (comparator.compare(heap[index], heap[comparatorIndex]) <= 0) {
                    comparatorIndex = index;
                }
                if (index == comparatorIndex) {
                    break;
                }
                swap(heap, index, comparatorIndex);
                index = comparatorIndex;
                comparatorIndex = 2 * comparatorIndex + 1;
            }
        }

        public void push(int num) {
            if (heapSize == limit) {
                throw new RuntimeException("堆结构已满，不能再加入元素");
            }
            heapInsert(num);
        }

        public int pop() {
            if (heapSize == 0) {
                throw new RuntimeException("堆已空，不能再弹出元素");
            }
            int result = heap[0];
            swap(heap, 0, --heapSize);
            heapify();
            return result;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }
    }

    public static void main(String[] args) {
        int value = 1000;
        int limit = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyHeap myHeap = new MyHeap(curLimit, (a, b) -> b - a);
            PriorityQueue<Integer> test = new PriorityQueue<>((a, b) -> b - a);
            for (int j = 0; j < curLimit; j++) {
                double random = Math.random();
                if (random < 0.5) {
                    if (myHeap.isFull()) {
                        if (myHeap.heapSize != test.size()) {
                            System.out.println("出错了1");
                            break;
                        }
                    } else {
                        int num = (int) (Math.random() * (value + 1));
                        myHeap.push(num);
                        test.offer(num);
                    }
                } else {
                    if (myHeap.isEmpty()) {
                        if (test.size() != 0) {
                            System.out.println("出错了2");
                            break;
                        }
                    } else {
                        int pop = myHeap.pop();
                        Integer poll = test.poll();
                        if (pop != poll) {
                            System.out.println("出错了3");
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
