package com.chen.algorithm.day28;

/**
 * code01 杀死怪兽
 * 给定3个参数，N，M，K：怪兽有N滴血，等着英雄来砍自己，英雄每一次打击，都会让怪兽流失[0~M]的血量，每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 *
 * @author chenzihan
 * @date 2022/09/02
 */
public class Code01KillMonster2 {

    public static void main(String[] args) {
        int nMax = 10;
        int mMax = 10;
        int kMax = 10;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * nMax);
            int m = (int) (Math.random() * mMax);
            int k = (int) (Math.random() * kMax);
            double ans1 = right(n, m, k);
            double ans2 = dp1(n, m, k);
            double ans3 = dp2(n, m, k);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                System.out.println("n = " + n);
                System.out.println("m = " + m);
                System.out.println("k = " + k);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                System.out.println("ans3 = " + ans3);
                break;
            }
        }
        System.out.println("测试结束");
//        int n = 6;
//        int m = 4;
//        int k = 7;
//        double ans1 = right(n, m, k);
//        double ans2 = dp1(n, m, k);
//        double ans3 = dp2(n, m, k);
//        System.out.println("ans1 = " + ans1);
//        System.out.println("ans2 = " + ans2);
//        System.out.println("ans3 = " + ans3);
    }

    /**
     * 暴力递归
     *
     * @param n n 滴血
     * @param m 每次砍掉 [0~m] 滴血
     * @param k 砍 k 次
     * @return long
     */
    private static double right(int n, int m, int k) {
        // 血量大于砍的上限，说明怎么也砍不死
        if (n <= 0 || m <= 0 || k <= 0 || n > m * k) {
            return 0;
        }
        double all = Math.pow(m + 1, k);
        return process(n, k, m) / all;
    }

    private static long process(int restHp, int restTimes, int m) {
        // 能走到最后一步说明肯定没死，返回 0
        if (restTimes == 0) {
            return 0;
        }
        long ways = 0;
        // 把 restHp < 0 的情况直接在 for 循环的条件中限制死
        for (int i = 0; i <= m; i++) {
            // 如果我这一刀下去，没死，说明可以继续往后走
            if (restHp - i > 0) {
                ways += process(restHp - i, restTimes - 1, m);
            } else {
                // 如果我这一刀下去死了，后面也不需要再递归了，直接能得到结果
                ways += Math.pow(m + 1, restTimes - 1);
            }
        }
        return ways;
    }

    /**
     * 动态规划一：
     * 根据递归方法可以知道一个普遍位置的点的值和两个变量有关系：n,k
     * 所以建立二维数组解决
     *
     * @param n n 滴血
     * @param m 每次砍掉 [0~m] 滴血
     * @param k 砍 k 次
     * @return long
     */
    private static double dp1(int n, int m, int k) {
        // 血量大于砍的上限，说明怎么也砍不死
        if (n <= 0 || m <= 0 || k <= 0 || n > m * k) {
            return 0;
        }
        long[][] dp = new long[n + 1][k + 1];
        for (int restHp = 1; restHp <= n; restHp++) {
            // 当还有剩余血量，但是没有剩余次数时，肯定砍不死了，所以 restTimes 从 1 开始
            for (int restTimes = 1; restTimes <= k; restTimes++) {
                long ways = 0;
                for (int i = 0; i <= m; i++) {
                    // 如果我这一刀下去，没死，说明可以继续往后走
                    if (restHp - i > 0) {
                        ways += dp[restHp - i][restTimes - 1];
                    } else {
                        // 如果我这一刀下去死了，后面也不需要再递归了，直接能得到结果
                        ways += Math.pow(m + 1, restTimes - 1);
                    }
                }
                dp[restHp][restTimes] = ways;
            }
        }
        double all = Math.pow(m + 1, k);
        return dp[n][k] / all;
    }

    /**
     * 动态规划二：
     * 根据动态规划一可知，最里层的 for 循环是枚举行为，所以观察点之间的依赖关系，干掉 for 循环
     *
     * @param n n 滴血
     * @param m 每次砍掉 [0~m] 滴血
     * @param k 砍 k 次
     * @return long
     */
    private static double dp2(int n, int m, int k) {
        // 血量大于砍的上限，说明怎么也砍不死
        if (n <= 0 || m <= 0 || k <= 0 || n > m * k) {
            return 0;
        }
        long[][] dp = new long[n + 1][k + 1];
        dp[0][0] = 1;
        for (int restTimes = 1; restTimes <= k; restTimes++) {
            dp[0][restTimes] = (long) Math.pow(m + 1, restTimes);
            for (int restHp = 1; restHp <= n; restHp++) {
                // 当还有剩余血量，但是没有剩余次数时，肯定砍不死了，所以 restTimes 从 1 开始
                dp[restHp][restTimes] = dp[restHp - 1][restTimes] + dp[restHp][restTimes - 1];
                // 当 restHp > m，也就是说二维数组的点能满足 dp[restHp - 1][restTimes] 的累加和，我们相当于多加了一个 dp[restHp - m - 1][restTimes - 1]
                if (restHp > m) {
                    dp[restHp][restTimes] -= dp[restHp - m - 1][restTimes - 1];
                } else {
                    //当 restHp <= m，也就是说二维数组的点不能满足 dp[restHp - 1][restTimes] 的累加和时，我们就相当于多加了一个 Math.pow(m + 1, restTimes - 1);
                    dp[restHp][restTimes] -= Math.pow(m + 1, restTimes - 1);
                }
            }
        }
        double all = Math.pow(m + 1, k);
        return dp[n][k] / all;
    }

}
