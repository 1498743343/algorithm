package com.chen.algorithm.day30;


/**
 * code02
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：
 * sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 *
 * @author chenzihan
 * @date 2022/09/26
 */
public class Code02AllLessNumSubArray {

	// 暴力的对数器方法
//	public static int right(int[] arr, int num) {
//		if(arr == null || arr.length == 0 || )
//	}
//
//	public static int num(int[] arr, int num) {
//
//	}

	// for test
	public static int[] generateRandomArray(int maxLen, int maxValue) {
		int len = (int) (Math.random() * (maxLen + 1));
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr != null) {
            for (int j : arr) {
                System.out.print(j + " ");
            }
			System.out.println();
		}
	}

	public static void main(String[] args) {
//		int maxLen = 100;
//		int maxValue = 200;
//		int testTime = 100000;
//		System.out.println("测试开始");
//		for (int i = 0; i < testTime; i++) {
//			int[] arr = generateRandomArray(maxLen, maxValue);
//			int num = (int) (Math.random() * (maxValue + 1));
//			int ans1 = right(arr, num);
//			int ans2 = num(arr, num);
//			if (ans1 != ans2) {
//				System.out.println("Oops!");
//				printArray(arr);
//				System.out.println(num);
//				System.out.println(ans1);
//				System.out.println(ans2);
//				break;
//			}
//		}
//		System.out.println("测试结束");

	}

}
