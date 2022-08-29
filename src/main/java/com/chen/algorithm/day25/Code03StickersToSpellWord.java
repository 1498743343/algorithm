package com.chen.algorithm.day25;

import java.util.HashMap;
import java.util.Map;

/**
 * code03 贴纸拼写单词
 * 我们有 n 种不同的贴纸。每个贴纸上都有一个小写的英文单词。
 * 您想要拼写出给定的字符串 target ，方法是从收集的贴纸中切割单个字母并重新排列它们。如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。
 * 返回你需要拼出 target 的最小贴纸数量。如果任务不可能，则返回 -1 。
 * 注意：在所有的测试用例中，所有的单词都是从 1000 个最常见的美国英语单词中随机选择的，并且 target 被选择为两个随机单词的连接。
 * <a href="https://leetcode.cn/problems/stickers-to-spell-word">测试链接</a>
 * 输入： stickers = ["with","example","science"], target = "thehat"
 * 输出：3
 *
 * @author chenzihan
 * @date 2022/08/29
 */
public class Code03StickersToSpellWord {
    public static void main(String[] args) {
        String[] strings = new String[]{"with", "example", "science"};
        String target = "thehat";
        int i = minStickers3(strings, target);
        System.out.println(i);
    }

    /**
     * 暴力递归
     *
     * @param stickers 贴纸
     * @param target   目标
     * @return int
     */
    public static int minStickers1(String[] stickers, String target) {
        int ans = process1(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process1(String[] stickers, String target) {
        // base case：减完时，说明找到了正确的方案，但此时不再需要贴纸，所以返回 0
        if (target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String str : stickers) {
            String rest = minus(str, target);
            // 把符合题意的用纸张最少的纸张量返回
            if (rest.length() != target.length()) {
                min = Math.min(min, process1(stickers, rest));
            }
        }
        // 可能都不满足，所以根据情况返回
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    private static String minus(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int[] count = new int[26];
        for (char c : c2) {
            count[c - 'a']++;
        }
        for (char c : c1) {
            count[c - 'a']--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                sb.append(String.valueOf((char) (i + 'a')).repeat(count[i]));
            }
        }
        return sb.toString();
    }

    /**
     * 暴力递归 + 剪支
     * 将字符串的字符减法，改为字符出现频次的减法
     *
     * @param stickers 贴纸
     * @param target   目标
     * @return int
     */
    public static int minStickers2(String[] stickers, String target) {
        int length = stickers.length;
        int[][] count = new int[length][26];
        for (int i = 0; i < length; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char a : chars) {
                count[i][a - 'a']++;
            }
        }
        int ans = process2(count, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process2(int[][] counts, String rest) {
        if (rest.length() == 0) {
            return 0;
        }
        char[] target = rest.toCharArray();
        int[] tCount = new int[26];
        for (char a : target) {
            tCount[a - 'a']++;
        }
        int min = Integer.MAX_VALUE;
        for (int[] count : counts) {
            // 只有当前贴纸包含 rest 的第一个字符才进行下一步操作，剪支
            if (count[target[0] - 'a'] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 26; i++) {
                    if (tCount[i] > 0) {
                        int num = tCount[i] - count[i];
                        if (num > 0) {
                            sb.append(String.valueOf((char) ('a' + i)).repeat(num));
                        }
                    }
                }
                min = Math.min(min, process2(counts, sb.toString()));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    /**
     * 动态规划：由于这个题的变量是字符串，所以有非常多的情况，我们没有办法去穷举或者穷举起来非常的费劲，此时就可以使用 map 来做动态规划的表进行缓存
     *
     * @param stickers 贴纸
     * @param target   目标
     * @return int
     */
    public static int minStickers3(String[] stickers, String target) {
        int length = stickers.length;
        int[][] count = new int[length][26];
        for (int i = 0; i < length; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char a : chars) {
                count[i][a - 'a']++;
            }
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("", 0);
        int ans = process3(count, target, map);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process3(int[][] counts, String rest, Map<String, Integer> map) {
        if (map.containsKey(rest)) {
            return map.get(rest);
        }
        char[] target = rest.toCharArray();
        int[] tCount = new int[26];
        for (char a : target) {
            tCount[a - 'a']++;
        }
        int min = Integer.MAX_VALUE;
        for (int[] count : counts) {
            if (count[target[0] - 'a'] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 26; i++) {
                    int num = tCount[i] - count[i];
                    if (num > 0) {
                        sb.append(String.valueOf((char) (i + 'a')).repeat(num));
                    }
                }
                min = Math.min(min, process3(counts, sb.toString(), map));
            }
        }
        min += min == Integer.MAX_VALUE ? 0 : 1;
        map.put(rest, min);
        return min;
    }

}
