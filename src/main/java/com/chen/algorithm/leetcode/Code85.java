package com.chen.algorithm.leetcode;

/**
 * code85 leetcode 第85题
 * <a href="https://leetcode.cn/problems/maximal-rectangle/?favorite=2cktkvj">测试链接</a>
 * 本题与 84 题是一道题，不过这个是用数组拼成的柱状图
 *
 * @author chenzihan
 * @date 2022/09/20
 */
public class Code85 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int max = 0;
        int[] heights = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                heights[j] = matrix[i][j] == '0' ? 0 : heights[j] + 1;
            }
            int curMax = process(heights);
            max = Math.max(curMax, max);
        }
        return max;
    }

    private int process(int[] heights) {
        int n = heights.length;
        int[] stack = new int[n];
        int si = -1;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int cur = heights[i];
            while (si != -1 && heights[stack[si]] >= cur) {
                int pop = heights[stack[si--]];
                int left = si == -1 ? 0 : stack[si] + 1;
                int right = i - 1;
                max = Math.max(max, (right - left + 1) * pop);
            }
            stack[++si] = i;
        }
        while (si != -1) {
            int pop = heights[stack[si--]];
            int left = si == -1 ? 0 : stack[si] + 1;
            max = Math.max(max, (n - left) * pop);
        }
        return max;
    }
}
