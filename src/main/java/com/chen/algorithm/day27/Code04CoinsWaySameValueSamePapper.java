package com.chen.algorithm.day27;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * code04
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。每个值都认为是一张货币，认为值相同的货币没有任何不同，
 * 返回组成aim的方法数
 * 例如：arr = {1,2,1,1,2,1,2}，aim = 4
 * 方法：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 * 解题思路：因为相同值的货币认为是同一种，没有任何区别，所以我们不妨把货币根据值分成不同的种类，然后根据给定的 arr 数组统计货币的种类和每种货币的个数
 * 最后根据转换的信息来求解
 *
 * @author chenzihan
 * @date 2022/08/31
 */
public class Code04CoinsWaySameValueSamePapper {
    static class Info {
        public int[] coins;
        public int[] nums;

        public Info(int[] coins, int[] nums) {
            this.coins = coins;
            this.nums = nums;
        }
    }

    public static Info getInfo(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int[] coins = new int[map.size()];
        int[] nums = new int[map.size()];
        int i = 0;
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            coins[i] = entry.getKey();
            nums[i] = entry.getValue();
            i++;
        }
        return new Info(coins, nums);
    }

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
        int maxLen = 10;
        int maxValue = 20;
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

    /**
     * 暴力递归
     *
     * @param arr 加勒比海盗
     * @param aim 目
     * @return int
     */
    private static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] nums = info.nums;
        return process(coins, nums, 0, aim);
    }

    private static int process(int[] coins, int[] nums, int index, int rest) {
        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        // 当前货币的总值 <= rest 并且使用当前货币的数量 <= 当前货币的数量
        for (int i = 0; coins[index] * i <= rest && i <= nums[index]; i++) {
            ways += process(coins, nums, index + 1, rest - coins[index] * i);
        }
        return ways;
    }

    /**
     * 动态规划一：完全根据递归函数改编
     * 由递归函数可知，和两个变量有关：index,rest
     *
     * @param arr 加勒比海盗
     * @param aim 目
     * @return int
     */
    private static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] nums = info.nums;
        int length = coins.length;
        int[][] dp = new int[length + 1][aim + 1];
        dp[length][0] = 1;
        for (int index = length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int i = 0; coins[index] * i <= rest && i <= nums[index]; i++) {
                    ways += dp[index + 1][rest - coins[index] * i];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    /**
     * 动态规划二：根据动态规划一方法改编
     * 由动态规划一方法我们可以知道在满足条件的情况下 dp[index][rest] = dp[index + 1][rest - coins[index] * i]，而当 i == 0时， dp[index][rest] == dp[index + 1][rest]
     * 所以分情况讨论:
     * 1. i == 0 时，dp[index][rest] = dp[index + 1][rest]
     * 2. 1 < i <= nums[index] && coins[index] * (nums[index] + 1) > rest时，这个条件的意思是 coins[index] <= rest < coins[index] * (nums[index] + 1)
     * 也就是说 (index,rest) 位置前最起码能满足有一个点 (index,rest-coins[index])，并且 (index,rest-coins[index]) 这个点不能满足前面包括所有当前货币都使用的情况
     * 此时：dp[index][rest] = dp[index][rest - coins[index]] + dp[index + 1][rest]
     * 3. coins[index] * nums[index] < rest时，这个条件的意思是 (index,rest-coins[index]) 可以包含所有当前货币都使用的情况，
     * 此时如果我们再使用条件二的累加处理就会多加一个点的值，这个点是 (index + 1,rest - coins[index] * (nums[index] + 1))，减去即可
     * 所以，dp[index][rest] = dp[index][rest - coins[index]] + dp[index + 1][rest] - dp[index + 1][rest - coins[index] * (nums[index] + 1)]
     *
     * @param arr 加勒比海盗
     * @param aim 目
     * @return int
     */
    private static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] nums = info.nums;
        int length = coins.length;
        int[][] dp = new int[length + 1][aim + 1];
        dp[length][0] = 1;
        for (int index = length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = dp[index + 1][rest];
                if (rest >= coins[index]) {
                    if (coins[index] * (nums[index] + 1) > rest) {
                        ways += dp[index][rest - coins[index]];
                    } else {
                        ways += dp[index][rest - coins[index]] - dp[index + 1][rest - coins[index] * (nums[index] + 1)];
                    }
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

}
