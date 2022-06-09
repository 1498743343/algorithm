package com.chen.algorithm.day12;

import java.util.Arrays;

/**
 * code03堆排序
 * 数组可以看作是一个堆，用堆结构堆数组进行排序的思路如下：
 * 1. 先把数组变为一个大根堆
 * 2. 把堆的最大值即 0 位置和堆的最后一个元素交换，然后将 heapSize - 1，然后再将堆复原为一个大根堆，周而复始
 * 3. 当 heapSize == 0 时，排序完成
 *
 * @author chenzihan
 * @date 2022/06/08
 */
public class Code03HeapSort {

    public static void main(String[] args) {
        int maxLength = 500;
        int maxValue = Integer.MAX_VALUE;
        int tryTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < tryTimes; i++) {
            int[] array = generateRandomArray(maxLength, maxValue);
            int[] copy = Arrays.copyOf(array, array.length);
            heapSort(array);
            Arrays.sort(copy);
            if (!test(array, copy)) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static void heapSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int length = array.length;
        // 把数组弄成一个大根堆 -> O(N * logN)，使用 heapInsert 实现就得从堆的最后插入新元素，然后使当前元素上浮形成新的大根堆
//        for (int i = 0; i < length; i++) {
//            heapInsert(array, i);
//        }
        // 把数组弄成一个大根堆 -> O(N)，使用 heapify 实现从堆的底部开始遍历，使每个元素都下沉至适当位置形成大根堆，比 heapInsert 优秀在，越靠下的元素下沉的距离越短
        for (int i = length - 1; i >= 0; i--) {
            heapify(array, i, length);
        }
        int heapSize = length;
        // 数组实现的大根堆，0 位置就是最大值的所在处，所以将 0 位置和大根堆的最后一个元素交换，大根堆的最后一个元素就是这次循环的最大值，然后减小大根堆的size，循环遍历，直到大根堆只剩下一个元素
        while (heapSize > 0) {
            swap(array, 0, --heapSize);
            heapify(array, 0, heapSize);
        }

    }

    /**
     * 堆插入
     * 将数组中 index 位置的元素加入到堆区，使数组中的 [0,index] 位置成为一个大根堆
     *
     * @param array 数组
     * @param index 指数
     */
    private static void heapInsert(int[] array, int index) {
        // 当前节点的值大于父节点的值时，交换，然后 index 位置变为父节点位置
        while (array[index] > array[(index - 1) / 2]) {
            swap(array, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 指定位置的元素下沉
     *
     * @param array    数组
     * @param index    指数
     * @param heapSize 堆大小
     */
    private static void heapify(int[] array, int index, int heapSize) {
        // 左子节点的位置
        int left = 2 * index + 1;
        while (left < heapSize) {
            int largest = left;
            // 有右子节点，并且右子节点的值大于左子节点
            if (left + 1 < heapSize && array[left] < array[left + 1]) {
                largest = left + 1;
            }
            if (array[index] >= array[largest]) {
                largest = index;
            }
            if (index == largest) {
                break;
            }
            swap(array, index, largest);
            index = largest;
            left = 2 * index + 1;
        }
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static boolean test(int[] array, int[] copy) {
        if (array == null && copy == null) {
            return true;
        }
        if (array.length == 0 && copy.length == 0) {
            return true;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] != copy[i]) {
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
