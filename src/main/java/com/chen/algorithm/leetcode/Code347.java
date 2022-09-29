package com.chen.algorithm.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * code347 leetcode 第347题
 * <a href="https://leetcode.cn/problems/top-k-frequent-elements/?favorite=2cktkvj">测试链接</a>
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 *
 * @author chenzihan
 * @date 2022/09/29
 */
public class Code347 {
    public static void main(String[] args) {
        //[4,1,-1,2,-1,2,3]
        //[4,1,-1,2,-1,2,3]
        // [1,1,1,2,2,3]
//        int[] ans = topKFrequent(test, 2);
        for (int i = 0; i < 1000; i++) {
            int[] test = {4, 1, -1, 2, -1, 2, 3};
            int[] ans = topKFrequent1(test, 2);
            for (int num : ans) {
                System.out.print(num + " ");
            }
        }
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(Comparator.comparingInt(entry -> entry.getValue()));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            heap.add(entry);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        int[] ans = new int[k];
        int index = 0;
        while (!heap.isEmpty()) {
            ans[index++] = heap.poll().getKey();
        }
        return ans;
    }

    public static int[] topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int[][] arr = new int[map.size()][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            arr[index][0] = entry.getKey();
            arr[index++][1] = entry.getValue();
        }
        qsort(arr, 0, arr.length - 1, k);
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = arr[i][0];
        }
        return ans;
    }

    private static void qsort(int[][] arr, int left, int right, int k) {
        if (left >= right) {
            return;
        }
        int[] range = partation(arr, left, right);
        if (range[0] > k) {
            qsort(arr, left, range[0], k);
        } else if (range[1] < k) {
            qsort(arr, range[1], right, k);
        }
    }

    private static int[] partation(int[][] arr, int left, int right) {
        int rIndex = left + (int) (Math.random() * (right - left + 1));
        int num = arr[rIndex][1];
        int lp = left - 1;
        int rp = right + 1;
        int index = left;
        while (index < rp) {
            if (arr[index][1] > num) {
                swap(arr, ++lp, index++);
            } else if (arr[index][1] == num) {
                index++;
            } else {
                swap(arr, --rp, index);
            }
        }
        return new int[]{lp, rp};
    }

    private static void swap(int[][] arr, int i, int j) {
        int[] tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


}
