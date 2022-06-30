package com.chen.algorithm.day15.review;

import java.util.Arrays;

/**
 * 堆排序
 * todo 待完成
 * @author chenzihan
 * @date 2022/06/30
 */
public class HeapSort {
    public static void main(String[] args) {
        int maxLength = 1000;
        int maxValue = 10000;
        int tryTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < tryTimes; i++) {
            int[] array1 = genernateRandomArray(maxLength, maxValue);
            int[] array2 = Arrays.copyOf(array1, array1.length);
            heapSort(array1);
            Arrays.sort(array2);
            if (!Arrays.equals(array1, array2)) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static void heapSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int length = array.length;
        for (int i = length - 1; i >= 0; i--) {
            heapify(array, i);
        }

    }

    private static void heapify(int[] array, int index) {
        int length = array.length;
        int left = 2 * index + 1;
        while (index < length - 1) {
            if (left >= length) {
                return;
            }
            if (left + 1 < length && array[left + 1] > array[left] && array[left + 1] > array[index]) {
                swap(array, left + 1, index);
                index = left + 1;
                left = 2 * index + 1;
            }
            if (array[left] > array[index]) {
                swap(array, left, index);
                index = left;
                left = 2 * index + 1;
            }
        }
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
}
