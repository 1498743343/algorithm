package com.chen.algorithm.day11;

/**
 * code01
 * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 * 测试链接： https://leetcode.cn/problems/count-of-range-sum/
 *
 * @author chenzihan
 * @date 2022/06/07
 */
public class Code01CountOfRangeSum {

    /**
     * 计算范围和
     * 将此问题转换为前缀数组进行求解：
     * 原来数组上 [0,i] 位置上的累加和赋值给 sum[i]，这样求解满足条件的 [i,j] 的累加和的个数就成了求解 sum[j] - sum[i-1] 满足条件的个数
     * 需要注意，因为将 int 类型的数组累加，所以 sum 可能超过 int 类型的最大值，所以将 sum 声明为 long 类型的数组
     *
     * @param nums  数组
     * @param lower 小值
     * @param upper 大值
     * @return int
     */
    public static int countRangeSum(int[] nums, int lower, int upper) {
        long[] sums = new long[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
        return process(sums, 0, sums.length - 1, lower, upper);
    }

    private static int process(long[] array, int left, int right, int lower, int upper) {
        // base case
        if (left == right) {
            if (array[left] >= lower && array[left] <= upper) {
                return 1;
            } else {
                return 0;
            }
        }
        int middle = left + ((right - left) >> 1);
        return process(array, left, middle, lower, upper) + process(array, middle + 1, right, lower, upper) + merge(array, left, middle, right, lower, upper);
    }

    /**
     * 归并中的合并操作
     *
     * @param array  数组
     * @param left   左下标
     * @param middle 中间下标
     * @param right  右下标
     * @param lower  小值
     * @param upper  大值
     * @return int
     */
    private static int merge(long[] array, int left, int middle, int right, int lower, int upper) {
        int windowLeft = left;
        int windowRight = left;
        int rightIndex = middle + 1;
        int result = 0;
        // 遍历右组，看看每个值在左组中有多少个满足条件的值；每次循环无需重置 windowLeft 和 windowRight 的值，这得益于左组和右组的数组都是有序的
        while (rightIndex <= right) {
            // 找出能满足当前右组值的值区间，然后去左组中寻找
            long min = array[rightIndex] - upper;
            long max = array[rightIndex] - lower;
            // 计算左组中满足条件的右边界，注意这里的 array[windowRight] <= max，条件中包含 ==，因为 array[windowRight] == max 也是满足要求的
            while (windowRight <= middle && array[windowRight] <= max) {
                windowRight++;
            }
            // 计算左组中满足条件的左边界，注意这里的 array[windowLeft] < min，条件中不包含 ==，因为 array[windowLeft] == min 不满足要求
            while (windowLeft <= middle && array[windowLeft] < min) {
                windowLeft++;
            }
            result += windowRight - windowLeft;
            rightIndex++;
        }
        int leftPoint = left;
        int rightPoint = middle + 1;
        int i = 0;
        long[] help = new long[right - left + 1];
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
