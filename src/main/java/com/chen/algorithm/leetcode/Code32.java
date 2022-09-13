package com.chen.algorithm.leetcode;

import java.util.Stack;

/**
 * code32 leetcode 第32题
 * <a href="https://leetcode.cn/problems/longest-valid-parentheses/">测试链接</a>
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 *
 * @author chenzihan
 * @date 2022/09/13
 */
public class Code32 {

    /**
     * 动态规划：从 0 位置开始，以 i 位置结束，遍历一边，求得最大值
     * 通过分析可知，如果 i 位置是 (，那么 dp[i] == 0，因为不能组成一个符合题意的答案，所以不需要更新 dp 和 max
     * 如果 i 位置是 )，那么有两种情况：
     * 1. i-1 是 (，此时 dp[i] 的值依赖于 dp[i-2] 的值
     * 2. i-1 是 )，此时就要根据情况分析
     * 2.1 如果 i > dp[i] 并且 s.charAt(i - dp[i - 1] - 1) == '('，那么从 i-dp[i-1]-1 ~ i 位置上就是一个满足情况的字符串
     * 2.2 满足 2.1 以后，如果 i - dp[i-1] - 2 >= 0，也就是说 i - dp[i - 1] - 1 位置前面还存在字符，我们还要去看一下 dp[i - dp[i - 1] - 2] 的值
     *
     * @param s 年代
     * @return int
     */
    public static int longestValidParentheses1(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        int length = s.length();
        int max = 0;
        int[] dp = new int[length];
        for (int i = 1; i < length; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else {
                    if (i > dp[i - 1] && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0);
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 栈：栈是解决括号问题非常好用的手段
     * 我们将 ( 位置压栈，每当遇到一个 ) 时，就从栈中弹出一个元素，这样，满足条件的括号位置都会被消除掉
     * 栈中剩余的就是不符合情况的起始位置，而我们在遍历的过程中可以拿到当前位置，这样 i - stack.peek() 就是当前段满足条件的长度
     *
     * @param s 年代
     * @return int
     */
    public static int longestValidParentheses2(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        // 如果 s 以 ) 开头，那么直接 pop 就会报错，所以先压栈一个值
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    // 如果栈弹空了，前面的要么都符合规则，弹完了，要么是前面剩下的 )，不管怎么样，新的一轮判断从当前位置开始，所以将 i 压栈
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

}
