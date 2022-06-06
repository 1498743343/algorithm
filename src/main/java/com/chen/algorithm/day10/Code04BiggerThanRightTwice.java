package com.chen.algorithm.day10;

/**
 * code04大于两倍
 * 给定一个数组 nums，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。返回给定数组中的重要翻转对的数量。
 * 本题测试链接 : https://leetcode.com/problems/reverse-pairs/
 *
 * @author chenzihan
 * @date 2022/06/06
 */
public class Code04BiggerThanRightTwice {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        return process(nums, 0, nums.length - 1);
    }

    private int process(int[] array, int left, int right) {
        if (left == right) {
            return 0;
        }
        int middle = left + ((right - left) >> 1);
        return process(array, left, middle) + process(array, middle + 1, right) + merge(array, left, middle, right);
    }

    /**
     * 合并
     * 求解过程中有两个注意点
     *
     * @param array  数组
     * @param left   左下标
     * @param middle 中间下标
     * @param right  右下标
     * @return int
     */
    private int merge(int[] array, int left, int middle, int right) {
        int result = 0;
        int windowR = middle + 1;
        for (int i = left; i <= middle; i++) {
            // 第一个注意点是 windowR 的位置是可以不用每次都从 0 开始计算，因为左右都有序，所以可以直接复用原来的值
            // 第二个注意点是 进行值比较的时候需要转为 long 类型再比较，因为 array[windowR] * 2 可能会超过 int 的最大值
            while (windowR <= right && (long) array[i] > (long) array[windowR] * 2) {
                windowR++;
            }
            result += windowR - middle - 1;
        }
        int i = 0;
        int leftPoint = left;
        int rightPoint = middle + 1;
        int[] help = new int[right - left + 1];
        while (leftPoint <= middle && rightPoint <= right) {
            help[i++] = array[leftPoint] < array[rightPoint] ? array[leftPoint++] : array[rightPoint++];
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
}
