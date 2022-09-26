package com.chen.algorithm.day29;

/**
 * code02
 * 给定一个正数数组arr，请把arr中所有的数分成两个集合
 * 如果arr长度为偶数，两个集合包含数的个数要一样多
 * 如果arr长度为奇数，两个集合包含数的个数必须只差一个
 * 请尽量让两个集合的累加和接近
 * 返回：
 * 最接近的情况下，较小集合的累加和
 *
 * @author chenzihan
 * @date 2022/09/26
 */
public class Code02SplitSumClosedSizeHalf {

    public static int right(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum /= 2;
        if (arr.length % 2 == 0) {
            return process(0, sum, arr.length / 2, arr);
        } else {
            // 因为 sum /= 2，已经限制了 sum 的最大值不会超过 sum 的一半，所以此处取最大值
            return Math.max(process(0, sum, arr.length / 2, arr), process(0, sum, arr.length / 2 + 1, arr));
        }

    }

    private static int process(int index, int target, int leftSize, int[] arr) {
        if (index == arr.length) {
            return leftSize == 0 ? 0 : -1;
        }
        int p1 = process(index + 1, target, leftSize, arr);
        int p2 = -1;
        if (arr[index] <= target && leftSize > 0) {
            int next = process(index + 1, target - arr[index], leftSize - 1, arr);
            if (next != -1) {
                p2 = next + arr[index];
            }
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
        int leftSize = (arr.length + 1) / 2;
        int[][][] dp = new int[arr.length + 1][sum + 1][leftSize + 1];
        for (int target = 0; target <= sum; target++) {
            for (int size = 1; size <= leftSize; size++) {
                dp[arr.length][target][size] = -1;
            }
        }
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int target = 0; target <= sum; target++) {
                for (int size = 0; size <= leftSize; size++) {
                    int p1 = dp[index + 1][target][size];
                    int p2 = -1;
                    if (arr[index] <= target && size > 0) {
                        int next = dp[index + 1][target - arr[index]][size - 1];
                        if (next != -1) {
                            p2 = next + arr[index];
                        }
                    }
                    dp[index][target][size] = Math.max(p1, p2);
                }
            }
        }
        if (arr.length % 2 == 0) {
            return dp[0][sum][arr.length / 2];
        } else {
            return Math.max(dp[0][sum][arr.length / 2], dp[0][sum][arr.length / 2 + 1]);
        }
    }

    // for test
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int maxLen = 10;
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