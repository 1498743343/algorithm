package com.chen.algorithm.day15.review;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author chenzihan
 * @date 2022/07/01
 */
public class QuickSort {
    public static void main(String[] args) {
        int maxLength = 1000;
        int maxValue = 10000;
        int tryTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < tryTimes; i++) {
            int[] array1 = genernateRandomArray(maxLength, maxValue);
            int[] array2 = Arrays.copyOf(array1, array1.length);
            int[] array3 = Arrays.copyOf(array1, array1.length);
            quickSort(array1);
            Arrays.sort(array2);
            if (!Arrays.equals(array1, array2)) {
                print(array3);
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static void quickSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        process(array, 0, array.length - 1);
    }

    private static void process(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int[] range = partation(array, left, right);
        process(array, left, range[0]);
        process(array, range[1], right);
    }

    private static int[] partation(int[] array, int left, int right) {
        int index = left + (int) (Math.random() * (right - left + 1));
        int targetNumber = array[index];
        int leftBorder = left - 1;
        int rightBorder = right + 1;
        index = left;
        while (index < rightBorder) {
            if (array[index] < targetNumber) {
                swap(array, index++, ++leftBorder);
            } else if (array[index] == targetNumber) {
                index++;
            } else {
                swap(array, index, --rightBorder);
            }
        }
        return new int[]{leftBorder, rightBorder};
    }

    private static int[] genernateRandomArray(int maxLength, int maxValue) {
        int length = (int) (Math.random() * (maxLength + 1));
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) (Math.random() * (maxValue + 1));
        }
        return array;
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void print(int[] array3) {
        for (int j : array3) {
            System.out.print(j + " ");
        }
    }
}
