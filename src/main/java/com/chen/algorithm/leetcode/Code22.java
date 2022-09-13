package com.chen.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * code22 leetcode 第22题
 * <a href="https://leetcode.cn/problems/generate-parentheses/">测试链接</a>
 * 递归 + 剪支
 *
 * @author chenzihan
 * @date 2022/09/13
 */
public class Code22 {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        process(0, 0, "", ans, n);
        return ans;
    }


    /**
     * 过程
     *
     * @param left  当前左括号的数量
     * @param right 当前右括号的数量
     * @param path  路径
     * @param ans   结果
     * @param n     n对括号
     */
    public void process(int left, int right, String path, List<String> ans, int n) {
        // base case: 当左右括号都已经到达 n 个时，说明结果正确，添加到 ans 中
        if (left == n && right == n) {
            ans.add(path);
            return;
        }
        // 当 left < n 时，可以继续无脑添加 (
        if (left < n) {
            process(left + 1, right, path + "(", ans, n);
        }
        // 当 right < left 时，可以无脑添加 )
        // 但是当 right >= left 时，就不能再添加 ) 了，比如此时 path = ()，添加 right 后就会变成 ())，这样就不满足条件了
        // 所以在这里做了剪支
        if (right < left) {
            process(left, right + 1, path + ")", ans, n);
        }
    }
}
