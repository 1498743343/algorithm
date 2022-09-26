package com.chen.algorithm.day29;

/**
 * code01
 * 给定一个正数数组arr，
 * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回：最接近的情况下，较小集合的累加和
 *
 * @author chenzihan
 * @date 2022/09/26
 */
public class Code01SplitSumClosed {

    /**
     * 暴力方法
     *
     * @param arr 数组
     * @return int
     */
    public static int right(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return process(0, arr, sum / 2);
    }

    private static int process(int index, int[] arr, int target) {
        if (index == arr.length) {
            return 0;
        }
        int p1 = process(index + 1, arr, target);
        int p2 = 0;
        if (arr[index] <= target) {
            p2 = process(index + 1, arr, target - arr[index]);
        }
        return Math.max(p1, p2);
    }

    public static int dp(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum /= 2;
        int[][] dp = new int[arr.length + 1][sum + 1];
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j <= sum; j++) {
                int p1 = dp[i + 1][j];
                int p2 = 0;
                if (arr[i] <= j) {
                    p2 = dp[i + 1][j - arr[i]];
                }
                dp[i][j] = Math.max(p1, p2);
            }
        }
        return dp[0][sum];
    }

    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = right(arr);
            int ans2 = dp(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
