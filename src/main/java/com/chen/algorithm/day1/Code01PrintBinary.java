package com.chen.algorithm.day1;

/**
 * code01打印二进制
 * 对一个数做 - 操作，如：5-> -5，其实是对二进制码进行 ~ + 1
 * @author chenzihan
 * @date 2022/05/05
 */
public class Code01PrintBinary {
    /**
     * 打印一个int值的二进制码
     *
     * @param num 任意 int 值
     */
    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : 1);
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int a = 123;
        System.out.println(a);
        print(a);
        System.out.println(-a);
        print(-a);
        System.out.println(~a +1 );
        print(~a + 1);
        print(Integer.MIN_VALUE);
    }
}
