package com.chen.algorithm.day8;

/**
 * code02 奇偶次
 *
 * @author chenzihan
 * @date 2022/05/25
 */
public class Code02EvenTimesOddTimes {

    /**
     * 给定数组中，a 出现了奇数次，其他数出现了偶数次，找出 a 并打印
     *
     * @param arr 加勒比海盗
     */
    public static void printOddTimesNum(int[] arr) {
        int result = 0;
        for (int num : arr) {
            result ^= num;
        }
        System.out.println("result = " + result);
    }

    /**
     * 给定数组中，a 和 b 都出现了奇数次，其他数出现了偶数次，找出 a 和 b 并打印（a != b）
     *
     * @param arr 加勒比海盗
     */
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int num : arr) {
            eor ^= num;
        }
        // 经过了整个数组的异或操作以后，eor = a ^ b，此时将 eor 最右侧的 1 取出来
        int rightOne = eor & (~eor + 1);
        // 将 arr 中的数分为两类，一类是 num & rightOne == 0 的，一类是 num & rightOne != 0 的，取一类进行异或操作，得到的就是 a，a ^ eor 的结果就是 b
        int a = 0;
        for (int num : arr) {
            if ((num & rightOne) == 0) {
                a ^= num;
            }
        }
        System.out.println("a = " + a);
        System.out.println("b = " + (a ^ eor));
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 1, 3, 3, 3, 3, 2, 2, 5, 5, 6, 6, 6};
        printOddTimesNum(arr1);
        int[] arr2 = {1, 1, 3, 3, 3, 3, 2, 2, 5, 5, 6, 6, 6, 77};
        printOddTimesNum2(arr2);
    }
}
