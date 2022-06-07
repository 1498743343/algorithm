package com.chen.algorithm.day11;


/**
 * code03分区2
 * 对数组进行一次遍历，将数组分为三块，左侧为 < num 的部分，中间 = num，右侧为 > num 的部分
 *
 * @author chenzihan
 * @date 2022/06/07
 */
public class Code03Partition2 {
    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        int length = 200;
        int value = 100;
        int tryTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < tryTime; i++) {
            int[] array = generateRandomArray(length, value);
            int num;
            if (array.length == 0) {
                continue;
            } else {
                num = array[(int) (Math.random() * array.length)];
            }
            int[] result = partition(array, num);
            if (!test(array, num, result)) {
                System.out.println(num);
                for (int j : result) {
                    System.out.print(j + " ");
                }
                System.out.println();
                for (int k : array) {
                    System.out.print(k + " ");
                }
                System.out.println();
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

    /**
     * 分区
     * 主要的思路是维护一个左侧的分界线、一个右侧的分界线和一个指针，左侧分界线内的值 <= num，index负责遍历数组
     *
     * @param array 数组
     * @param num   指定值
     * @return int
     */
    private static int[] partition(int[] array, int num) {
        int left = -1;
        int right = array.length;
        int index = 0;
        // 当 index == right 时，代表已经遍历完了
        while (index < right) {
            if (array[index] < num) {
                // 当前值 < num 时，将 array[left + 1] 位置的元素和 array[index] 位置的元素交换，然后 index++；result++
                swap(array, index++, ++left);
            } else if (array[index] > num) {
                // 当前值 > num 时，将 array[index] 与 array[right - 1] 的元素交换，然后 right--
                swap(array, index, --right);
            } else {
                // 当前值 == num 时，index++
                index++;
            }
        }
        // 因为 left 表示的是 < num 的下标，right 表示的是 > num 的下标，所以 [left + 1,right - 1] 才是 num 正确的区间
        return new int[]{left + 1, right - 1};
    }

    private static boolean test(int[] array, int num, int[] scope) {
        int left = scope[0];
        int right = scope[1];
        for (int i = 0; i < left; i++) {
            if (array[i] >= num) {
                return false;
            }
        }
        for (int i = left; i <= right; i++) {
            if (array[i] != num) {
                return false;
            }
        }
        for (int i = right + 1; i < array.length - 1; i++) {
            if (array[i] <= num) {
                return false;
            }
        }
        return true;
    }

    private static int[] generateRandomArray(int maxLength, int maxValue) {
        int length = (int) (Math.random() * (maxLength + 1));
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = (int) (Math.random() * (maxValue + 1));
        }
        return result;
    }
}
