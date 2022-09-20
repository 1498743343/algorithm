package com.chen.algorithm.leetcode;

/**
 * code96 leetcode 第96题
 * <a href="https://leetcode.cn/problems/unique-binary-search-trees/">测试链接</a>
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 * 这是一个 dp 问题
 *
 * @author chenzihan
 * @date 2022/09/20
 */
public class Code96 {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        // n == 0 时，没有元素，所以只有一种情况
        dp[0] = 1;
        // n == 1 时，只有一个头节点，也只有一种情况
        dp[1] = 1;
        // i 从 2 开始遍历
        for (int i = 2; i <= n; i++) {
            // j 是头节点的位置
            for (int j = 1; j <= i; j++) {
                // 对于一个个数为 i 的二叉树，当 j 是头节点时，左侧有 j - 1 个节点，右侧有 i - j 个节点
                // 所以当前情况下的结果等于头节点位置从 1 开始，遍历到 i 的所有情况的综合
                // dp[j-1] 是左侧的总组合数，dp[i-j] 是右侧的总组合数，他们的积就是以 j 为头节点的总组合数
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
