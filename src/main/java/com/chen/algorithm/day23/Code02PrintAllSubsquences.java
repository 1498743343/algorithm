package com.chen.algorithm.day23;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * code02 打印所有子序列
 * 子序列：可以不连续，但是相对次序不能乱
 *
 * @author chenzihan
 * @date 2022/08/24
 */
public class Code02PrintAllSubsquences {

	public static void main(String[] args) {
		String test = "aba";
		List<String> ans1 = subs(test);
		List<String> ans2 = subsNoRepeat(test);

		for (String str : ans1) {
			System.out.println(str);
		}
		System.out.println("=================");
		for (String str : ans2) {
			System.out.println(str);
		}
		System.out.println("=================");

	}

	/**
	 * 返回字符串的所有子字符串
	 *
	 * @param str str
	 * @return {@link List}<{@link String}>
	 */
	private static List<String> subs(String str) {
		char[] chars = str.toCharArray();
		List<String> ans = new ArrayList<>();
		process1(chars, ans, 0,"");
		return ans;
	}

	/**
	 * 之前的结果全保存在 path 中，当遍历到最后时，保存 path 然后返回
	 * 所有位置的字符都有两种结果，path 中有我，path 中没我
	 *
	 * @param chars 字符数组
	 * @param ans   结果
	 * @param index 当前位置
	 * @param path  之前保存的字符串
	 */
	private static void process1(char[] chars, List<String> ans, int index, String path) {
		if (index == chars.length) {
			ans.add(path);
			return;
		}
		process1(chars, ans, index + 1, path + chars[index]);
		process1(chars, ans, index + 1, path);
	}

	/**
	 * 返回字符串的去重后的所有子字符串
	 *
	 * @param str str
	 * @return {@link List}<{@link String}>
	 */
	private static List<String> subsNoRepeat(String str) {
		char[] chars = str.toCharArray();
		Set<String> set = new HashSet<>();
		process2(chars, set, 0, "");
		return new ArrayList<>(set);
	}

	private static void process2(char[] chars, Set<String> set, int index, String path) {
		if (index == chars.length) {
			set.add(path);
			return;
		}
		process2(chars, set, index + 1, path);
		process2(chars, set, index + 1, path + chars[index]);
	}

}
