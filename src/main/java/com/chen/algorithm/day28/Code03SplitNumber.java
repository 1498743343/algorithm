package com.chen.algorithm.day28;

/**
 * code03分裂数
 * 给定一个正数n，求n的裂开方法数，规定：后面的数不能比前面的数小
 * 比如4的裂开方法有：1+1+1+1、1+1+2、1+3、2+2、4
 * 5种，所以返回5
 *
 * @author chenzihan
 * @date 2022/09/02
 */
public class Code03SplitNumber {

    public static void main(String[] args) {
        int maxValue = 50;
        int tryTimes = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < tryTimes; i++) {
            int value = (int) (Math.random() * maxValue) + 1;
//            int value = 3;
            int ans1 = ways(value);
            int ans2 = dp1(value);
            int ans3 = dp2(value);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("测试出错了");
                System.out.println("value = " + value);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                System.out.println("ans3 = " + ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }

    /**
     * 递归方法
     *
     * @param aim 目标值
     * @return int
     */
    private static int ways(int aim) {
        if (aim == 1) {
            return 1;
        }
        // 调用递归时，我们认为之前的值是 1，剩余 aim
        return process(1, aim);
    }

    /**
     * 递归：因为要保证当前值不能小于前一个值，所以我需要一个参数来记录前一个值，除此之外我还需要记录剩余的值，这样就可以得到答案
     *
     * @param pre  我的上一个值
     * @param rest 剩余值
     * @return int
     */
    private static int process(int pre, int rest) {
        // base case：rest == 0的时候，此时说明已经完成目标了，需要返回 1
        if (rest == 0) {
            return 1;
        }
        // base case：当 pre 大于 rest时，那么我肯定不能完成目标，说明这条路是错误的
        if (pre > rest) {
            return 0;
        }
        int ways = 0;
        for (int i = 0; i <= rest - pre; i++) {
            ways += process(pre + i, rest - pre - i);
        }
        return ways;
    }

    /**
     * 动态规划一：由递归方法可知，结果和两个变量有关：pre,rest
     * 二维数组解决
     *
     * @param aim 目标值
     * @return int
     */
    private static int dp1(int aim) {
        if (aim == 1) {
            return 1;
        }
        int[][] dp = new int[aim + 1][aim + 1];
        for (int pre = 1; pre <= aim; pre++) {
            dp[pre][0] = 1;
        }
        for (int pre = aim; pre >= 1; pre--) {
            for (int rest = pre; rest <= aim; rest++) {
                int ways = 0;
                for (int i = 0; i <= rest - pre; i++) {
                    ways += dp[pre + i][rest - pre - i];
                }
                dp[pre][rest] = ways;
            }
        }
        return dp[1][aim];
    }

    /**
     * 动态规划二：由动态规划一方法可知，存在枚举行为，观察位置关系，干掉最里层的 for 循环
     *
     * @param aim 目标值
     * @return int
     */
    private static int dp2(int aim) {
        if (aim == 1) {
            return 1;
        }
        int[][] dp = new int[aim + 1][aim + 1];
        for (int pre = 1; pre <= aim; pre++) {
            dp[pre][0] = 1;
        }
        // 因为简化后的表达式存在 pre+1，所以一定要让 pre <= aim-1，但这样我们就没有给 dp[aim][aim] 点赋值
        // 所以在这里提前给这个点赋值
        dp[aim][aim] = 1;
        for (int pre = aim - 1; pre >= 1; pre--) {
            for (int rest = pre; rest <= aim; rest++) {
                // 由于填充数组的顺序是从下到上，从左到右，所以观察依赖关系时，我们需要看左边的节点和下面的节点
                // 左侧节点观察后没有发现关联关系，所以只能看下面的点，即 dp[pre+1][rest]
                // dp[pre][rest] = dp[pre][rest-pre] + dp[pre+1][rest-pre-1] + ... + dp[rest][0]
                // dp[pre+1][rest] = dp[pre+1][rest-pre-1] + dp[pre+2][rest-pre-2] + .. + dp[rest][0]
                // 所以可得: dp[pre][rest] = dp[pre][rest-pre] + dp[pre+1][rest]
                dp[pre][rest] = dp[pre][rest - pre] + dp[pre + 1][rest];
            }
        }
        return dp[1][aim];
    }
}
