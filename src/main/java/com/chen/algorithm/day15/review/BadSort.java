package com.chen.algorithm.day15.review;

import java.util.Arrays;

/**
 * 选择排序、冒泡排序、插入排序
 *
 * @author chenzihan
 * @date 2022/06/29
 */
public class BadSort {

    public static void main(String[] args) {
        int maxLength = 100;
        int maxValue = 100;
        int tryTimes = 100000;
        for (int i = 0; i < tryTimes; i++) {
            int[] array1 = genernateRandomArray(maxLength, maxValue);
            int[] array2 = Arrays.copyOf(array1, array1.length);
            insertSort(array1);
            Arrays.sort(array2);
            if (!Arrays.equals(array1, array2)) {
                System.out.println("出错了");
                break;
            }

        }
    }

    /**
     * 选择排序
     * 遍历一轮找到最小值的 index，和当前位置的值交换
     *
     * @param array 数组
     */
    private static void selectSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
    }

    /**
     * 冒泡排序
     * 和右边的比，大就交换
     *
     * @param array 数组
     */
    private static void bubbleSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            boolean flag = true;
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    /**
     * 插入排序
     * 往前看，找到合适的位置
     *
     * @param array 数组
     */
    private static void insertSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                }
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
