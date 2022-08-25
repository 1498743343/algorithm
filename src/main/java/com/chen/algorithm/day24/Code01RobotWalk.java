package com.chen.algorithm.day24;

/**
 * code01 机器人行走
 * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 *
 * @author chenzihan
 * @date 2022/08/25
 */
public class Code01RobotWalk {

    public static void main(String[] args) {
        System.out.println(ways1(2, 3, 5, 5));
        System.out.println(ways2(2, 3, 5, 5));
        System.out.println(ways3(2, 3, 5, 5));
    }

    /**
     * 方法一：暴力递归
     *
     * @param cur    当前位置
     * @param target 目标位置
     * @param n      范围：1~n
     * @param k      步数
     * @return int
     */
    private static int ways1(int cur, int target, int n, int k) {
        return process1(cur, target, n, k);
    }

    private static int process1(int cur, int target, int n, int k) {
        // 当步数为 0 的时候，说明走完了，如果走到了目标位置，返回 1，否则返回 0
        if (k == 0) {
            return cur == target ? 1 : 0;
        }
        // 当 cur == 1 时，只能往右走
        if (cur == 1) {
            return process1(2, target, n, k - 1);
        }
        // 当 cur == n 时，只能往左走
        if (cur == n) {
            return process1(n - 1, target, n, k - 1);
        }
        // 当 1 < cur < n 时，左右都可以走
        return process1(cur - 1, target, n, k - 1) + process1(cur + 1, target, n, k - 1);
    }

    /**
     * 方法二：暴力缓存
     * 方法一中，有很多重复的计算步骤没有缓存下来，我们对之前计算过的结果进行缓存就可以优化
     * 由方法一种的递归调用我们可以看到，target 和 n 是始终没有变化的，所以当 targer 和 n 确定以后，结果只和 cur、k 有关
     * 所以我们建立关于 cur 和 k 的缓存
     *
     * @param cur    当前位置
     * @param target 目标位置
     * @param n      范围：1~n
     * @param k      步数
     * @return int
     */
    private static int ways2(int cur, int target, int n, int k) {
        int[][] cache = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                cache[i][j] = -1;
            }
        }
        return process2(cur, target, n, k, cache);
    }

    private static int process2(int cur, int target, int n, int k, int[][] cache) {
        // 命中缓存就直接返回
        if (cache[cur][k] != -1) {
            return cache[cur][k];
        }
        // 没命中，计算过后缓存
        int ans;
        if (k == 0) {
            ans = cur == target ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(2, target, n, k - 1, cache);
        } else if (cur == n) {
            ans = process2(n - 1, target, n, k - 1, cache);
        } else {
            ans = process2(cur - 1, target, n, k - 1, cache) + process2(cur + 1, target, n, k - 1, cache);
        }
        cache[cur][k] = ans;
        return ans;
    }

    /**
     * 方法三：动态规划
     * 通过方法二我们可以发现，除了 base case 一共有三种情况
     * 1. cur == 1时，结果依赖于 (2,k-1)
     * 2. cur == n时，结果依赖于 (n-1,k-1)
     * 3. 1 < cur < n时，结果依赖于 (cur - 1, k - 1) 和 (cur + 1, k - 1)
     * 综上，我们可以根据 base case 建立所有情况的图
     * 我们以 target == 3, n == 5举例，见下图
     *
     * @param cur    当前位置
     * @param target 目标位置
     * @param n      范围：1~n
     * @param k      步数
     * @return int
     * @return int
     */
    private static int ways3(int cur, int target, int n, int k) {
        int[][] dp = new int[n + 1][n + 1];
        dp[target][0] = 1;
        for (int i = 1; i <= k; i++) {
            dp[1][i] =
        }
    }

}
