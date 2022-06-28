package com.chen.algorithm.day14;

/**
 * code04 基数排序
 * 一般来讲，基数排序要求，样本是10进制的正整数
 * todo 未完成
 *
 * @author chenzihan
 * @date 2022/06/28
 */
public class Code04RadixSort {
    public static void main(String[] args) {
    }

    /**
     * 基数排序
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
        int[] count = new int[10];
        for (int i = 0; i < sortTimes; i++) {

        }
        System.arraycopy(help, 0, arr, 0, arr.length);
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
}
