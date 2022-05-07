package com.chen.algorithm.day3;

import java.util.Arrays;

/**
 * code02二分法
 * 已知一个数组有序 arr，使用二分法找到 >=num 最左侧的值的数组下标
 *
 * @author chenzihan
 * @date 2022/05/07
 */
public class Code02BisectionMethodNearLeft {
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
     * 暴力验证方法，遍历一遍数组，找到满足条件的下标
     *
     * @param arr   数组
     * @param value 给定值
     * @return boolean
     */
    public static int test(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 使用二分法找到满足条件的下标
     *
     * @param arr   数组
     * @param value 给定值
     * @return boolean
     */
    public static int find(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] >= value) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
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
                printArray(randomArray);
                System.out.println(value);
                System.out.println(test(randomArray, value));
                System.out.println(find(randomArray, value));
                success = false;
                break;
            }
        }
        System.out.println(success ? "验证没有问题" : "程序有错误");
    }
}
