package com.chen.algorithm.day14;

import java.util.Arrays;

/**
 * code03 计数排序
 * 一般来讲，计数排序要求，样本是整数，且范围比较窄
 * 桶排序是一种思想，大体上包括了计数排序和基数排序：
 * 1)桶排序思想下的排序都是不基于比较的排序
 * 2)时间复杂度为O(N)，额外空间负载度O(M)
 * 3)应用范围有限，需要样本的数据状况满足桶的划分
 *
 * @author chenzihan
 * @date 2022/06/28
 */
public class Code03CountSort {
    /**
     * 计数排序
     * 此方法仅适用于 >= 0 的整数范围，如果要满足 < 0 的情况，需要改造代码，这也是桶排序的缺点之一：改变一点条件就需要增加肉眼可见的代码量
     *
     * @param arr 数组
     */
    public static void countSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int number : arr) {
            max = Math.max(max, number);
        }
        // 有 0，所以 + 1
        int[] bucket = new int[max + 1];
        for (int number : arr) {
            bucket[number]++;
        }
        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }

    public static void main(String[] args) {
        int maxLength = 100;
        int maxValue = 200;
        int tryTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < tryTimes; i++) {
            int[] arr1 = genernateRandomArray(maxLength, maxValue);
            int[] arr2 = Arrays.copyOf(arr1, arr1.length);
            countSort(arr1);
            Arrays.sort(arr2);
            if (!test(arr1, arr2)) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static boolean test(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    private static int[] genernateRandomArray(int maxLength, int maxValue) {
        int length = (int) (Math.random() * (maxLength + 1));
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = (int) (Math.random() * (maxValue + 1));
        }
        return result;
    }
}
