package com.chen.algorithm.leetcode;

/**
 * code121 leetcode 第121题
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/?favorite=2cktkvj">测试链接</a>
 *
 * @author chenzihan
 * @date 2022/09/20
 */
public class Code121 {
    public int maxProfit(int[] prices) {
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int price : prices) {
            min = Math.min(min, price);
            max = Math.max(max, price - min);
        }
        return max;
    }
}
