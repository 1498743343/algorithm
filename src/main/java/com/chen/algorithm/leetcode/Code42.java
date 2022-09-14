package com.chen.algorithm.leetcode;

/**
 * code42 leetcode 第42题
 * <a href="https://leetcode.cn/problems/trapping-rain-water/">测试链接</a>
 * 思考：整体思路就是在每个位置 i 求解自己可以接到雨水的量，这取决于 i 位置左右两侧的高度的最大值中的小值，即 leftMax 和 rightMax 中较小的值
 * 这样每次到了一个位置我都要去遍历一遍数组，时间复杂度为 O(n^2)<br/>
 * 优化：正向遍历一遍数组，我们就可以得到每个位置上的左侧的最大值，我们可以拿到 left 数组；反向遍历一遍数组我们就可以得到每个位置上右侧的最大值，我们可以拿到 right 数组。
 * 这样我们就能知道每个位置上的左右侧的最大值，由此可得答案<br/>
 * <img width="640" height="320" src="https://fastly.jsdelivr.net/gh/1498743343/images@master/images/16631524510321.png" alt="">
 *
 * @author chenzihan
 * @date 2022/09/14
 */
public class Code42 {
    /**
     * 动态规划
     *
     * @param height 高度
     * @return int
     */
    public int trap(int[] height) {
        int length = height.length;
        int leftMax = 0;
        int[] left = new int[length];
        for (int i = 0; i < length; i++) {
            leftMax = Math.max(leftMax, height[i]);
            left[i] = leftMax;
        }

        int rightMax = 0;
        int[] right = new int[length];
        for (int i = length - 1; i >= 0; i--) {
            rightMax = Math.max(rightMax, height[i]);
            right[i] = rightMax;
        }
        int ans = 0;
        for (int i = 0; i < length; i++) {
            ans += Math.min(left[i], right[i]) - height[i];
        }
        return ans;
    }
}
