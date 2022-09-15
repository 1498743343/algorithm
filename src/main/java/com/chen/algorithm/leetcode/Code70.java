package com.chen.algorithm.leetcode;

/**
 * code70 leetcode 第70题
 * <a href="https://leetcode.cn/problems/climbing-stairs/">测试链接</a>
 * 爬楼梯：经典的 dp 问题
 *
 * @author chenzihan
 * @date 2022/09/15
 */
public class Code70 {
    /**
     * 暴力递归
     *
     * @param n n
     * @return int
     */
    public int climbStairs1(int n) {
        return process(0, n);
    }

    public int process(int index, int n) {
        if (index == n) {
            return 1;
        }
        int ans = 0;
        ans += process(index + 1, n);
        if (index + 2 <= n) {
            ans += process(index + 2, n);
        }
        return ans;
    }

    /**
     * dp1
     *
     * @param n n
     * @return int
     */
    public int climbStairs2(int n) {
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            int ans = 0;
            ans += dp[i + 1];
            if (i + 2 <= n) {
                ans += dp[i + 2];
            }
            dp[i] = ans;
        }
        return dp[0];
    }

    /**
     * dp2：优化逻辑
     *
     * @param n n
     * @return int
     */
    public int climbStairs3(int n) {
        if (n == 1) {
            return 1;
        } else {
            int[] dp = new int[n + 1];
            dp[n] = 1;
            dp[n - 1] = 1;
            for (int i = n - 2; i >= 0; i--) {
                dp[i] = dp[i + 1] + dp[i + 2];
            }
            return dp[0];
        }
    }

    /**
     * dp4
     *
     * @param n n
     * @return int
     */
    public int climbStairs4(int n) {
        int i1 = 1;
        int i2 = 0;
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            ans = i1 + i2;
            i2 = i1;
            i1 = ans;
        }
        return ans;
    }
}
