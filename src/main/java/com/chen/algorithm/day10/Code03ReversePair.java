package com.chen.algorithm.day10;

import java.util.Arrays;

/**
 * code03 求解数组的逆序对个数
 * 在一个数组中，任何一个前面的数a，和任何一个后面的数b，如果(a,b)是降序的，就称为逆序对
 * 返回数组中所有的逆序对
 *
 * @author chenzihan
 * @date 2022/06/06
 */
public class Code03ReversePair {


    public static void main(String[] args) {
        int maxLength = 100;
        int maxValue = 1000;
        int tryTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < tryTimes; i++) {
            int[] array = generateRandomArray(maxLength, maxValue);
            int[] copy = Arrays.copyOf(array, array.length);
            if (reversePair(array) != test(copy)) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static int test(int[] array) {
        if (array == null || array.length <= 1) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    result++;
                }
            }
        }
        return result;
    }

    private static int reversePair(int[] array) {
        if (array == null || array.length <= 1) {
            return 0;
        }
        return process(array, 0, array.length - 1);
    }

    private static int process(int[] array, int left, int right) {
        if (left == right) {
            return 0;
        }
        int middle = left + ((right - left) >> 1);
        return process(array, left, middle) + process(array, middle + 1, right) + merge(array, left, middle, right);
    }

    /**
     * 这里也可以使用倒序遍历来计算逆序对的个数
     *
     * @param array  数组
     * @param left   左下标
     * @param middle 中间下标
     * @param right  右下标
     * @return int
     */
    private static int merge(int[] array, int left, int middle, int right) {
        int leftPoint = left;
        int rightPoint = middle + 1;
        int[] help = new int[right - left + 1];
        int i = 0;
        int result = 0;
        while (leftPoint <= middle && rightPoint <= right) {
            if (array[leftPoint] <= array[rightPoint]) {
                help[i++] = array[leftPoint++];
            } else {
                result += middle - leftPoint + 1;
                help[i++] = array[rightPoint++];
            }
        }
        while (leftPoint <= middle) {
            help[i++] = array[leftPoint++];
        }
        while (rightPoint <= right) {
            help[i++] = array[rightPoint++];
        }
        for (i = 0; i < help.length; i++) {
            array[left + i] = help[i];
        }
        return result;
    }

    private static int[] generateRandomArray(int maxLength, int maxValue) {
        int length = (int) (Math.random() * (maxLength + 1));
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = generateRandomNumber(maxValue);
        }
        return result;
    }

    private static int generateRandomNumber(int maxValue) {
        return ((int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1)));
    }
}
