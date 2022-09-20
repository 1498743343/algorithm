package com.chen.algorithm.leetcode;

import java.util.List;

/**
 * code139 leetcode 第139题
 * <a href="https://leetcode.cn/problems/word-break/">测试链接</a>
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 * @author chenzihan
 * @date 2022/09/20
 */
public class Code139 {

    /**
     * 单词分割
     *
     * @param s        字符串
     * @param wordDict 字典
     * @return boolean
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;
        for (int start = n - 1; start >= 0; start--) {
            for (int end = n; end > start; end--) {
                // 只有 end 位置为 true，并且 start~end 这个区间组成的字符串也在字典内，才能把 start 位置标记为 true
                if (dp[end] && wordDict.contains(s.substring(start, end))) {
                    dp[start] = true;
                    break;
                }
            }
        }
        return dp[0];
    }
}
