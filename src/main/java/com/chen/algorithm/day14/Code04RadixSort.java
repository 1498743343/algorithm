package com.chen.algorithm.day14;

import java.util.Arrays;

/**
 * code04 基数排序
 * 一般来讲，基数排序要求，样本是10进制的正整数
 * todo 未完成
 *
 * @author chenzihan
 * @date 2022/06/28
 */
public class Code04RadixSort {

    /**
     * 基数排序
     * 此处使用更加优雅的方式去实现基数排序：help数组
     *
     * @param arr 数组
     */
    private static void radixSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int radix = 10;
        int max = Integer.MIN_VALUE;
        for (int number : arr) {
            max = Math.max(max, number);
        }
        int sortTimes = calculateSortTimes(max, radix);
        int[] help = new int[arr.length];
        for (int i = 0; i < sortTimes; i++) {
            int[] count = new int[10];
            // 计算出数组上每个值当前位置的值，并累加到 count 中。例如 i = 1时， number = 123，此时会取出 2
            for (int number : arr) {
                int digit = getDigit(number, i);
                count[digit]++;
            }
            // 对 count 数组作累加操作，形成前缀树组
            for (int j = 1; j < count.length; j++) {
                count[j] = count[j - 1] + count[j];
            }
            // 按照当前值将 arr 中的元素取出，然后按照顺序放入 help 中
            for (int j = arr.length - 1; j >= 0; j--) {
                int number = arr[j];
                int digit = getDigit(number, i);
                int index = --count[digit];
                help[index] = number;
            }
            // 将 help 的值 copy 给 arr
            System.arraycopy(help, 0, arr, 0, arr.length);
        }
    }

    private static int getDigit(int number, int power) {
        return (int) (number / Math.pow(10, power)) % 10;
    }

    /**
     * 计算排序次数
     *
     * @param max   最大值
     * @param radix 基数
     * @return int
     */
    private static int calculateSortTimes(int max, int radix) {
        int result = 0;
        while (max != 0) {
            max = max / radix;
            result++;
        }
        return result;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, arr.length);
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
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

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 200;
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        radixSort(arr);
        printArray(arr);

    }
}
