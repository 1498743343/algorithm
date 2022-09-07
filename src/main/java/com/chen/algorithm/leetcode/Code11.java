package com.chen.algorithm.leetcode;

/**
 * code11 leetcode 第11题
 * <a href="https://leetcode.cn/problems/container-with-most-water/comments/">测试链接</a>
 * 这道题，我从暴力递归优化到了一维数组的动态规划，但还是超时了，说明肯定有更优解
 * 后来看到其他人的写法使用到了双指针，下面写上自己的版本
 *
 * @author chenzihan
 * @date 2022/09/07
 */
public class Code11 {
    public static void main(String[] args) {
        int[] test = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(dp1(test));
        System.out.println(dp2(test));
        System.out.println(dp3(test));
        System.out.println(maxArea(test));
    }

    public static int dp1(int[] height) {
        return process(height, 0, height.length - 1);
    }

    private static int process(int[] height, int left, int right) {
        if (left == right) {
            return 0;
        } else if (left == right - 1) {
            return Math.min(height[left], height[right]);
        }
        // 三种可能性
        // 1. 最大值在[left,right-1]上
        int p1 = process(height, left, right - 1);
        // 2. 最大值在[left+1,right]上
        int p2 = process(height, left + 1, right);
        // 3. 最大值就是 left,right
        int p3 = (right - left) * (Math.min(height[left], height[right]));
        return Math.max(p1, Math.max(p2, p3));
    }

    public static int dp2(int[] height) {
        int length = height.length;
        int[][] dp = new int[length][length];
        for (int left = length - 2; left >= 0; left--) {
            dp[left][left + 1] = Math.min(height[left], height[left + 1]);
            for (int right = left + 1; right < length; right++) {
                // 1. 最大值在[left,right-1]上
                int p1 = dp[left][right - 1];
                // 2. 最大值在[left+1,right]上
                int p2 = dp[left + 1][right];
                // 3. 最大值就是 left,right
                int p3 = (right - left) * (Math.min(height[left], height[right]));
                dp[left][right] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[0][length - 1];
    }

    public static int dp3(int[] height) {
        int length = height.length;
        int[] dp = new int[length];
        for (int left = length - 2; left >= 0; left--) {
            dp[left + 1] = Math.min(height[left], height[left + 1]);
            for (int right = left + 2; right < length; right++) {
                // 1. 最大值在[left,right-1]上
                int p1 = dp[right - 1];
                // 2. 最大值在[left+1,right]上
                int p2 = dp[right];
                // 3. 最大值就是 left,right
                int p3 = (right - left) * (Math.min(height[left], height[right]));
                dp[right] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[length - 1];
    }

    public static int maxArea(int[] height) {
        int length = height.length;
        int left = 0;
        int right = length - 1;
        int ans = 0;
        while (left < right) {
            int cur = Math.min(height[left], height[right]) * (right - left);
            ans = Math.max(cur, ans);
            // 左右两个点，谁小干掉谁，保证下一次可能更大，给更新留下可能性
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return ans;
    }
}
