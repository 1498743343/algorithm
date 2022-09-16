package com.chen.algorithm.leetcode;

/**
 * code72 leetcode 第72题
 * <a href="https://leetcode.cn/problems/edit-distance/?favorite=2cktkvj">测试链接</a>
 *
 * @author chenzihan
 * @date 2022/09/16
 */
public class Code72 {
    public static void main(String[] args) {
        String s1 = "intention";
        String s2 = "execution";
        int ans = minDistance(s1, s2);
        System.out.println(ans);
    }

    /**
     * 暴力递归
     *
     * @param word1 word1
     * @param word2 word2
     * @return int
     */
    public static int minDistance1(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        if (length1 == 0 || length2 == 0) {
            return length1 == 0 ? length2 : length1;
        }
        return process(word1, length1 - 1, word2, length2 - 1);
    }

    private static int process(String word1, int index1, String word2, int index2) {
        // base case: 当遍历到word1 或者 word2 的第一个字母时，就可以判断出具体还需要几步了
        // 如果一个单词遍 w1 历到 0 位置了，看看另一个单词 w2 的 0~index 位置是否包含 w1[0]，如果包含，那么操作步骤就要少一步
        if (index1 == 0) {
            return (index2 + (word2.substring(0, index2 + 1).contains(word1.substring(0, 1)) ? 0 : 1));
        }
        if (index2 == 0) {
            return (index1 + (word1.substring(0, index1 + 1).contains(word2.substring(0, 1)) ? 0 : 1));
        }
        // 当前两个位置的字符相等，都跳一步，去看前面的
        if (word1.charAt(index1) == word2.charAt(index2)) {
            return process(word1, index1 - 1, word2, index2 - 1);
        } else {
            // 替换，替换以后 word1[index1] 位置的字符肯定和 word2[index2] 相等，所以都往前跳一步
            int p1 = process(word1, index1 - 1, word2, index2 - 1) + 1;
            // 删除，删除以后，word1 要往自己的前面继续遍历，但是 word2 的 index 是不需要变动的
            int p2 = process(word1, index1 - 1, word2, index2) + 1;
            // 新增，新增以后，word1 的 index 是不会动的，因为新增的字符肯定和 word2[index2] 相等，所以是 word2 往前看，word1 不动
            int p3 = process(word1, index1, word2, index2 - 1) + 1;
            return Math.min(Math.min(p1, p2), p3);
        }
    }

    /**
     * dp：由暴力递归方法可知，对于任意位置的操作数只和两个变量有关， index1 和 index2，所以使用二维数组解决
     *
     * @param word1 word1
     * @param word2 word2
     * @return int
     */
    public static int minDistance(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        if (length1 == 0 || length2 == 0) {
            return length1 == 0 ? length2 : length1;
        }
        int[][] dp = new int[length1][length2];
        dp[0][0] = word1.charAt(0) == word2.charAt(0) ? 0 : 1;
        for (int x = 1; x < length1; x++) {
            // 当 word2 处于 0 位置时，看看 word1 到 x 位置有几个字符，如果 0~x 上有字符等于 word2[0]，那操作步数需要减一
            dp[x][0] = x + 1 - (word1.substring(0, x + 1).contains(word2.substring(0, 1)) ? 1 : 0);
        }
        for (int y = 1; y < length2; y++) {
            // 同理
            dp[0][y] = y + 1 - (word2.substring(0, y + 1).contains(word1.substring(0, 1)) ? 1 : 0);
        }
        for (int x = 1; x < length1; x++) {
            for (int y = 1; y < length2; y++) {
                int num;
                if (word1.charAt(x) == word2.charAt(y)) {
                    num = dp[x - 1][y - 1];
                } else {
                    int p1 = dp[x - 1][y - 1] + 1;
                    // 删除
                    int p2 = dp[x - 1][y] + 1;
                    // 新增
                    int p3 = dp[x][y - 1] + 1;
                    num = Math.min(Math.min(p1, p2), p3);
                }
                dp[x][y] = num;
            }
        }
        return dp[length1 - 1][length2 - 1];
    }
}
