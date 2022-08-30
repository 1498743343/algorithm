package com.chen.algorithm.day26;


/**
 * code01 求解给定字符串的最长回文子序列长度
 * 两种解法：1.正面分析	2.将给定的字符串逆序，求解字符串和逆序字符串的最长公共子序列(day25中最后一题)
 *
 * <a href="https://leetcode.cn/problems/longest-palindromic-subsequence/">测试链接</a>
 *
 * @author chenzihan
 * @date 2022/08/29
 */
public class Code01PalindromeSubsequence {

    public static void main(String[] args) {
        String test = "abab";
        System.out.println(longestPalindromeSubseq1(test));
        System.out.println(longestPalindromeSubseq2(test));
    }

    /**
     * 方法一：
     * 逆序字符串，动态规划求解两个字符串的最长公共子序列
     *
     * @param s 字符串
     * @return int
     */
    public static int longestPalindromeSubseq1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        char[] c1 = s.toCharArray();
        char[] c2 = reverse(c1);
        int[][] dp = new int[length][length];
        dp[0][0] = c1[0] == c2[0] ? 1 : 0;
        for (int i = 1; i < length; i++) {
            dp[0][i] = c1[0] == c2[i] ? 1 : dp[0][i - 1];
        }
        for (int i = 1; i < length; i++) {
            dp[i][0] = c1[i] == c2[0] ? 1 : dp[i - 1][0];
        }

        for (int i = 1; i < length; i++) {
            for (int j = 1; j < length; j++) {
                int max;
                if (c1[i] == c2[j]) {
                    max = dp[i - 1][j - 1] + 1;
                } else {
                    int p1 = dp[i - 1][j];
                    int p2 = dp[i][j - 1];
                    max = Math.max(p1, p2);
                }
                dp[i][j] = max;
            }
        }
        return dp[length - 1][length - 1];
    }

    /**
     * 逆序字符串，递归求解两个字符串的最长公共子序列
     *
     * @param s 字符串
     * @return int
     */
    public static int reverseRecursion(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        char[] c1 = s.toCharArray();
        char[] c2 = reverse(c1);
        return subSequence(c1, c2, length - 1, length - 1);
    }

    private static int subSequence(char[] c1, char[] c2, int i, int j) {
        if (i == j && i == 0) {
            return c1[i] == c2[j] ? 1 : 0;
        }
        if (i == 0) {
            if (c1[i] == c2[j]) {
                return 1;
            } else {
                return subSequence(c1, c2, i, j - 1);
            }
        } else if (j == 0) {
            if (c1[i] == c2[j]) {
                return 1;
            } else {
                return subSequence(c1, c2, i - 1, j);
            }
        } else {
            if (c1[i] == c2[j]) {
                return subSequence(c1, c2, i - 1, j - 1) + 1;
            } else {
                int p1 = subSequence(c1, c2, i - 1, j);
                int p2 = subSequence(c1, c2, i, j - 1);
                return Math.max(p1, p2);
            }
        }
    }

    private static char[] reverse(char[] chars) {
        int length = chars.length;
        char[] ans = new char[length];
        for (char aChar : chars) {
            ans[--length] = aChar;
        }
        return ans;
    }


    /**
     * 方法二：
     * 正面分析，暴力递归求解
     *
     * @param s 字符串
     * @return int
     */
    public static int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        return process(chars, 0, s.length() - 1);
    }

    /**
     * 递归：求解 chars 在 left ~ right 范围上的最长回文字符串长度
     *
     * @param chars 字符数组
     * @param left  左
     * @param right 右
     * @return int
     */
    private static int process(char[] chars, int left, int right) {
        // base case 当 left == right 时，说明是同一个字符，返回1
        if (left == right) {
            return 1;
        } else if (left == right - 1) {
            // base case 当 left 和 right 相差 1 时，相等返回2，不等返回1
            return chars[left] == chars[right] ? 2 : 1;
        } else {
            // 当 left 和 right 相差 1 以上时，分情况讨论
            // 1. 不以 left 开头也不以 right 结尾，但是通过函数我们必然知道，情况2和情况3都包括了情况1，也就是说 p2,p3 >= p1，所以 p1 可以去掉
            int p1 = process(chars, left + 1, right - 1);
            // 2. 不以 left 开头，以 right 结尾
            int p2 = process(chars, left + 1, right);
            // 3. 以 left 开头，不以 right 结尾
            int p3 = process(chars, left, right - 1);
            // 4. 以 left 开头，并且以 right 结尾
            int p4 = (chars[left] == chars[right] ? 2 : 0) + process(chars, left + 1, right - 1);
            return Math.max(Math.max(p1, p2), Math.max(p3, p4));
        }
    }

    /**
     * 方法三：
     * 动态规划正面求解，根据方法二我们可以看到影响最终结果的只有两个值，left 和 right
     * 对于一个点 (left,right)，和三个点的位置有关系(left+1,right),(left,right-1),(left+1,right-1)
     * 综上用二维数组可以表示所有情况
     *
     * @param s 年代
     * @return int
     */
    public static int longestPalindromeSubseq3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int length = chars.length;
        int[][] dp = new int[length][length];
        // 填充 base case
        dp[length - 1][length - 1] = 1;
        for (int i = 0; i < length - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = chars[i] == chars[i + 1] ? 2 : 1;
        }
        // 根据已经填好的 base case 补充其他节点的值
        for (int left = length - 3; left >= 0; left--) {
            for (int right = left + 2; right < length; right++) {
                int p1 = dp[left + 1][right];
                int p2 = dp[left][right - 1];
                int max = Math.max(p1, p2);
                if (chars[left] == chars[right]) {
                    max = Math.max(max, (dp[left + 1][right - 1] + 2));
                }
                dp[left][right] = max;
            }
        }
        return dp[0][length - 1];
    }

}
