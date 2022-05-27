package com.chen.algorithm.day9;


/**
 * code04 环形数组
 * 使用数组实现栈和队列：关键点是引入额外变量 size，这样就不用再去对 pushIndex 和 popIndex 做复杂的比较来判断是否可以新增或者弹出元素
 *
 * @author chenzihan
 * @date 2022/05/27
 */
public class Code04RingArray {
    public static class MyQueue<T> {
        Object[] arr;
        int pushIndex;
        int popIndex;
        int size;
        int limit;

        public MyQueue(int limit) {
            this.limit = limit;
            pushIndex = 0;
            popIndex = 0;
            size = 0;
            arr = new Object[limit];
        }

        public void push(T t) {
            if (size == limit) {
                throw new RuntimeException("队列已满，不能再添加");
            }
            size++;
            arr[pushIndex] = t;
            pushIndex = nextIndex(pushIndex);
        }

        public T pop() {
            if (size == 0) {
                return null;
            }
            size--;
            T result = (T) arr[popIndex];
            popIndex = nextIndex(popIndex);
            return result;
        }

        private int nextIndex(int index) {
            return index == limit - 1 ? 0 : index + 1;
        }
    }

}
