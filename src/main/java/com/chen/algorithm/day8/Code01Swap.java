package com.chen.algorithm.day8;

/**
 * code01 不额外申请变量，将 a 和 b 的值交换（a != b）
 *
 * @author chenzihan
 * @date 2022/05/25
 */
public class Code01Swap {

    /**
     * 注意：交换成功的前提是 arr[i] != arr[j]，因为 a ^ a = 0
     *
     * @param arr 加勒比海盗
     * @param i   我
     * @param j   j
     */
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a = " + a);
        System.out.println("b = " + b);

        int[] arr = new int[5];
        for (int i = 0; i < 5; i++) {
            arr[i] = i;
        }
        swap(arr, 1, 3);
        for (int i = 0; i < 5; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
