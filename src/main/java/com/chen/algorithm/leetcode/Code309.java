package com.chen.algorithm.leetcode;

/**
 * code309 leetcode 第309题
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/?favorite=2cktkvj">测试链接</a>
 *
 * @author chenzihan
 * @date 2022/09/29
 */
public class Code309 {
    /**
     * 对于任意一天来说，有两种状态：持有股票、不持有股票
     * 1. 持有：
     * 1) 我昨天就持有，今天没做任何操作 -> dp[i - 1][1]
     * 2) 我昨天不持有，今天买入，这里有一个限制条件，今天能买入的条件就是昨天不是冷却期，如果判定？那就是让前天不持有，不管前天不持有的状态是怎么来的，
     * 只要前天不持有，我今天就可以买入，详细分析如下：
     * 2.1 前天卖掉了，昨天是冷却期，今天可以买入
     * 2.2 前天本来就不持有，并且没做操作，那就更不影响我今天买入了
     * 2.3 如果前天没有，昨天买入了，那不好意思我今天想保持持有的状态，只能什么都不做，这种情况在 1) 中已经包括了，所以才有了 dp[i - 2][0] - prices[i]
     * 2. 不持有：
     * 1) 我昨天就不持有，今天没做任何操作 -> dp[i - 1][0]
     * 2) 我昨天持有，今天卖了 -> dp[i - 1][1] + prices[i]
     *
     * @param prices 价格
     * @return int
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 1) {
            return 0;
        }
        // 0 是不持有状态
        // 1 是持有状态
        int[][] dp = new int[n][2];
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]);
        dp[1][1] = Math.max(dp[0][0] - prices[1], dp[0][1]);
        for (int i = 2; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }
        // 最后一天肯定是不持有的值最大
        return dp[n - 1][0];
    }
}
