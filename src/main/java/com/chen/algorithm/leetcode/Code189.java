package com.chen.algorithm.leetcode;

/**
 * code189 leetcode 第189题
 * <a href="https://leetcode.cn/problems/rotate-array/?envType=study-plan-v2&envId=top-100-liked">测试链接</a>
 *
 * @author chenzihan
 */
public class Code189 {
    /**
     * 本题主要有两种解题方法：
     * 1. 数学，涉及到求解最大公因数和最小公倍数，这里不做解析
     * 2. 翻转数组：先把数组整个翻转。然后把 0~k-1 翻转，最后把 k~n-1 翻转。原理：整体翻转以后，数组成倒叙排列， 0~k-1 翻转相当于把最后的 k 个按顺序移动
     * 到了头部，最后翻转 k~n-1 相当于把原来的头部的 n-k个元素向右移动 k 个位置，这样就完成了
     * @param nums 数组
     * @param k 向右移动的个数
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while(start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
}