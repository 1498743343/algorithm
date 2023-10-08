package com.chen.algorithm.leetcode;

/**
 * code03 leetcode 第3题
 * <a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/">测试链接</a>
 *
 * @author chenzihan
 */

public class Code03 {
    /**
     * 滑动窗口解决问题：最大字串指的是连续的字符串
     * @param s 字符串
     * @return 无重复字符的最大字串长度
     */
    public int lengthOfLongestSubstring(String s) {
        char[] sc = s.toCharArray();
        int[] count = new int[128];
        int left = 0;
        int right = 0;
        int n = sc.length;
        int max = 0;
        while(right < n) {
            if(count[sc[right]] == 0) {
                max = Math.max(max, right - left + 1);
                count[sc[right++]]++;
            } else {
                count[sc[left++]]--;
            }
        }
        return max;
    }
}
