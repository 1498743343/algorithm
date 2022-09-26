package com.chen.algorithm.day30;

import java.util.LinkedList;

/**
 * code01 利用单调双端队列，可以实现 O(1) 时间复杂度，求解窗口内的极值相关问题
 * <a href="https://leetcode.cn/problems/sliding-window-maximum/?favorite=2cktkvj">测试链接</a>
 *
 * @author chenzihan
 * @date 2022/09/26
 */
public class Code01SlidingWindowMaxArray {

    // 暴力的对数器方法
    public static int[] right(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return null;
        }
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        for (int left = 0; left < n - k + 1; left++) {
            int right = left + k - 1;
            int max = 0;
            for (int i = left; i <= right; i++) {
                max = Math.max(max, nums[i]);
            }
            ans[left] = max;
        }
        return ans;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return null;
        }
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int index = 0;
        // 使用双端队列来维护进窗口和出窗口的数据结构
        LinkedList<Integer> list = new LinkedList<>();
        for (int right = 0; right < n; right++) {
            // 因为求解的是范围内的最大值，所以维护一个从头到尾递减的结构
            while (!list.isEmpty() && nums[list.peekLast()] <= nums[right]) {
                list.pollLast();
            }
            list.addLast(right);
            // 检验头部元素是否过期
            if (list.peekFirst() + k == right) {
                list.pollFirst();
            }
            // 检验是否达到了开始填充值的条件
            if (right + 1 >= k) {
                ans[index++] = nums[list.peekFirst()];
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
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

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 10;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = maxSlidingWindow(arr, k);
            int[] ans2 = right(arr, k);
            if (!isEqual(ans1, ans2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
