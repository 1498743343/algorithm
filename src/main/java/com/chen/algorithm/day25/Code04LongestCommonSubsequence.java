package com.chen.algorithm.day25;

/**
 * code04 最长公共子序列
 * <a href="https://leetcode.cn/problems/longest-common-subsequence/">测试链接</a>
 *
 * @author chenzihan
 * @date 2022/08/29
 */
public class Code04LongestCommonSubsequence {

    /**
     * 暴力递归：根据经验，一般从结尾开始对可能的情况进行讨论
     *
     * @param s1 s1
     * @param s2 s2
     * @return int
     */
    public static int longestCommonSubsequence1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        return process(c1, c2, c1.length - 1, c2.length - 1);
    }

    private static int process(char[] c1, char[] c2, int i, int j) {
        // 分情况讨论，一共就4种情况：
        // 1. i 和 j 都等于0
        // 2. i == 0, j != 0
        // 3. i != 0, j == 0
        // 4. i 和 j 都不等于0
        if (i == j && i == 0) {
            return c1[i] == c2[j] ? 1 : 0;
        } else if (i == 0) {
            if (c1[i] == c2[j]) {
                return 1;
            } else {
                // 如果c1[i] != c2[j]，i不能再减了，j继续往前看
                return process(c1, c2, i, j - 1);
            }
        } else if (j == 0) {
            if (c1[i] == c2[j]) {
                return 1;
            } else {
                // 如果c1[i] != c2[j]，j不能再减了，i继续往前看
                return process(c1, c2, i - 1, j);
            }
        } else {
            int subLength;
            // 当 i != 0 && j != 0 时，无非就两种情况，c1[i] == c2[j] 和 c1[i] != c2[j]
            if (c1[i] == c2[j]) {
                subLength = process(c1, c2, i - 1, j - 1) + 1;
            } else {
                // 当 c1[i] != c2[j] 时，需要进行尝试，看看哪个以哪个尝试获得的结果更大就返回哪个
                int p1 = process(c1, c2, i - 1, j);
                int p2 = process(c1, c2, i, j - 1);
                subLength = Math.max(p1, p2);
            }
            return subLength;
        }
    }


    /**
     * 动态规划：由暴力递归方法可以知道，最终结果只和 i 和 j 有关，所以建立 i 和 j 的二维数组即可
     *
     * @param s1 s1
     * @param s2 s2
     * @return int
     */
    public static int longestCommonSubsequence2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int length1 = c1.length;
        int length2 = c2.length;
        int[][] dp = new int[length1][length2];
        // base case
        dp[0][0] = c1[0] == c2[0] ? 1 : 0;
        // base case
        for (int i = 1; i < length2; i++) {
            dp[0][i] = c1[0] == c2[i] ? 1 : dp[0][i - 1];
        }
        // base case
        for (int i = 1; i < length1; i++) {
            dp[i][0] = c1[i] == c2[0] ? 1 : dp[i - 1][0];
        }
        // 对于下面讨论的任意一个点，它的值和三个点有关系，左边的点、上面的点、左上方的点
        for (int i = 1; i < length1; i++) {
            for (int j = 1; j < length2; j++) {
                int max;
                if (c1[i] == c2[j]) {
                    max = 1 + dp[i - 1][j - 1];
                } else {
                    max = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                dp[i][j] = max;
            }
        }
        return dp[length1 - 1][length2 - 1];
    }


}
