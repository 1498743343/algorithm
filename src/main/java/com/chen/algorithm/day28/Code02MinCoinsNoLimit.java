package com.chen.algorithm.day28;

/**
 * code02
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数
 *
 * @author chenzihan
 * @date 2022/09/02
 */
public class Code02MinCoinsNoLimit {

    public static int[] randomArray(int maxLen, int maxValue) {
        int n = (int) (Math.random() * maxLen);
        int[] arr = new int[n];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < n; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
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
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * maxLen);
            int[] arr = randomArray(n, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = minCoins(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println("aim = " + aim);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                System.out.println("ans3 = " + ans3);
                break;
            }
        }
        System.out.println("功能测试结束");
    }

    /**
     * 最小硬币
     *
     * @param arr 数组
     * @param aim 目标数
     * @return int
     */
    private static int minCoins(int[] arr, int aim) {
        return process(0, aim, arr);
    }

    /**
     * 递归过程
     *
     * @param index 当前位置
     * @param rest  剩余值
     * @param arr   数组
     * @return int
     */
    private static int process(int index, int rest, int[] arr) {
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int min = Integer.MAX_VALUE;
        for (int num = 0; arr[index] * num <= rest; num++) {
            int next = process(index + 1, rest - arr[index] * num, arr);
            if (next != Integer.MAX_VALUE) {
                min = Math.min(min, num + next);
            }
        }
        return min;
    }

    /**
     * 动态规划一：
     * 根据递归方法可知，影响结果的两个变量是 index,rest
     * 所以二维数组解决
     *
     * @param arr 数组
     * @param aim 目标数
     * @return int
     */
    private static int dp1(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        int length = arr.length;
        int[][] dp = new int[length + 1][aim + 1];
        dp[length][0] = 0;
        for (int rest = 1; rest <= aim; rest++) {
            dp[length][rest] = Integer.MAX_VALUE;
        }
        for (int index = length - 1; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                int min = Integer.MAX_VALUE;
                for (int num = 0; arr[index] * num <= rest; num++) {
                    int next = dp[index + 1][rest - arr[index] * num];
                    if (next != Integer.MAX_VALUE) {
                        min = Math.min(min, num + next);
                    }
                }
                dp[index][rest] = min;
            }
        }
        return dp[0][aim];
    }

    /**
     * 动态规划二：
     * 动态规划一存在枚举行为，根据点之间的位置关系干掉 for 循环
     *
     * @param arr 数组
     * @param aim 目标数
     * @return int
     */
    private static int dp2(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        int length = arr.length;
        int[][] dp = new int[length + 1][aim + 1];
        dp[length][0] = 0;
        for (int rest = 1; rest <= aim; rest++) {
            dp[length][rest] = Integer.MAX_VALUE;
        }
        for (int index = length - 1; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                // 由动态规划一可知,dp[index][rest]的值是 dp[index + 1][rest - arr[index] * num] 中的最小值
                // 所以当 rest < arr[index] 时，没有其他比较对象，只有一个dp[index + 1][rest]
                // 当 rest >= arr[index] 时，有多个值可以比较
                // 但是我们又知道 dp[index][rest-arr[index]]的值是 dp[index + 1][rest - arr[index] * (num+1)] 中的最小值
                // 所以当 dp[index][rest-arr[index]] 是一个有效值时，我们直接拿 dp[index][rest-arr[index]]+1 和 dp[index + 1][rest]比较赋值即可
                dp[index][rest] = dp[index + 1][rest];
                if (rest >= arr[index] && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index + 1][rest], dp[index][rest - arr[index]] + 1);
                }
            }
        }
        return dp[0][aim];
    }
}
