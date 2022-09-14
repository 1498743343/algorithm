package com.chen.algorithm.leetcode;

/**
 * code34 leetcode 第34题
 * <a href="https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/">测试链接</a>
 * 题目描述的不清晰，其实就是一个递增数组，包含重复元素，求解 target 在数组中的范围
 * 使用二分法很简单就可以得到，只需要找到 >= target 最左侧的下标 和 <= target 最右侧的下标，然后判断是否符合题意即可
 *
 * @author chenzihan
 * @date 2022/09/14
 */
public class Code34 {
    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] result = searchRange(nums, target);
        System.out.println("[" + result[0] + "," + result[1] + "]");
    }

    public static int[] searchRange(int[] nums, int target) {
        int length = nums.length;
        if (length == 0 || nums[0] > target || nums[length - 1] < target) {
            return new int[]{-1, -1};
        }
        int left = 0;
        int right = length - 1;
        int leftIndex = -1;
        int rightIndex = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target) {
                leftIndex = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        left = 0;
        right = length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= target) {
                rightIndex = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (leftIndex <= rightIndex && leftIndex >= 0 && rightIndex <= length - 1 && nums[leftIndex] == target && nums[rightIndex] == target) {
            return new int[]{leftIndex, rightIndex};
        }
        return new int[]{-1, -1};
    }
}
