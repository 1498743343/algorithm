package com.chen.algorithm.day10;

import java.util.Arrays;

/**
 * code02 求数组的小和
 * 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和
 * 例子： [1,3,4,2,5]
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1、3
 * 2左边比2小的数：1
 * 5左边比5小的数：1、3、4、2
 * 所以数组的小和为1+1+3+1+1+3+4+2=16
 *
 * @author chenzihan
 * @date 2022/06/06
 */
public class Code02SmallSum {

    public static void main(String[] args) {
        int length = 10;
        int value = 100;
        int tryTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < tryTimes; i++) {
            int[] array = generateRandomArray(length, value);
//            smallSum(array);
            int[] copy = Arrays.copyOf(array, array.length);
            if (smallSum(copy) != test(array)) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static int test(int[] array) {
        if (array == null || array.length <= 1) {
            return 0;
        }
        int length = array.length;
        int result = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j]) {
                    result += array[j];
                }
            }
        }
        return result;
    }

    private static int smallSum(int[] array) {
        if (array == null || array.length <= 1) {
            return 0;
        }
        return process(array, 0, array.length - 1);
    }

    private static int process(int[] array, int left, int right) {
        // 递归实现的归并排序，此处直接 return，因为需要进行累加，所以 process 方法有了返回值，此处也返回 0
        if (left == right) {
            return 0;
        }
        int middle = left + ((right - left) >> 1);
        // 执行顺序没有区别，只不过增加了返回值，真正有用的值是 merge 方法的返回值
        return process(array, left, middle) + process(array, middle + 1, right) + merge(array, left, middle, right);
    }

    private static int merge(int[] array, int left, int middle, int right) {
        int leftPoint = left;
        int rightPoint = middle + 1;
        int[] help = new int[right - left + 1];
        int index = 0;
        int result = 0;
        while (leftPoint <= middle && rightPoint <= right) {
            if (array[leftPoint] < array[rightPoint]) {
                result += array[leftPoint] * (right - rightPoint + 1);
                help[index++] = array[leftPoint++];
            } else {
                help[index++] = array[rightPoint++];
            }
        }
        while (leftPoint <= middle) {
            help[index++] = array[leftPoint++];
        }
        while (rightPoint <= right) {
            help[index++] = array[rightPoint++];
        }
        for (index = 0; index < help.length; index++) {
            array[left + index] = help[index];
        }
        return result;

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
