package com.chen.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * code438 leetcode 第438题
 * <a href = "https://leetcode.cn/problems/find-all-anagrams-in-a-string/description/?envType=study-plan-v2&envId=top-100-liked">测试链接</a>
 *
 * @author chenzihan
 */
public class Code438 {
    /**
     * 找到字符串中所有字母异位词：这种解法的精妙之处在于可以让 right 去追 left，left 虽然可以比 right 靠前，但是 right 可以马上追上
     *
     * @param s 源字符串
     * @param p 目标字符串
     * @return 起始下标集合
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int[] arr = new int[26];
        for (char c : p.toCharArray()) {
            arr[c - 'a']++;
        }
        int count = p.length();
        char[] sc = s.toCharArray();
        int left = 0;
        int right = 0;
        while (right < sc.length) {
            if (arr[sc[right] - 'a'] > 0) {
                arr[sc[right++] - 'a']--;
            } else {
                arr[sc[left++] - 'a']++;
            }
            if (right - left == count) {
                ans.add(left);
            }
        }
        return ans;
    }
}
