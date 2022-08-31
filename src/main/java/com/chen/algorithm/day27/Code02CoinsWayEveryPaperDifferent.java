package com.chen.algorithm.day27;

/**
 * code02
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。每个值都认为是一张货币，即便是值相同的货币也认为每一张都是不同的
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 *
 * @author chenzihan
 * @date 2022/08/31
 */
public class Code02CoinsWayEveryPaperDifferent {

    public static int[] randomArray(int maxLen, int maxValue) {
        int n = (int) (Math.random() * maxLen);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinWays(arr, aim);
            int ans2 = dp(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }

    /**
     * 递归方法
     *
     * @param arr 加勒比海盗
     * @param aim 目
     * @return int
     */
    private static int coinWays(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int index, int rest) {
        // base case：rest < 0 时说明不是正确解，直接返回 0
        if (rest < 0) {
            return 0;
        }
        // base case：所有的纸币都遍历完，看rest来返回结果
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        // 一共有两种可能：要当前纸币；不要当前纸币
        // 要
        int p1 = process(arr, index + 1, rest - arr[index]);
        // 不要
        int p2 = process(arr, index + 1, rest);
        return p1 + p2;
    }

    /**
     * 动态规划
     * 由递归方法可知，结果和两个变量有关，index,rest
     *
     * @param arr 数组
     * @param aim 目标值
     * @return int
     */
    private static int dp(int[] arr, int aim) {
        int length = arr.length;
        int[][] dp = new int[length + 1][aim + 1];
        dp[length][0] = 1;
        for (int index = length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                if (rest - arr[index] >= 0) {
                    p2 = dp[index + 1][rest - arr[index]];
                }
                dp[index][rest] = p1 + p2;
            }
        }
        return dp[0][aim];
    }
}
