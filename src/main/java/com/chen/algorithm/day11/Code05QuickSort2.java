package com.chen.algorithm.day11;

import java.util.Arrays;
import java.util.Stack;

/**
 * code04 随机快排算法：非递归实现
 *
 * @author chenzihan
 * @date 2022/06/07
 */
public class Code05QuickSort2 {
    public static void main(String[] args) {
        int maxLength = 200;
        int maxValue = Integer.MAX_VALUE;
        int tryTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < tryTimes; i++) {
            int[] array = generateRandomArray(maxLength, maxValue);
            int[] copy = Arrays.copyOf(array, array.length);
            int[] copy2 = Arrays.copyOf(array, array.length);
            quickSort(array);
            Arrays.sort(copy);
            if (!test(array, copy)) {
                System.out.println("排序前");
                for (int num : copy2) {
                    System.out.print(num + " ");
                }
                System.out.println();
                System.out.println("排序后");
                for (int num : array) {
                    System.out.print(num + " ");
                }
                System.out.println();
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

    /**
     * 快速排序
     *
     * @param array 数组
     */
    private static void quickSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        Stack<Info> stack = new Stack<>();
        stack.push(new Info(0, array.length - 1));
        while (!stack.isEmpty()) {
            Info pop = stack.pop();
            if (pop.left < pop.right) {
                Info info = partition(array, pop.left, pop.right);
                stack.push(new Info(pop.left, info.left));
                stack.push(new Info(info.right, pop.right));
            }
        }
    }

    public static Info partition(int[] array, int left, int right) {
        int leftIndex = left - 1;
        int rightIndex = right + 1;
        int randomNum = array[(left + (int) (Math.random() * (right - left + 1)))];
        int index = left;
        while (index < rightIndex) {
            if (array[index] < randomNum) {
                swap(array, index++, ++leftIndex);
            } else if (array[index] == randomNum) {
                index++;
            } else {
                swap(array, index, --rightIndex);
            }
        }
        return new Info(leftIndex, rightIndex);
    }


    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static boolean test(int[] array, int[] copy) {
        if (array == null && copy == null) {
            return true;
        }
        if (array.length == 0 && copy.length == 0) {
            return true;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] != copy[i]) {
                return false;
            }
        }
        return true;
    }

    private static int[] generateRandomArray(int maxLength, int maxValue) {
        int length = (int) (Math.random() * (maxLength + 1));
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = (int) (Math.random() * (maxValue + 1));
        }
        return result;
    }

    public static class Info {
        public int left;
        public int right;

        public Info(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}
