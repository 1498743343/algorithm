package com.chen.algorithm.leetcode;

/**
 * code136 leetcode 第136题
 * <a href="https://leetcode.cn/problems/single-number/?favorite=2cktkvj">测试链接</a>
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * @author chenzihan
 * @date 2022/09/20
 */
public class Code136 {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }
}
