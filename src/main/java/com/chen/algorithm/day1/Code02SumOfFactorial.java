package com.chen.algorithm.day1;

/**
 * code02求 1~n 的阶乘相加：1! + 2! + 3! + ... + n!
 *
 * @author chenzihan
 * @date 2022/05/05
 */
public class Code02SumOfFactorial {
    /**
     * 每次都对所有的数进行阶乘操作，然后相加
     * 性能低，重复步骤多
     *
     * @param n n
     * @return long
     */
    public static long bad(int n) {
        long result = 0;
        for (int i = 1; i <= n; i++) {
            long s = 1;
            for (int j = 1; j <= i; j++) {
                s *= j;
            }
            result += s;
        }
        return result;
    }

    /**
     * 每次记录上次阶乘的结果，然后 *n 后加到 result 中
     * 性能高，避免了之前的重复阶乘
     *
     * @param n n
     * @return long
     */
    public static long good(int n) {
        long result = 0;
        int s = 1;
        for (int i = 1; i <= n; i++) {
            s *= i;
            result += s;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 4;
        long result = good(n);
        System.out.println("result = " + result);
    }
}
