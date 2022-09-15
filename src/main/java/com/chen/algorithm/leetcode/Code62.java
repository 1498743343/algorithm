package com.chen.algorithm.leetcode;

/**
 * code62 leetcode 第62题
 * <a href="https://leetcode.cn/problems/unique-paths/">测试链接</a>
 * 经典的机器人走路问题，dp解决
 *
 * @author chenzihan
 * @date 2022/09/15
 */
public class Code62 {

    /**
     * 暴力递归
     *
     * @param m m
     * @param n n
     * @return int
     */
    public static int uniquePaths1(int m, int n) {
        return process(0, 0, m, n);
    }

    private static int process(int x, int y, int m, int n) {
        if (x == m - 1 || y == n - 1) {
            return 1;
        }
        return process(x + 1, y, m, n) + process(x, y + 1, m, n);
    }

    /**
     * 二维数组 dp
     *
     * @param m m
     * @param n n
     * @return int
     */
    public static int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = 1;
        for (int i = 0; i < m; i++) {
            dp[i][n - 1] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[m - 1][i] = 1;
        }
        for (int x = m - 2; x >= 0; x--) {
            for (int y = n - 2; y >= 0; y--) {
                dp[x][y] = dp[x + 1][y] + dp[x][y + 1];
            }
        }
        return dp[0][0];
    }

    /**
     * 一维数组dp
     *
     * @param m m
     * @param n n
     * @return int
     */
    public static int uniquePaths3(int m, int n) {
        int[] dp = new int[n];
        for (int y = 0; y < n; y++) {
            dp[y] = 1;
        }
        for (int x = m - 2; x >= 0; x--) {
            for (int y = n - 2; y >= 0; y--) {
                dp[y] = dp[y + 1] + dp[y];
            }
        }
        return dp[0];
    }
}
