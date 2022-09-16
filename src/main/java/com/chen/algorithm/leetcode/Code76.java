package com.chen.algorithm.leetcode;

import java.util.*;

/**
 * code76 leetcode 第76题
 * <a href="https://leetcode.cn/problems/minimum-window-substring/?favorite=2cktkvj">测试链接</a>
 *
 * @author chenzihan
 * @date 2022/09/16
 */
public class Code76 {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String ans = minWindow(s, t);
        String ans1 = minWindow1(s, t);
        System.out.println("ans = " + ans);
        System.out.println("ans1 = " + ans1);
    }

    /**
     * 缓存 + 双指针：主要思路就是从左到右遍历 s，然后记录双指针区间内是否包含 t 中所有的字符了
     * 然后根据条件去更新 left 、right、ans
     *
     * @param s 年代
     * @param t t
     * @return {@link String}
     */
    public static String minWindow(String s, String t) {
        // 存储 s 的字符和个数的 map
        Map<Character, Integer> ms = new HashMap<>();
        // 存储 t 的字符和个数的 map
        Map<Character, Integer> mt = new HashMap<>();
        for (char a : t.toCharArray()) {
            mt.put(a, mt.getOrDefault(a, 0) + 1);
        }
        String ans = "";
        int count = 0;
        int length = Integer.MAX_VALUE;
        // 从左到右遍历 s
        for (int left = 0, right = 0; right < s.length(); right++) {
            char cLeft = s.charAt(left);
            char cRight = s.charAt(right);
            // 先把当前位置的字符放入 ms 中
            ms.put(cRight, ms.getOrDefault(cRight, 0) + 1);
            // 更新有效长度
            if (mt.containsKey(cRight) && ms.get(cRight) <= mt.get(cRight)) {
                count++;
            }
            // left右移条件：
            // 1. left < right
            // 2. t 中不包含 cLeft 这个字符，或者 s 在[left,right] 区间上包含的 cLeft 字符数已经超过了需要的个数
            while (left < right && (!mt.containsKey(cLeft) || ms.get(cLeft) > mt.getOrDefault(cLeft, 0))) {
                // 更新 ms 中 cLeft 的个数
                ms.put(cLeft, ms.get(cLeft) - 1);
                // 更新 cLeft
                cLeft = s.charAt(++left);
            }
            // 是否可以更新 ans：当[left,right]区间上包含的有效长度等于 t 的长度时，并且当前区间长度小于之前的长度，才可以更新 ans
            if (count == t.length() && right - left + 1 < length) {
                length = right - left + 1;
                ans = s.substring(left, right + 1);
            }
        }
        return ans;
    }

    /**
     * 暴力递归：用暴力递归 + 傻缓存的方式试过了，也通不过
     * 转换为 dp 的难点在于，path 不是一个好确定的变量，即使将 [left,right] 区间锁定， path值也不是唯一个的。
     * 如果 path 是 int 类型可以建立三维数组来解，但是这里并不行，这道题必须使用 两个 int + 一个 String 才能确定解法
     *
     * @param s 年代
     * @param t t
     * @return {@link String}
     */
    public static String minWindow1(String s, String t) {
        int[] arr = process(0, 0, t, s, t);
        String ans = "";
        if (arr[1] - arr[0] != Integer.MAX_VALUE) {
            ans = s.substring(arr[0], arr[1]);
        }
        return ans;
    }

    private static int[] process(int left, int index, String path, String s, String t) {
        // base case：如果当前的 path 已经被减没了，直接记录结果并返回
        if ("".equals(path)) {
            return new int[]{left, index};
        }
        // base case：如果当前的 path 还有字符，但是已经遍历到最后了，说明不是一个有效解
        if (index == s.length()) {
            return new int[]{0, Integer.MAX_VALUE};
        }
        // 一般情况：对于任意位置的一个字符，如果 path 中没有包含，肯定是不要，如果包含了，我可以选择要也可以选择不要
        // path 不包含当前位置的字符
        String curStr = s.substring(index, index + 1);
        if (!path.contains(curStr)) {
            return process(left, index + 1, path, s, t);
        } else {
            // path 包含当前位置的字符
            // 不要
            int[] p2 = process(left, index + 1, path, s, t);
            // 要
            if (t.equals(path)) {
                left = index;
            }
            int[] p1 = process(left, index + 1, path.replaceFirst(curStr, ""), s, t);
            int l1 = p1[1] - p1[0];
            int l2 = p2[1] - p2[0];
            return l1 > l2 ? p2 : p1;
        }
    }
}
