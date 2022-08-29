package com.chen.algorithm.day25;

/**
 * code01 背包
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 *
 * @author chenzihan
 * @date 2022/08/29
 */
public class Code01Knapsack {

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }

    /**
     * 动态规划：由 maxValue 方法可知，结果只和 rest 以及 index 有关，所以建立二维数组就可以解决
     *
     * @param weights 权重
     * @param values  值
     * @param bag     袋
     * @return int
     */
    private static int dp(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length != values.length) {
            return 0;
        }
        int length = values.length;
        // 通过 maxValue 方法可知，对于任意一个位置: dp[i][j] 和两个位置有关系: dp[i][j+1] 和 dp[i-weight[i]][j+1]
        // 而我们的 base case是 j == values.length时返回 0 ，所以我们需要从 j == length-1 开始填充数组
        int[][] dp = new int[bag + 1][length + 1];
        for (int index = length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = -1;
                if (rest - weights[index] >= 0) {
                    p1 = values[index] + dp[rest - weights[index]][index + 1];
                }
                int p2 = dp[rest][index + 1];
                dp[rest][index] = Math.max(p1, p2);
            }
        }
        return dp[bag][0];
    }

    private static int maxValue(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length != values.length) {
            return 0;
        }
        return process(weights, values, bag, 0);
    }

    /**
     * 暴力递归：对于 index 位置的货物，我可以选择要也可以选择不要，这样就可以拿到所有的可能性
     *
     * @param weights 权重
     * @param values  值
     * @param rest    剩余容量
     * @param index   指数
     * @return int
     */
    private static int process(int[] weights, int[] values, int rest, int index) {
        // 当背包的容量为负数时，说明不是一个正确的结果，直接剪支
        if (rest < 0) {
            return -1;
        }
        // base case: 当处于越界位置时，说明已经遍历完成，返回
        if (index == values.length) {
            return 0;
        }
        // 要当前货物
        int p1 = process(weights, values, rest - weights[index], index + 1);
        // 如果要当前货物，但是 rest 不够了，说明不是一个有效结果，返回 0，因为 rest 之前是大于 0 的，减去当前货物以后小于 0 的，之前的结果是有效的
        if (p1 == -1) {
            return 0;
        } else {
            p1 = p1 + values[index];
        }
        // 不要当前货物
        int p2 = process(weights, values, rest, index + 1);
        return Math.max(p1, p2);
    }

}
