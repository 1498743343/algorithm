package com.chen.algorithm.day3;


/**
 * code04二分法
 * 已知一个数组 arr 相邻的值不相等，使用二分法找到一个局部最小值。
 * 局部最小值：arr[0]<arr[1]，则arr[0]为局部最小值；arr[n-1]>arr[n]，则arr[n]为局部最小值；arr[x-1]>arr[x]<arr[x+1]，则arr[x]为局部最小值
 *
 * @author chenzihan
 * @date 2022/05/07
 */
public class Code04BisectionMethodPartMin {
    /**
     * 得到随机数组，要求相邻的值不相等
     * 长度：[0,maxLength]；值：[0,maxValue]
     *
     * @param maxLength 最大长度
     * @param maxValue  最大价值
     * @return {@link int[]}
     */
    public static int[] getRandomArray(int maxLength, int maxValue) {
        int length = (int) (Math.random() * (maxLength + 1));
        int[] arr = new int[length];
        if (length > 0) {
            arr[0] = (int) (Math.random() * (maxValue + 1));
            for (int i = 1; i < length; i++) {
                do {
                    arr[i] = (int) (Math.random() * (maxValue + 1));
                } while (arr[i] == arr[i - 1]);
            }
        }
        return arr;
    }

    /**
     * 验证方法
     *
     * @param arr      数组
     * @param minIndex 局部最小值的index
     * @return boolean
     */
    public static boolean test(int[] arr, int minIndex) {
        if (arr == null || arr.length == 0) {
            return minIndex == -1;
        }
        int left = minIndex - 1;
        int right = minIndex + 1;
        boolean leftBigger = left < 0 || arr[left] > arr[minIndex];
        boolean rightBigger = right >= arr.length || arr[right] > arr[minIndex];
        return leftBigger && rightBigger;
    }

    /**
     * 使用二分法找到满足条件的下标
     *
     * @param arr 数组
     * @return boolean
     */
    public static int find(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int length = arr.length;
        if (length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[length - 2] > arr[length - 1]) {
            return length - 1;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left < right - 1) {
            int mid = (left + right) / 2;
            if (arr[mid - 1] > arr[mid] && arr[mid] < arr[mid + 1]) {
                return mid;
            } else {
                if (arr[mid - 1] > arr[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return arr[left] < arr[right] ? left : right;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLength = 20;
        int maxValue = 100;
        int tryTimes = 1000000;
        boolean success = true;
        for (int i = 0; i < tryTimes; i++) {
            int[] randomArray = getRandomArray(maxLength, maxValue);
            int partMin = find(randomArray);
            if (!test(randomArray, partMin)) {
                printArray(randomArray);
                System.out.println(partMin);
                success = false;
                break;
            }
        }
        System.out.println(success ? "验证没有问题" : "程序有错误");
    }
}
