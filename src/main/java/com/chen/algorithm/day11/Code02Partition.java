package com.chen.algorithm.day11;


/**
 * code02分区
 * 对数组进行一次遍历，将数组分为两块，左侧为 <= num 的部分，右侧为 > num 的部分
 *
 * @author chenzihan
 * @date 2022/06/07
 */
public class Code02Partition {
    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        int length = 200;
        int value = 10;
        int tryTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < tryTime; i++) {
            int[] array = generateRandomArray(length, value);
            int num = (int) (Math.random() * (value + 1));
            int index = partition(array, num);
            if (!test(array, num, index)) {
                System.out.println("num = " + num);
                System.out.println("index = " + index);
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
     * 主要的思路是维护一个左侧的分界线和一个指针，左侧分界线内的值 <= num，index负责遍历数组
     *
     * @param array 数组
     * @param num   指定值
     * @return int
     */
    private static int partition(int[] array, int num) {
        if (array == null || array.length == 0) {
            return -1;
        }
        // 数组中的元素全部 <=num，返回 length-1，数组中的元素全部 >num，返回 -1
        int result = -1;
        int index = 0;
        while (index <= array.length - 1) {
            // 当前值 <= num 时，将 array[result+1] 位置的元素和 array[index] 位置的元素交换，然后 index++；result++
            if (array[index] <= num) {
//                swap(array, index, result + 1);
//                index++;
//                result++;
                // 下面这行代码等价于上面三行代码
                swap(array, index++, ++result);
            } else {
                // 当前值 > num 时，index++
                index++;
            }
        }
        return result;
    }

    private static boolean test(int[] array, int num, int index) {
        if (index == -1) {
            if (array.length == 0) {
                return true;
            }

        }
        boolean left = true;
        for (int i = 0; i <= index; i++) {
            if (array[i] > num) {
                left = false;
                break;
            }
        }
        boolean right = true;
        for (int i = index + 1; i < array.length; i++) {
            if (array[i] <= num) {
                right = false;
                break;
            }
        }
        return left && right;
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
