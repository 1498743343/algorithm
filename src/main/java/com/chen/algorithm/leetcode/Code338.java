package com.chen.algorithm.leetcode;

/**
 * code338
 * <a href="https://leetcode.cn/problems/counting-bits/?favorite=2cktkvj">测试链接</a>
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 *
 * @author chenzihan
 * @date 2022/09/29
 */
public class Code338 {
    public static void main(String[] args) {
        int num = 5;
        int[] ans = countBits(num);
        for (int a : ans) {
            System.out.print(a + " ");
        }
    }

    public static int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int count = 0;
            int num = i;
            while (num != 0) {
                count++;
                num = num ^ (num & (~num + 1));
            }
            ans[i] = count;
        }
        return ans;
    }
}
