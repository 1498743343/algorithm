package com.chen.algorithm.day28;

/**
 * code01 杀死怪兽
 * 给定3个参数，N，M，K：怪兽有N滴血，等着英雄来砍自己，英雄每一次打击，都会让怪兽流失[0~M]的血量，每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 * 在这里我们求解怪兽被砍 k 次之后存活下来的方法数，在后一个类中去求解被砍死的概率
 *
 * @author chenzihan
 * @date 2022/09/02
 */
public class Code01KillMonster1 {

    public static void main(String[] args) {
        int nMax = 10;
        int mMax = 10;
        int kMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * nMax);
            int m = (int) (Math.random() * mMax);
            int k = (int) (Math.random() * kMax);
            long ans1 = right(n, m, k);
            long ans2 = dp1(n, m, k);
            long ans3 = dp2(n, m, k);
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
    }

    /**
     * 暴力递归
     *
     * @param n n 滴血
     * @param m 每次砍掉 [0~m] 滴血
     * @param k 砍 k 次
     * @return long
     */
    private static long right(int n, int m, int k) {
        // 校验参数，参数违规直接返回
        if (n <= 0 || m <= 0 || k <= 0) {
            return 0;
        }
        // 血量大于砍的上限，说明怎么也砍不死
        if (n > m * k) {
            return (long) Math.pow(m + 1, k);
        }
        return process(n, k, m);
    }

    private static long process(int restHp, int restTimes, int m) {
        // 能走到最后一步说明肯定没死，返回 1
        if (restTimes == 0) {
            return 1;
        }
        long ways = 0;
        // 把 restHp < 0 的情况直接在 for 循环的条件中限制死
        for (int i = 0; restHp - i > 0 && i <= m; i++) {
            ways += process(restHp - i, restTimes - 1, m);
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
    private static long dp1(int n, int m, int k) {
        if (n <= 0 || m <= 0 || k <= 0) {
            return 0;
        }
        // 血量大于砍的上限，说明怎么也砍不死
        if (n > m * k) {
            return (long) Math.pow(m + 1, k);
        }
        long[][] dp = new long[n + 1][k + 1];
        for (int restHp = 1; restHp <= n; restHp++) {
            dp[restHp][0] = 1;
        }
        for (int restHp = 1; restHp <= n; restHp++) {
            for (int restTimes = 1; restTimes <= k; restTimes++) {
                long ways = 0;
                // 把 restHp < 0 的情况直接在 for 循环的条件中限制死
                for (int i = 0; restHp - i > 0 && i <= m; i++) {
                    ways += dp[restHp - i][restTimes - 1];
                }
                dp[restHp][restTimes] = ways;
            }
        }
        return dp[n][k];
    }

    /**
     * 动态规划二：
     * 观察动态规划一可知，最里层的 for 循环存在枚举行为，通过观察点之间的依赖关系，看看能不能优化掉最里层的 for 循环
     *
     * @param n n 滴血
     * @param m 每次砍掉 [0~m] 滴血
     * @param k 砍 k 次
     * @return long
     */
    private static long dp2(int n, int m, int k) {
        if (n <= 0 || m <= 0 || k <= 0) {
            return 0;
        }
        // 血量大于砍的上限，说明怎么也砍不死
        if (n > m * k) {
            return (long) Math.pow(m + 1, k);
        }
        long[][] dp = new long[n + 1][k + 1];
        for (int restHp = 1; restHp <= n; restHp++) {
            dp[restHp][0] = 1;
        }
        for (int restHp = 1; restHp <= n; restHp++) {
            for (int restTimes = 1; restTimes <= k; restTimes++) {
                // 对于一个普遍位置的点：dp[restHp][restTimes] = dp[restHp][restTimes-1] + dp[restHp-1][restTimes-1] + ... + dp[restHp-m][restTimes-1]
                // 对于 dp[restHp - 1][restTimes] = dp[restHp -1][restTimes-1] + dp[restHp-1][restTimes-1] + ... + dp[restHp-m -1][restTimes-1]
                // 所以我们可以推导出 dp[restHp][restTimes] = dp[restHp - 1][restTimes] + dp[restHp][restTimes-1] - dp[restHp-m -1][restTimes-1]
                // 但是当 restHp-m-1<0 时，我们就不需要在 - dp[restHp-m -1][restTimes-1]了
                dp[restHp][restTimes] = dp[restHp - 1][restTimes] + dp[restHp][restTimes - 1];
                if (restHp - m - 1 > 0) {
                    dp[restHp][restTimes] -= dp[restHp - m - 1][restTimes - 1];
                }
            }
        }
        return dp[n][k];
    }

}
