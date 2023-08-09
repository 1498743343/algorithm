package com.chen.algorithm.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapTest {
    public static void main(String[] args) {
        int maxLength = 10;
        int maxValue = 20;
        int tryTimes = 1000000;
        for (int j = 0; j < tryTimes; j++) {
            int[] arr1 = getArray(maxLength, maxValue);
            int[] arr2 = Arrays.copyOf(arr1, arr1.length);
            int n = arr1.length;
            for (int i = n - 1; i >= 0; i--) {
                heapify(arr1, i);
            }
            PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(num -> (int) num).reversed());
            for (int num : arr2) {
                heap.add(num);
            }
            int extraStudents = 200;
            for (int i = 0; i < extraStudents; i++) {
                arr1[0]++;
                heapify(arr1, 0);
                int n1 = arr1[0];
                int poll = heap.poll();
                heap.add(++poll);
                int n2 = heap.peek();
                if (n1 != n2) {
                    System.out.println("出错了");
                    System.out.println("n1 = " + n1);
                    System.out.println("n2 = " + n2);
                    break;
                }
            }
        }
    }

    public static void heapify(int[] arr, int index) {
        int n = arr.length;
        int next = 2 * index + 1;
        while (next < n) {
            if (next + 1 < n && arr[next] < arr[next + 1]) {
                next++;
            }
            if (arr[index] >= arr[next]) {
                break;
            }
            swap(arr, index, next);
            index = next;
            next = index * 2 + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int[] getArray(int maxLength, int maxValue) {
        int length = (int) (Math.random() * maxLength) + 1;
        int[] ans = new int[length];
        for (int i = 0; i < length; i++) {
            ans[i] = (int) (Math.random() * (maxValue + 1));
        }
        return ans;
    }
}
