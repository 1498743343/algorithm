package com.chen.algorithm.day3;

import java.util.Arrays;

/**
 * code01二分法存在
 * 已知一个数组有序 arr，使用二分法判定数组中是否存在指定的 value
 *
 * @author chenzihan
 * @date 2022/05/07
 */
public class Code01BisectionMethodExist {
    /**
     * 得到随机数组
     * 长度：[0,maxLength]；值：[0,maxValue]
     *
     * @param maxLength 最大长度
     * @param maxValue  最大价值
     * @return {@link int[]}
     */
    public static int[] getRandomArray(int maxLength, int maxValue) {
        int length = (int) (Math.random() * (maxLength + 1));
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    /**
     * 暴力验证方法，遍历一遍数组，看看是否存在 value
     *
     * @param arr   数组
     * @param value 给定值
     * @return boolean
     */
    public static boolean test(int[] arr, int value) {
        for (int test : arr) {
            if (test == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * 使用二分法查找数组中是否存在 value
     *
     * @param arr   数组
     * @param value 给定值
     * @return boolean
     */
    public static boolean find(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] < value) {
                left = mid + 1;
            } else if (arr[mid] == value) {
                return true;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int maxLength = 50;
        int maxValue = 100;
        int tryTimes = 1000000;
        boolean success = true;
        for (int i = 0; i < tryTimes; i++) {
            int[] randomArray = getRandomArray(maxLength, maxValue);
            int value = (int) (Math.random() * (maxValue + 1));
            Arrays.sort(randomArray);
            if (test(randomArray, value) != find(randomArray, value)) {
                System.out.println();
                success = false;
                break;
            }
        }
        System.out.println(success ? "验证没有问题" : "程序有错误");
    }
}
