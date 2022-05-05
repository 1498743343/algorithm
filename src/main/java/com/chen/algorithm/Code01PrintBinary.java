package com.chen.algorithm;

/**
 * code01打印二进制
 *
 * @author chenzihan
 * @date 2022/05/05
 */
public class Code01_PrintBinary {
    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : 1);
        }
    }
    public static void main(String[] args) {
        int a = Integer.MIN_VALUE;
        System.out.println(a);
        print(a);
        System.out.println(-a);
        print(-a);
    }
}
