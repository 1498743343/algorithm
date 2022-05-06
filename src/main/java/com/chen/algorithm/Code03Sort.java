package com.chen.algorithm;

import java.util.Arrays;

/**
 * code03排序 todo
 * 3种性能低但是经典的排序算法：
 * <ol>
 *     <li>选择排序</li>
 *     <li>冒泡排序</li>
 *     <li>插入排序</li>
 * </ol>
 *
 * @author chenzihan
 * @date 2022/05/05
 */
public class Code03Sort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 6, 7, 3, 2, 1, 6, 8};
        System.out.println("before sort: " + Arrays.toString(arr));
        insertSort(arr);
        System.out.println("after sort:  " + Arrays.toString(arr));
    }

    /**
     * 将给定数组的 i 位置和 j 位置的值做交换
     *
     * @param arr 数组
     * @param i   数组下标i
     * @param j   数组下标j
     */
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    /**
     * 选择排序
     * 每轮对比完，将最小值和初始位置交换
     *
     * @param arr 需要排序的数组
     */
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int currentMinIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[currentMinIndex]) {
                    currentMinIndex = j;
                }
            }
            swap(arr, i, currentMinIndex);
        }
    }

    /**
     * 冒泡排序
     * 与相邻位置比较， arr[i] > arr[i+1] 就交换，每次遍历可以把最大值找出来放到当前的最右侧
     *
     * @param arr 需要排序的数组
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }

    }

    /**
     * 插入排序
     * 从 n = 0开始，每次保证 0~n 位置上有序，将 n+1 位置上的值与左侧已经排好序的部分做比较，将 arr[n+1] 放到合适的位置上
     *
     * @param arr 需要排序的数组
     */
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                swap(arr, j, j - 1);
            }
        }
    }
}
