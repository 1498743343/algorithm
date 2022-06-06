package com.chen.algorithm.day10;

import java.util.Arrays;

/**
 * code01归并排序
 * 归并排序的核心是分治思想，先使局部有序，然后进行合并操作，最后整体有序。整体来讲可以分为三个步骤：
 * 分解（Divide）：将n个元素分成个含n/2个元素的子序列。
 * 解决（Conquer）：用合并排序法对两个子序列递归的排序。
 * 合并（Combine）：合并两个已排序的子序列已得到排序结果。
 *
 * @author chenzihan
 * @date 2022/06/06
 */
public class Code01MergeSort {

    /**
     * 使用递归完成归并排序
     *
     * @param arr 加勒比海盗
     */
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    /**
     * 拆解过程
     *
     * @param arr   数组
     * @param left  左下标
     * @param right 右下标
     */
    private static void process(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int middle = left + ((right - left) >> 1);
        process(arr, left, middle);
        process(arr, middle + 1, right);
        merge(arr, left, middle, right);
    }

    private static void merge(int[] arr, int left, int middle, int right) {
        int leftPoint = left;
        int rightPoint = middle + 1;
        int[] help = new int[right - left + 1];
        int i = 0;
        // 左右两边都不越界时，进行对比然后放入数组
        while (leftPoint <= middle && rightPoint <= right) {
            help[i++] = arr[leftPoint] <= arr[rightPoint] ? arr[leftPoint++] : arr[rightPoint++];
        }
        // 左右两部分的指针有一个越界时，就执行下面两个 while 循环的其中一个，两个 while 不会同时执行
        while (leftPoint <= middle) {
            help[i++] = arr[leftPoint++];
        }
        while (rightPoint <= right) {
            help[i++] = arr[rightPoint++];
        }
        // 左右两部分都遍历完以后，将排好序的 help 数组中的元素添加到 arr 中
        for (i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }
    }

    /**
     * 使用迭代法完成归并排序
     *
     * @param arr 加勒比海盗
     */
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int mergeSize = 1;
        int length = arr.length;
        // 以 mergeSize 为单位，每次进行局部 merge 时，左侧和右侧的长度均为 mergeSize，不够时剩下的进行合并；直到 length/2 < mergeSize < length
        while (mergeSize < length) {
            int left = 0;
            // 以 left 为指针，每次循环使数组在固定长度上有序，然后合并
            while (left < length) {
                // 当剩余的元素个数 <= mergeSize 时，就不需要再进行排序，因为此时排序 middle + 1 的坐标会造成数组下标越界，剩下的元素会在 mergeSize * 2 后的循环里进行排序
                if (mergeSize >= length - left) {
                    break;
                }
                int middle = left + mergeSize - 1;
                // length - middle - 1 的值是 [middle+1,length-1] 上的 size，如果 size <= mergeSize，则将 right 定位到 length - 1 位置即可，否则会越界
                int right = middle + Math.min(mergeSize, length - middle - 1);
                merge(arr, left, middle, right);
                left = right + 1;
            }
            if (mergeSize * 2 > length) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    public static void main(String[] args) {
        int length = 100;
        int value = 100;
        int tryTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < tryTimes; i++) {
            int[] array = generateRandomArray(length, value);
            int[] copyArray = Arrays.copyOf(array, array.length);
            mergeSort2(array);
            Arrays.sort(copyArray);
            for (int j = 0; j < array.length; j++) {
                if (array[j] != copyArray[j]) {
                    System.out.println("出错了");
                    break;
                }
            }
        }
        System.out.println("测试结束");
    }

    public static int[] generateRandomArray(int maxLength, int maxValue) {
        int length = (int) (Math.random() * (maxLength + 1));
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = generateRandomNumber(maxValue);
        }
        return result;
    }

    public static int generateRandomNumber(int maxValue) {
        return ((int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1)));
    }
}
