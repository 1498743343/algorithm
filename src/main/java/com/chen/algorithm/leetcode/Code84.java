package com.chen.algorithm.leetcode;

import java.util.Stack;

/**
 * code84 leetcode 第84题
 * <a href="https://leetcode.cn/problems/largest-rectangle-in-histogram/?favorite=2cktkvj">测试链接</a>
 * 单调栈经典问题，求矩形最大面积
 *
 * @author chenzihan
 * @date 2022/09/16
 */
public class Code84 {

    /**
     * 使用系统栈实现
     *
     * @param heights 高度
     * @return int
     */
    public static int largestRectangleArea1(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            int num = heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] >= num) {
                int cur = heights[stack.pop()];
                int left = stack.isEmpty() ? 0 : stack.peek() + 1;
                int right = i - 1;
                max = Math.max(max, (right - left + 1) * cur);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int cur = heights[stack.pop()];
            max = Math.max(max, (n - (stack.isEmpty() ? 0 : stack.peek() + 1)) * cur);
        }
        return max;
    }

    /**
     * 使用数组模拟栈结构实现
     *
     * @param heights 高度
     * @return int
     */
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] stack = new int[n];
        int max = 0;
        int sIndex = -1;
        for (int i = 0; i < n; i++) {
            int num = heights[i];
            while (sIndex != -1 && heights[stack[sIndex]] >= num) {
                int cur = heights[stack[sIndex--]];
                int left = sIndex == -1 ? 0 : stack[sIndex] + 1;
                int right = i - 1;
                max = Math.max(max, (right - left + 1) * cur);
            }
            stack[++sIndex] = i;
        }
        while (sIndex != -1) {
            int cur = heights[stack[sIndex--]];
            max = Math.max(max, (n - (sIndex == -1 ? 0 : stack[sIndex] + 1)) * cur);
        }
        return max;
    }
}
