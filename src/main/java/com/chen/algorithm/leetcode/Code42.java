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

    /**
     * 双指针
     *
     * @param height 高度
     * @return int
     */
    public int trap1(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }
        int n = height.length;
        int left = 0;
        int right = n - 1;
        // 先给 leftMax 和 rightMax 赋初始值
        int leftMax = height[0];
        int rightMax = height[n - 1];
        int sum = 0;
        while (left < right) {
            // 如果 height[left] < height[right]，则必有 leftMax < rightMax，所以 leftMax 决定了 left 位置的存水量
            if (height[left] < height[right]) {
                leftMax = Math.max(height[left], leftMax);
                sum += leftMax - height[left++];
            } else {
                // 如果 height[left] >= height[right]，则必有 leftMax >= rightMax，所以 rightMax 决定了 right 位置的存水量
                rightMax = Math.max(rightMax, height[right]);
                sum += rightMax - height[right--];
            }
        }
        return sum;
    }

    /**
     * 单调栈
     *
     * @param height 高度
     * @return int
     */
    public int trap2(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }
        int n = height.length;
        int[] stack = new int[n];
        int si = -1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int cur = height[i];
            // 做一个由下到上递减的单调栈，如果发现栈顶元素小于 cur 时，那么就知道以当前位置为底，即以 height[stack[si]] 为底，可以存水的矩形的面积
            while (si != -1 && height[stack[si]] < cur) {
                int num = height[stack[si--]];
                // 这是一个比较坑的地方，在计算矩形面积的题中，在 while 循环中，如果 si == -1，当前的 num 也可以计算面积，但是这里是不能存水的，所以要 break
                if (si == -1) {
                    break;
                }
                int left = stack[si];
                sum += (Math.min(height[left], height[i]) - num) * (i - left - 1);
            }
            stack[++si] = i;
        }
        return sum;
    }
}
