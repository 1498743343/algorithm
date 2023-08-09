package com.chen.algorithm.leetcode;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Code406 {
    public static void main(String[] args) {
        //[[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
//        int[][] test = new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
//        Code406 c = new Code406();
//        int[][] ints = c.reconstructQueue(test);
        List<Integer> integers = new java.util.ArrayList<>(List.of(3, 2, 1));
        integers.remove(2);
        System.out.println(integers);
//        System.out.println(ints);
    }

    public int[][] reconstructQueue(int[][] people) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((arr1, arr2) -> {
            int num = arr1[1] - arr2[1];
            if (num == 0) {
                return arr1[0] - arr2[0];
            } else {
                return num;
            }
        });
        Collections.addAll(heap, people);
        int[][] ans = new int[heap.size()][2];
        ans[0] = heap.poll();
        int index = 1;
        while (!heap.isEmpty()) {
            ans[index] = heap.poll();
            checkAndSwap(ans, index);
            index++;
        }
        return ans;
    }

    public void checkAndSwap(int[][] arr, int index) {
        int[] info = arr[index];
        int count = 0;
        int i = 0;
        while (count <= info[1] && i <= index) {
            if (arr[i][0] >= info[0]) {
                count++;
            }
            i++;
        }
        swap(arr, i - 1, index);
        while (i < index) {
            swap(arr, i, index);
            i++;
        }
    }

    public void swap(int[][] arr, int i, int j) {
        int[] tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
