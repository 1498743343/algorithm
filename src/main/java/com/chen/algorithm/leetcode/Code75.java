package com.chen.algorithm.leetcode;

/**
 * code75 leetcode 第75题
 * <a href="https://leetcode.cn/problems/sort-colors/">测试链接</a>
 * 颜色分类：这是一个经典的荷兰国旗问题，即快排的其中一步
 *
 * @author chenzihan
 * @date 2022/09/15
 */
public class Code75 {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int left = -1;
        int right = n;
        int index = 0;
        while (index < right) {
            if (nums[index] < 1) {
                swap(nums, ++left, index++);
            } else if (nums[index] == 1) {
                index++;
            } else {
                swap(nums, --right, index);
            }
        }
    }

    public void swap(int[] arr, int i1, int i2) {
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }
}
