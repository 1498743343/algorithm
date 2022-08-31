package com.chen.algorithm.day27;

/**
 * code03
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的方法数
 * 例如：arr = {1,2}，aim = 4
 * 方法如下：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 *
 * @author chenzihan
 * @date 2022/08/31
 */
public class Code03CoinsWayNoLimit {

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
        int maxLen = 10;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinsWay(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int i = 0; arr[index] * i <= rest; i++) {
            ways += process(arr, index + 1, rest - arr[index] * i);
        }
        return ways;
    }

    private static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int length = arr.length;
        int[][] dp = new int[length + 1][aim + 1];
        dp[length][0] = 1;
        for (int index = length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                // 保证不重复，让 index 不回头，保证每种货币只会看一遍
                for (int i = 0; arr[index] * i <= rest; i++) {
                    ways += dp[index + 1][rest - arr[index] * i];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int length = arr.length;
        int[][] dp = new int[length + 1][aim + 1];
        dp[length][0] = 1;
        for (int index = length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                // 通过 dp1 中的依赖关系可以看到，(index,rest)位置的值依赖于(index+1,rest)和rest-arr[index]*i>0时(index+1,rest-arr[index]*i)各个位置上的值
                // 但是必有一个值满足 rest-arr[index]*(i+1) < 0 <= rest-arr[index]*i，此时的位置满足 dp[index][rest-arr[index]*i] == dp[index+1][rest-arr[index]*i]，因为没有累加值了
                // 继续往后推导我们可以知道 dp[index][rest] = dp[index][rest-arr[index]] + arr[index]
                // 总结为下面代码
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

}
