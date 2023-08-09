package com.chen.algorithm.leetcode;

public class Code215 {
    public static void main(String[] args) {
        // [3,2,1,5,6,4]
        // [2, 4, 4, 2, 4, 8, 9, 9, 10, 5]
        //2
        for (int i = 0; i < 1000; i++) {
            int[] test = new int[]{2, 4, 4, 2, 4, 8, 9, 9, 10, 5};
            int ans = findKthLargest(test, 5);
            if (ans == -2147483648) {
                System.out.println(ans);
            }
        }
    }

    public static int findKthLargest(int[] nums, int k) {
        return process(nums, 0, nums.length - 1, nums.length - k);
    }

    public static int process(int[] nums, int left, int right, int k) {
        int[] location = partation(nums, left, right);
        if (location[0] >= k) {
            return process(nums, left, location[0], k);
        } else if (location[1] <= k) {
            return process(nums, location[1], right, k);
        } else {
            return nums[k];
        }
    }

    public static int[] partation(int[] nums, int left, int right) {
        int random = left + (int) (Math.random() * (right - left + 1));
        int cur = nums[random];
        int lp = left - 1;
        int rp = right + 1;
        int index = left;
        while (index < rp) {
            if (nums[index] < cur) {
                swap(nums, ++lp, index++);
            } else if (nums[index] == cur) {
                index++;
            } else {
                swap(nums, --rp, index);
            }
        }
        return new int[]{lp, rp};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
