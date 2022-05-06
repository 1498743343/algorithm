package com.chen.algorithm.day2;

/**
 * code01前缀数组
 * 给定一个数组，其中 l ~ r 位置经常求和，如果每次都进行累加，效率很低
 * 写出一个较好的方法
 *
 * @author chenzihan
 * @date 2022/05/06
 */
public class Code01PrefixArray {
    public static void main(String[] args) {
        int[] arr = {4, 6, 3, 4, 1, 7, 8};
        int length = arr.length;
        int l = 3;
        int r = 6;
//      借助辅助数组，但是使用一维数组，help[i] = arr[0] + arr[1] + ... + arr[i]，计算 l~r 的和时，取 help[r] - help[l-1]即可，如果l=0，直接取help[r]
        int[] help1 = new int[length];
        help1[0] = arr[0];
        for (int i = 1; i < length; i++) {
            help1[i] = help1[i - 1] + arr[i];
        }
        int result1 = help1[r] - help1[l-1];
        System.out.println(result1);
//        空间换时间：创建一个二维数组help，两个维度分别代表l和r的坐标，如 1~4，我们通过 help[l][r]就可以获取到对应的和
        int[][] help = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if (i == j) {
                    help[i][j] = arr[j];
                } else {
                    help[i][j] = help[i][j - 1] + arr[j];
                }
            }
        }
        int result2 = help[l][r];
        System.out.println(result2);
    }


}
