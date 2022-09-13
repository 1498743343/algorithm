package com.chen.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * code17 leetcode 第17题
 * <a href="https://leetcode.cn/problems/letter-combinations-of-a-phone-number/">测试链接</a>
 * 思路就是递归遍历所有的字符，虽然前后提交过几个版本，但优化的都是常数时间，时间复杂度不变
 *
 * @author chenzihan
 * @date 2022/09/13
 */
public class Code17 {
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return ans;
        }
        String[] cache = new String[]{
                "",
                "",
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqrs",
                "tuv",
                "wxyz"
        };
        process(0, new StringBuilder(), digits, cache, ans);
        return ans;
    }

    private void process(int index, StringBuilder preStr, String digits, String[] cache, List<String> ans) {
        if (index == digits.length()) {
            ans.add(preStr.toString());
            return;
        }
        int curNum = Integer.parseInt(String.valueOf(digits.charAt(index)));
        String curStr = cache[curNum];
        for (int i = 0; i < curStr.length(); i++) {
            process(index + 1, preStr.append(curStr.charAt(i)), digits, cache, ans);
            preStr.deleteCharAt(index);
        }
    }
}
