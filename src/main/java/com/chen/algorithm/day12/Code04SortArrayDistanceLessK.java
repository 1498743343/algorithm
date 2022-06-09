package com.chen.algorithm.day12;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * code04
 * 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
 *
 * @author chenzihan
 * @date 2022/06/09
 */
public class Code04SortArrayDistanceLessK {

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

    public static void main(String[] args) {
        int maxLength = 500;
        int maxValue = Integer.MAX_VALUE;
        int tryTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < tryTimes; i++) {
            int k = (int) (Math.random() * maxLength) + 1;
            int[] array = generateConformRandomArray(maxLength, maxValue, k);
            int[] array1 = Arrays.copyOf(array, array.length);
            int[] array2 = Arrays.copyOf(array, array.length);
            heapSort(array1, k);
            Arrays.sort(array2);
            if (!isEqual(array1, array2)) {
                System.out.println("出错了");
                printArray(array);
                printArray(array1);
                printArray(array2);
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static void heapSort(int[] array, int k) {
        if (array == null || array.length <= 1) {
            return;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        // k 可能大于 array.length ，取两数的最小值
        int size = Math.min(k, array.length);
        int index = 0;
        while (index < size) {
            heap.add(array[index++]);
        }
        int i = 0;
        while (index < array.length) {
            Integer poll = heap.poll();
            array[i++] = poll;
            heap.add(array[index++]);
        }
        while (!heap.isEmpty()) {
            Integer poll = heap.poll();
            array[i++] = poll;
        }
    }

    private static int[] generateConformRandomArray(int maxLength, int maxValue, int k) {
        int length = (int) (Math.random() * (maxLength + 1));
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = (int) (Math.random() * (maxValue + 1));
        }
        //将生成的随机数组排好序后，进行随机交换，保证交换的距离 <= k
        Arrays.sort(result);
        boolean[] isSwapped = new boolean[length];
        for (int i = 0; i < length; i++) {
            // 取随即坐标和最大坐标中的最小值
            int j = Math.min((i + (int) (Math.random() + (k + 1))), length - 1);
            // 两个坐标都没有交换过，就进行交换
            if (!isSwapped[i] && isSwapped[j]) {
                isSwapped[i] = true;
                isSwapped[j] = true;
                int tmp = result[i];
                result[i] = result[j];
                result[j] = tmp;
            }
        }
        return result;
    }

}