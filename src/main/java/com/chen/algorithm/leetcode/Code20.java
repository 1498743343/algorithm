package com.chen.algorithm.leetcode;

import java.util.Stack;

/**
 * code20 leetcode 第20题
 * <a href="https://leetcode.cn/problems/valid-parentheses/">测试链接</a>
 * 使用栈来判断括号的对称性
 *
 * @author chenzihan
 * @date 2022/09/13
 */
public class Code20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char a : s.toCharArray()) {
            if (a == '{') {
                stack.push('}');
            } else if (a == '(') {
                stack.push(')');
            } else if (a == '[') {
                stack.push(']');
            } else {
                if (stack.isEmpty() || a != stack.pop()) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
