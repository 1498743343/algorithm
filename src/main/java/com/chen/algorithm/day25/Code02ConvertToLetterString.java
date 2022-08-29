package com.chen.algorithm.day25;

/**
 * code02 字符串转换
 * 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为: "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 *
 * @author chenzihan
 * @date 2022/08/29
 */
public class Code02ConvertToLetterString {
    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int length = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * length);
            String s = randomString(len);
            int ans0 = number(s);
            int ans1 = dp(s);
            if (ans0 != ans1) {
                System.out.println(s);
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

    /**
     * 从右往左的动态规划：由 number 方法可知，结果只和 index 有关
     * 对于一个任意点，他的可能性和当前位置以及下一个位置有关
     * dp[i] 表示在 str 的 i~length-1范围上可以转换的可能性有多少种
     *
     * @param str str
     * @return int
     */
    private static int dp(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int length = str.length();
        char[] chars = str.toCharArray();
        int[] dp = new int[length + 1];
        dp[length] = 1;
        for (int i = str.length() - 1; i >= 0; i--) {
            char cur = chars[i];
            if (cur != '0') {
                int ways = dp[i + 1];
                // 如果我和下一个字符组合起来 <= 26 说明有两种情况
                if (i + 1 < chars.length && (chars[i] - '0') * 10 + (chars[i + 1] - '0') <= 26) {
                    ways += dp[i + 2];
                }
                dp[i] = ways;
            }
        }
        return dp[0];
    }

    private static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        return process(chars, 0);
    }

    /**
     * 对于 str 的一个位置，验证是否可以进行正常转换
     *
     * @param chars 字符数组
     * @param index 下标
     * @return int
     */
    private static int process(char[] chars, int index) {
        // base case：当下标越界时，说明遍历完成，而且都合规，这条路线正确，返回 1
        if (index == chars.length) {
            return 1;
        }
        char cur = chars[index];
        // base case：如果当前位置的字符是 0，说明是一个错误的路线，返回 0
        if (cur == '0') {
            return 0;
        }
        int ways = process(chars, index + 1);
        // 如果我和下一个字符组合起来 <= 26 说明有两种情况
        if (index + 1 < chars.length && (chars[index] - '0') * 10 + (chars[index + 1] - '0') <= 26) {
            ways += process(chars, index + 2);
        }
        return ways;
    }

}
