package com.chen.algorithm.leetcode;

/**
 * code05 leetcode 第5题
 * <a href="https://leetcode.cn/problems/longest-palindromic-substring/">测试链接</a>
 *
 * @author chenzihan
 * @date 2022/09/07
 */
public class Code05 {

    /**
     * 最长回文
     * 观察可得，影响最终返回值的就两个值，left 和 right
     * 所以建立二维数组进行 dp
     *
     * @param s 字符串
     * @return {@link String}
     */
    public String longestPalindrome(String s) {
        if (s.length() == 0) {
            return s;
        }
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        // left 和 right 是一个值时，一定是回文
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        char[] arr = s.toCharArray();
        int maxLength = 1;
        int maxLeft = 0;
        // 因为 left == length - 1时，right 也只能是这个值，之前已经设置过为 true，所以从 left 的最大值是 length - 2
        // 分析各点的依赖关系可知，当 right - left <= 2时，只依赖于 arr[left] 是否等于 arr[right]，因为即使他俩中间有一个元素，也不影响他俩是不是回文结构
        // 当 right - left > 2 时，除了依赖 arr[left] 是否等于 arr[right]，还依赖 dp[left + 1][right - 1] 的值
        // 所以需要从下到上来填充 dp 数组
        for (int left = length - 2; left >= 0; left--) {
            for (int right = left + 1; right < length; right++) {
                dp[left][right] = arr[left] == arr[right] && (dp[left + 1][right - 1] || right - left <= 2);
                // 当我这个情况是回文结构，并且我的回文长度大于之前的长度才更新
                if (dp[left][right] && right - left + 1 > maxLength) {
                    maxLeft = left;
                    maxLength = right - left + 1;
                }
            }
        }
        return s.substring(maxLeft, maxLeft + maxLength);
    }
}
