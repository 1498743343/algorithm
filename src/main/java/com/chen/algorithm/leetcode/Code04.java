package com.chen.algorithm.leetcode;

import java.util.Arrays;

/**
 * code04 leetcode 第4题
 * <a href="https://leetcode.cn/problems/median-of-two-sorted-arrays/">测试链接</a>
 *
 * @author chenzihan
 * @date 2022/09/07
 */
public class Code04 {
    public static void main(String[] args) {
        int maxLength = 1000;
        int maxValue = (int) Math.pow(10, 6);
        int tryTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < tryTimes; i++) {
            int[] nums1 = generateRandomArray(maxValue, maxLength);
            int[] nums2 = generateRandomArray(maxValue, maxLength);
            if (nums1.length == 0 && nums2.length == 0) {
                continue;
            }
            int[] copyNums1 = Arrays.copyOf(nums1, nums1.length);
            int[] copyNums2 = Arrays.copyOf(nums2, nums2.length);
            double right = right(nums1, nums2);
            double test = findMedianSortedArrays(copyNums1, copyNums2);
            if (right != test) {
                System.out.println("出错了");
                System.out.println("right = " + right);
                System.out.println("test = " + test);
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static double right(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int length = length1 + length2;
        int[] arr = new int[length];
        System.arraycopy(nums1, 0, arr, 0, nums1.length);
        System.arraycopy(nums2, 0, arr, nums1.length, nums2.length);
        Arrays.sort(arr);
        return (arr[length / 2] + arr[(length - 1) / 2]) / 2.0;
    }

    private static int[] generateRandomArray(int maxValue, int maxLength) {
        int length = (int) (Math.random() * maxLength);
        int[] ans = new int[length];
        for (int i = 0; i < length; i++) {
            int value = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
            ans[i] = value;
        }
        Arrays.sort(ans);
        return ans;
    }

    /**
     * 整体思路就是二分法，每次根据两个数组中找到的中间值作比较，循环往复
     *
     * @param nums1 nums1
     * @param nums2 nums2
     * @return double
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int length = length1 + length2;
        int leftMid = (length + 1) / 2;
        int rightMid = (length + 2) / 2;
        return (process(nums1, 0, nums2, 0, leftMid) + process(nums1, 0, nums2, 0, rightMid)) / 2.0;
    }

    /**
     * 过程
     *
     * @param arr1   arr1
     * @param index1 arr1 的起始下标
     * @param arr2   arr2
     * @param index2 arr2 的起始下标
     * @param k      在 arr1 和 arr2 上寻找第 k 个元素
     * @return double
     */
    private static double process(int[] arr1, int index1, int[] arr2, int index2, int k) {
        // base case: 当 arr1 或 arr2 无效时，直接返回另一个数组对应下标的元素
        // 无效共有两种可能性：
        // 1. 传入的数组本身 length == 0
        // 2. index == arr.length，这种是由于在下面的判断中，根据情况对这个数组的 index 进行了调整导致的，这里不用担心 index > arr.length 的情况
        // 因为下面有对于越界的判断，index 只可能增长到和 arr.length 相等的值
        if (index1 == arr1.length) {
            return arr2[index2 + k - 1];
        }
        if (index2 == arr2.length) {
            return arr1[index1 + k - 1];
        }
        // 当 k == 1时，说明要取两个数组中合并后的第一个元素，所以返回最小值
        if (k == 1) {
            return Math.min(arr1[index1], arr2[index2]);
        }
        // 在不越界的情况下返回当前数组的第 k/2 个元素的值
        // 当越界时，说明当前数组的index 不够移动 k/2 的，所以赋最大值，在接下来在进行比较
        int value1 = index1 + k / 2 - 1 < arr1.length ? arr1[index1 + k / 2 - 1] : Integer.MAX_VALUE;
        int value2 = index2 + k / 2 - 1 < arr2.length ? arr2[index2 + k / 2 - 1] : Integer.MAX_VALUE;
        // 比较 value1 和 value2 的 index 分别移动 k/2 后的元素大小，谁小谁来真的移动这 k/2 步，然后进行下一轮比较
        if (value1 > value2) {
            // 寻找第 k/2 个元素必须用 k - k/2，因为我当前的数组只移动了 k/2，当 k 是基数时，(k - k/2) 和 k/2 的值会差1
            return process(arr1, index1, arr2, index2 + k / 2, k - k / 2);
        } else {
            return process(arr1, index1 + k / 2, arr2, index2, k - k / 2);
        }
    }


}
