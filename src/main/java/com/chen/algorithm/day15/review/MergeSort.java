package com.chen.algorithm.day15.review;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author chenzihan
 * @date 2022/06/29
 */
public class MergeSort {
    public static void main(String[] args) {
        int maxLength = 1000;
        int maxValue = 10000;
        int tryTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < tryTimes; i++) {
            int[] array1 = genernateRandomArray(maxLength, maxValue);
            int[] array2 = Arrays.copyOf(array1, array1.length);
            mergeSort(array1);
            Arrays.sort(array2);
            if (!Arrays.equals(array1, array2)) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static void mergeSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        process(array, 0, array.length - 1);
    }

    private static void process(int[] array, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + (right - left) / 2;
        process(array, left, mid);
        process(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int leftPoint = left;
        int rightPoint = mid + 1;
        int[] help = new int[right - left + 1];
        int index = 0;
        while (leftPoint <= mid && rightPoint <= right) {
            if (array[leftPoint] <= array[rightPoint]) {
                help[index++] = array[leftPoint++];
            } else {
                help[index++] = array[rightPoint++];
            }
        }
        while (leftPoint <= mid) {
            help[index++] = array[leftPoint++];
        }
        while (rightPoint <= right) {
            help[index++] = array[rightPoint++];
        }
        for (index = 0; index < help.length; index++) {
            array[left + index] = help[index];
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
}
