package com.chen.algorithm.day8;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * code03
 * 给定数组 arr，一个数出现了 k 次，其他数出现了 m 次，找出出现了 k 次的数，要求空间复杂度为 O(1)
 *
 * @author chenzihan
 * @date 2022/05/25
 */
public class Code03KTimesMTimes {

    public static void main(String[] args) {
        int maxValue = 100;
        // m 可以取到的最大值
        int range = 10;
        // 数组中的数的种类的最大值
        int maxKinds = 9;
        int tryTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < tryTimes; i++) {
            int a = (int) (Math.random() * range + 1);
            int b = (int) (Math.random() * range + 1);
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            if (k == m) {
                m++;
            }
            int[] arr = getRandomArray(maxValue, maxKinds, k, m);
            int num1 = test(arr, k, m);
            int num2 = getKTimesNum(arr, k, m);
            if (num1 != num2) {
                System.out.println("出错了");
                System.out.println("num1 = " + num1);
                System.out.println("num2 = " + num2);
                break;
            }
        }
        System.out.println("测试结束");
    }

    /**
     * 找到数组中出现了 k 次的 num
     *
     * @param arr 数组
     * @param k   k
     * @param m   m
     * @return int
     */
    private static int getKTimesNum(int[] arr, int k, int m) {
        int[] bitArray = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                if ((num & (1 << i)) != 0) {
                    bitArray[i]++;
                }
            }
        }
        int kNum = 0;
        for (int i = 0; i < 32; i++) {
            if (bitArray[i] % m == k) {
                kNum |= (1 << i);
            }
        }
        return kNum;
    }

    private static int test(int[] arr, int k, int m) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int result = -1000000;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(k)) {
                return entry.getKey();
            }
        }
        return result;
    }


    /**
     * 得到随机阵列
     * 得到随机数组
     * 要求：value 区间为 [-maxValue,maxValue]，一个数有 k 个，其它数都有 m 个，并且保证 1 <= k < m，数组中共有 [2,kinds] 种数
     * @param maxValue 最大值
     * @param maxKinds 数组中最多有 maxKinds 种数
     * @param k        k
     * @param m        m
     * @return {@link int[]}
     */
    private static int[] getRandomArray(int maxValue, int maxKinds, int k, int m) {
        // 初始化出现了 k 次的数
        int kNum = getRandomNum(maxValue);
        // 数组中共包含 kinds 种数
        int kinds = (int) (Math.random() * (maxKinds - 2) + 2);
        // 数组长度
        int length = k + (kinds - 1) * m;
        int[] arr = new int[length];
        // 在数组中添加 kNum
        for (int i = 0; i < k; i++) {
            arr[i] = kNum;
        }
        int mKind = kinds - 1;
        int index = k;
        Set<Integer> set = new HashSet<>();
        // 先把 kNum 添加进去，否则有可能造成 kNum == randomNum 的情况，导致出错
        set.add(kNum);
        while (mKind > 0) {
            int randomNum = getRandomNum(maxValue);
            // set 中包含 randomNum 时，重新获取 randomNum
            while (set.contains(randomNum)) {
                randomNum = getRandomNum(maxValue);
            }
            set.add(randomNum);
            // 将 randomNum 添加到 arr 中
            for (int i = 0; i < m; i++) {
                arr[index++] = randomNum;
            }
            mKind--;
        }
        for (int i = 0; i < arr.length; i++) {
            int a = (int) (Math.random() * length);
            int b = (int) (Math.random() * length);
            swap(arr, a, b);
        }
        return arr;

    }

    /**
     * 交换 arr 中 a 位置和 b 位置的数
     *
     * @param arr 数组
     * @param a   a -> index
     * @param b   b -> index
     */
    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;

    }

    /**
     * 得到随机num
     * value 区间为 [-maxValue,maxValue]
     *
     * @param maxValue 最大价值
     * @return int
     */
    private static int getRandomNum(int maxValue) {
        return (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
    }

}
