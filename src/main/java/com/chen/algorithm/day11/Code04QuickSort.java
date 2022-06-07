package com.chen.algorithm.day11;

import java.util.Arrays;

/**
 * code04 随机快排算法
 *
 * @author chenzihan
 * @date 2022/06/07
 */
public class Code04QuickSort {
    public static void main(String[] args) {
        int maxLength = 300;
        int maxValue = Integer.MAX_VALUE;
        int tryTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < tryTimes; i++) {
            int[] array = generateRandomArray(maxLength, maxValue);
            int[] copy = Arrays.copyOf(array, array.length);
            quickSort(array);
            Arrays.sort(copy);
            if (!test(array, copy)) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static void quickSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        process(array, 0, array.length - 1);
    }

    private static void process(int[] array, int left, int right) {
        // baseCase，当 left >= right，说明最小单位内已经排序完成
        if (left >= right) {
            return;
        }
        int[] scope = partition(array, left, right);
        process(array, left, scope[0]);
        process(array, scope[1], right);
    }

    /**
     * 分区
     *
     * @param array 数组
     * @param left  左下标
     * @param right 右下标
     * @return {@link int[]} 返回的结果就是 < randomNum 的最右下标和 > randomNum 的最坐下标
     */
    private static int[] partition(int[] array, int left, int right) {
        // 获取随机数这一点很重要，目的是通过随机来打乱数组原来的顺序，如果我们每次都取 array[right]，那么当数组本来就有序时，时间复杂度为 O(n2)
        int randomIndex = left + (int) (Math.random() * (right - left + 1));
        int randomNum = array[randomIndex];
        int leftIndex = left - 1;
        int rightIndex = right + 1;
        int index = left;
        while (index < rightIndex) {
            if (array[index] < randomNum) {
                swap(array, index++, ++leftIndex);
            } else if (array[index] == randomNum) {
                index++;
            } else {
                swap(array, index, --rightIndex);
            }
        }
        return new int[]{leftIndex, rightIndex};
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static boolean test(int[] array, int[] copy) {
        if (array == null && copy == null) {
            return true;
        }
        if (array.length == 0 && copy.length == 0) {
            return true;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] != copy[i]) {
                return false;
            }
        }
        return true;
    }

    private static int[] generateRandomArray(int maxLength, int maxValue) {
        int length = (int) (Math.random() * (maxLength + 1));
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = (int) (Math.random() * (maxValue + 1));
        }
        return result;
    }
}
