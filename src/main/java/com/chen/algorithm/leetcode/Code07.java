package com.chen.algorithm.leetcode;

/**
 * code07 leetcode 第7题
 * <a href="https://leetcode.cn/problems/reverse-integer/submissions/">测试链接</a>
 *
 * @author chenzihan
 * @date 2022/09/07
 */
public class Code07 {

    /**
     * 翻转整数我开始是用链表来转换的，没有问题，但是看到了这个解答，真的太牛逼了
     * 我们让 x 每次对 10 取模，这样就可以获得最后一位的数，然后把他加到 ans 上，然后让 x/10，这样每次就可以变换一位
     * 当 x == 0时，说明最后一位已经转换完了，退出循环
     *
     * @param x x
     * @return int
     */
    public int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            int tmp = x % 10;
            if (ans < Integer.MIN_VALUE / 10 || ans > Integer.MAX_VALUE / 10) {
                return 0;
            }
            ans = ans * 10 + tmp;
            x /= 10;
        }
        return ans;
    }

}
