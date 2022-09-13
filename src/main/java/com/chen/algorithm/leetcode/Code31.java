package com.chen.algorithm.leetcode;

/**
 * code31 leetcode 第31题
 * <a href="https://leetcode.cn/problems/next-permutation/">测试链接</a>
 * 寻找下一个排列，其实可以理解为，在当前 nums 的顺序下找到比他大的最小值，举个例子：
 * nums = [1,2,3,8,5,7,6,4]，他组成的数组就是 12385764，所以符合题意的结果就是 12386457，即 [1,2,3,8,6,4,5,7]
 *
 * @author chenzihan
 * @date 2022/09/13
 */
public class Code31 {
    public void nextPermutation(int[] nums) {
        // 如果我们能在 nums 的所有排列中找到一个比当前排列组成的数值更大的数，那么一定说明整个数组不是全部降序的，即存在升序对
        // 我们要找到整个升序位置 i
        // 比如，我现在的数组是 [3,2,1]，那么就不可能找到一个比 321 更大的数，如果是 [1,3,2]，1 < 3，所以 1 就是我们要找的数，0 就是我们要找的 i
        int length = nums.length;
        // 找到 nums 上最后一个升序对 i,j ，即 nums[i] < nums[j]
        int i = length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // i 可能存在也可能不存在，如果 i 不存在，那说明 nums 是降序排列的，我们只要将 nums 逆序就可以得到下一个排列
        // 如果 i 存在，见下面的解析
        if (i >= 0) {
            // 找到 i 的位置以后，可以知道的是: 在 [i+1,length-1]范围上是降序的，那我们就需要找到刚刚满足 arr[j] > arr[i] 的位置 j
            // 因为 nums[i+1] > nums[i]，所以在 [i+1,length-1]范围上必然存在 j，满足 arr[j] > arr[i]并且 arr[j] 是这个范围上满足条件的最小值
            int j = length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            // 找到 j 位置以后，我们就可以将 i 和 j 位置的数字交换，此时就已经找到了一个满足要求的排列，但是根据分析我们要让排列的结果组成的数字尽可能小
            // 找到 j 以后可以满足最高位是最小值，但是后面是降序的，我们改成升序以后就可以满足要求了
            swap(nums, i, j);
        }
        // 通过上述两种情况的操作，到这里[i+1,length-1]位置上必然已经是降序的了，我们把顺序倒过来就可以了
        reverse(nums, i + 1, length - 1);

    }

    private void swap(int[] arr, int l1, int l2) {
        int tmp = arr[l1];
        arr[l1] = arr[l2];
        arr[l2] = tmp;
    }

    private void reverse(int[] arr, int left, int right) {
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }
}
