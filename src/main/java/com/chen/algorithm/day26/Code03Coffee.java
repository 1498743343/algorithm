package com.chen.algorithm.day26;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * code03咖啡
 * 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
 * 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
 * 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
 * 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
 * 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
 * 四个参数：arr, n, a, b
 * 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
 *
 * @author chenzihan
 * @date 2022/08/30
 */

public class Code03Coffee {

    // 验证的方法
    // 彻底的暴力
    // 很慢但是绝对正确
    public static int right(int[] arr, int n, int a, int b) {
        int[] times = new int[arr.length];
        int[] drink = new int[n];
        return forceMake(arr, times, 0, drink, n, a, b);
    }

    // 每个人暴力尝试用每一个咖啡机给自己做咖啡
    public static int forceMake(int[] arr, int[] times, int kth, int[] drink, int n, int a, int b) {
        if (kth == n) {
            int[] drinkSorted = Arrays.copyOf(drink, kth);
            Arrays.sort(drinkSorted);
            return forceWash(drinkSorted, a, b, 0, 0, 0);
        }
        int time = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int work = arr[i];
            int pre = times[i];
            drink[kth] = pre + work;
            times[i] = pre + work;
            time = Math.min(time, forceMake(arr, times, kth + 1, drink, n, a, b));
            drink[kth] = 0;
            times[i] = pre;
        }
        return time;
    }

    public static int forceWash(int[] drinks, int a, int b, int index, int washLine, int time) {
        if (index == drinks.length) {
            return time;
        }
        // 选择一：当前index号咖啡杯，选择用洗咖啡机刷干净
        int wash = Math.max(drinks[index], washLine) + a;
        int ans1 = forceWash(drinks, a, b, index + 1, wash, Math.max(wash, time));

        // 选择二：当前index号咖啡杯，选择自然挥发
        int dry = drinks[index] + b;
        int ans2 = forceWash(drinks, a, b, index + 1, washLine, Math.max(dry, time));
        return Math.min(ans1, ans2);
    }

    // for test
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        System.out.print("arr : ");
        for (int i : arr) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 10;
        int max = 10;
        int testTime = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = (int) (Math.random() * 7) + 1;
            int a = (int) (Math.random() * 7) + 1;
            int b = (int) (Math.random() * 10) + 1;
            int ans1 = right(arr, n, a, b);
            int ans2 = recursion(arr, n, a, b);
            int ans3 = dp(arr, n, a, b);
            if (ans1 != ans2 || ans2 != ans3) {
                printArray(arr);
                System.out.println("n : " + n);
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println(ans1 + " , " + ans2 + " , " + ans3);
                System.out.println("===============");
                break;
            }
        }
        System.out.println("测试结束");

    }

    static class MakeInfo {
        public int startTime;
        public int makeTime;

        public MakeInfo(int startTime, int makeTime) {
            this.startTime = startTime;
            this.makeTime = makeTime;
        }
    }

    /**
     * 递归：
     * 这个题目可以分为两步：
     * 1.首先找到可以最快制作完这些咖啡的排序方式
     * 2.因为喝咖啡不需要时间，所以制作完的时间就是可以进行 clean cup 的时间，再找到可以最快让杯子都干净的方法即可
     *
     * @param arr 咖啡机
     * @param n   n个人
     * @param a   机器洗杯子需要的时间
     * @param b   挥发干净需要的时间
     * @return int
     */
    private static int recursion(int[] arr, int n, int a, int b) {
        // 存储每个杯子可以开始清理时间的数组
        int[] drink = new int[n];
        PriorityQueue<MakeInfo> heap = new PriorityQueue<>(Comparator.comparingInt(info -> (info.startTime + info.makeTime)));
        // 初始化每个咖啡机的状态，每个咖啡机都是在 0 时间点可用，做一杯需要耗时 makeTime
        for (int makeTime : arr) {
            heap.add(new MakeInfo(0, makeTime));
        }
        // 为 n 个人做咖啡，通过堆排找到最优解
        for (int i = 0; i < n; i++) {
            MakeInfo makeInfo = heap.poll();
            makeInfo.startTime += makeInfo.makeTime;
            drink[i] = makeInfo.startTime;
            heap.add(makeInfo);
        }
        return process(drink, a, b, 0, 0);
    }

    /**
     * 递归过程：
     * 对于任意一个杯子，我都可以进行洗或者让它自己挥发，所以基础的选择就两个
     * 当到 index 来到 drink.length 的时候，说明所有杯子都已经遍历过了
     *
     * @param drink    每个杯子可以进行清理的时间数组
     * @param a        洗一个杯子需要的时间
     * @param b        自己挥发需要的时间
     * @param freeTime 咖啡机空闲时间
     * @param index    当前来到哪个杯子
     * @return int
     */
    private static int process(int[] drink, int a, int b, int freeTime, int index) {
        //base case
        if (index == drink.length) {
            return 0;
        }
        // 当前杯子洗
        int washTime = Math.max(freeTime, drink[index]) + a;
        int restTime = process(drink, a, b, washTime, index + 1);
        int p1 = Math.max(washTime, restTime);
        // 当前杯子挥发
        int airTime = drink[index] + b;
        int restTime2 = process(drink, a, b, freeTime, index + 1);
        int p2 = Math.max(airTime, restTime2);
        return Math.min(p1, p2);
    }

    /**
     * 动态规划：
     * 由暴力递归方法可知，结果只和 freeTime、index 两个变量有关
     *
     * @param arr 咖啡机
     * @param n   n个人
     * @param a   机器洗杯子需要的时间
     * @param b   挥发干净需要的时间
     * @return int
     */
    private static int dp(int[] arr, int n, int a, int b) {
        int[] drink = new int[n];
        PriorityQueue<MakeInfo> heap = new PriorityQueue<>(Comparator.comparingInt(info -> info.makeTime + info.startTime));
        for (int makeTime : arr) {
            heap.add(new MakeInfo(0, makeTime));
        }
        for (int i = 0; i < n; i++) {
            MakeInfo info = heap.poll();
            info.startTime += info.makeTime;
            drink[i] = info.startTime;
            heap.add(info);
        }
        // freeTime 不是一个很好估计范围的变量，但是我们可以根据业务去拿到它的最大值
        int maxFreeTime = 0;
        for (int time : drink) {
            maxFreeTime = Math.max(time, maxFreeTime) + a;
        }
        int[][] dp = new int[n + 1][maxFreeTime + 1];
        //base case：当 index == n时，返回 0，即 dp[n][i] = 0，所以不用改动
        //因为当前值依赖于 下一行的值，所以倒序遍历
        for (int index = n - 1; index >= 0; index--) {
            for (int freeTime = 0; freeTime <= maxFreeTime; freeTime++) {
                // freeTime <= maxFreeTime，所以 freeTime + a 可能越界
                int washTime = Math.max(freeTime, drink[index]) + a;
                if (washTime > maxFreeTime) {
                    break;
                }
                int restTime = dp[index + 1][washTime];
                int p1 = Math.max(washTime, restTime);
                // 当前杯子挥发
                int airTime = drink[index] + b;
                int restTime2 = dp[index + 1][freeTime];
                int p2 = Math.max(airTime, restTime2);
                dp[index][freeTime] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }

}
