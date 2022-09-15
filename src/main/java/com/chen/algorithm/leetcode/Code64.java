package com.chen.algorithm.leetcode;

/**
 * code64 leetcode 第64题
 * <a href="https://leetcode.cn/problems/minimum-path-sum/submissions/">测试链接</a>
 * 最小路径和，经典的 dp 问题
 *
 * @author chenzihan
 * @date 2022/09/15
 */
public class Code64 {
    public int minPathSum1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = grid[m - 1][n - 1];
        for (int y = n - 2; y >= 0; y--) {
            dp[m - 1][y] = dp[m - 1][y + 1] + grid[m - 1][y];
        }
        for (int x = m - 2; x >= 0; x--) {
            dp[x][n - 1] = dp[x + 1][n - 1] + grid[x][n - 1];
        }

        for (int x = m - 2; x >= 0; x--) {
            for (int y = n - 2; y >= 0; y--) {
                int min = Math.min(dp[x + 1][y], dp[x][y + 1]);
                dp[x][y] = min + grid[x][y];
            }
        }
        return dp[0][0];
    }

    public int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        dp[n - 1] = grid[m - 1][n - 1];
        for (int y = n - 2; y >= 0; y--) {
            dp[y] = dp[y + 1] + grid[m - 1][y];
        }
        for (int x = m - 2; x >= 0; x--) {
            for (int y = n - 1; y >= 0; y--) {
                if (y == n - 1) {
                    dp[y] += grid[x][y];
                } else {
                    int min = Math.min(dp[y], dp[y + 1]);
                    dp[y] = min + grid[x][y];
                }
            }
        }
        return dp[0];
    }
}
