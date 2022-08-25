package com.chen.algorithm.day23;

import java.util.ArrayList;
import java.util.List;

/**
 * code03打印字符串全排列
 *
 * @author chenzihan
 * @date 2022/08/25
 */
public class Code03PrintAllPermutations {

	public static void main(String[] args) {
		String s = "aba";
		List<String> ans1 = permutation1(s);
		for (String str : ans1) {
			System.out.println(str);
		}
		System.out.println("=======");
		List<String> ans2 = permutation2(s);
		for (String str : ans2) {
			System.out.println(str);
		}
	}


	private static List<String> permutation1(String str) {
		char[] chars = str.toCharArray();
		List<String> ans = new ArrayList<>();
		process1(chars, ans,0);
		return ans;
	}

	private static void process1(char[] chars, List<String> ans, int index) {
		if (index == chars.length) {
			ans.add(String.valueOf(chars));
			return;
		}
		// 以index为起始位置，前面的已经确定好了，从index开始，index位置和后面的每个位置都进行交换，这样index的所有可以交换的场景就都覆盖到了
		for (int i = index; i < chars.length; i++) {
			swap(chars, i, index);
			process1(chars, ans, index + 1);
			// 第二次 swap 是为了还原现场，不给下一次循环带来影响
			swap(chars, i, index);
		}
	}

	private static List<String> permutation2(String str) {
		char[] chars = str.toCharArray();
		List<String> ans = new ArrayList<>();
		process2(chars, ans, 0);
		return ans;
	}

	/**
	 * 去重
	 *
	 * @param chars 字符数组
	 * @param ans   结果
	 * @param index 当前位置
	 */
	private static void process2(char[] chars, List<String> ans, int index) {
		if (index == chars.length) {
			ans.add(String.valueOf(chars));
			return;
		}
		// 注意 visited 的位置，在每次 for 循环的外面 new 的数组，这样可以保证在同一个循环中多次遇到同一个字符时跳过
		boolean[] visited = new boolean[256];
		for (int i = index; i < chars.length; i++) {
			if (!visited[chars[i]]) {
				visited[chars[i]] = true;
				swap(chars, i, index);
				process2(chars, ans, index + 1);
				swap(chars, i, index);
			}
		}
	}

	private static void swap(char[] chars, int index1, int index2) {
		char tmp = chars[index1];
		chars[index1] = chars[index2];
		chars[index2] = tmp;
	}
}
