package com.chen.algorithm.day20;

import java.util.PriorityQueue;

/**
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。
 * 比如长度为20的金条，不管怎么切，都要花费20个铜板。 一群人想整分整块金条，怎么分最省铜板?
 * 输入一个数组，返回分割的最小代价
 *
 * @author chenzihan
 * @date 2022/08/08
 */
public class Code02LessMoneySplitGold {

	/**
	 * 暴力递归
	 *
	 * @param arr 加勒比海盗
	 * @return int
	 */
	public static int lessMoney1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		return process(arr, 0);
	}

	/**
	 * 等待合并的数都在arr里，pre之前的合并行为产生了多少总代价
	 * arr中只剩一个数字的时候，停止合并，返回最小的总代价
	 *
	 * @param arr 加勒比海盗
	 * @param pre 精准医疗
	 * @return int
	 */
	public static int process(int[] arr, int pre) {
		if (arr.length == 1) {
			return pre;
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
			}
		}
		return ans;
	}

	public static int[] copyAndMergeTwo(int[] arr, int i, int j) {
		int[] ans = new int[arr.length - 1];
		int ansi = 0;
		for (int arri = 0; arri < arr.length; arri++) {
			if (arri != i && arri != j) {
				ans[ansi++] = arr[arri];
			}
		}
		ans[ansi] = arr[i] + arr[j];
		return ans;
	}

	/**
	 * 将所有元素添加到堆中，每次弹出两个，然后将两个数相加后累加到 ans 上，再添加到堆中，直到只剩一个，返回 ans
	 *
	 * @param arr 加勒比海盗
	 * @return int
	 */
	public static int lessMoney2(int[] arr) {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int num : arr) {
			queue.add(num);
		}
		int ans = 0;
		while (queue.size() >= 2) {
			int cur = queue.poll() + queue.poll();
			ans += cur;
			queue.add(cur);
		}
		return ans;
	}

	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * (maxValue + 1));
		}
		return arr;
	}

	public static void main(String[] args) {
		int testTime = 100000;
		int maxSize = 6;
		int maxValue = 1000;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			if (lessMoney1(arr) != lessMoney2(arr)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
