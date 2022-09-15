package com.chen.algorithm.leetcode;

import java.util.*;

/**
 * code49 leetcode 第49题
 * <a href="https://leetcode.cn/problems/group-anagrams/">测试链接</a>
 * 这道题没有特别简单的方法，我们必须去判断两个字符串的所有字符是不是都相等，比较合理的一个方法是让他们都按照字符排序，然后去判断是不是同一个字符串
 *
 * @author chenzihan
 * @date 2022/09/15
 */
public class Code49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String s = String.valueOf(chars);
            if (map.containsKey(s)) {
                map.get(s).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(s, list);
            }
        }
        return new ArrayList<>(map.values());
    }
}
