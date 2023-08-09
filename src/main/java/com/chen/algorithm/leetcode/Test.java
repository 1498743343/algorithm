package com.chen.algorithm.leetcode;


import java.util.Arrays;

/**
 * 测试
 * 求 1~n 的阶乘相加：1! + 2! + 3! + ... + n!
 *
 * @author chenzihan
 * @date 2023/03/28
 */
public class Test {
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
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i; j < n; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }

    /**
     * 冒泡排序
     * 与相邻位置比较， arr[i] > arr[i+1] 就交换，每次遍历可以把最大值找出来放到当前的最右侧
     *
     * @param arr 需要排序的数组
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n - i; j++) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j - 1, j);
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
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 1; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }


}

