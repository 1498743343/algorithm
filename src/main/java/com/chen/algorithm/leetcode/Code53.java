package com.chen.algorithm.leetcode;

/**
 * code53 leetcode 第53题
 * <a href="https://leetcode.cn/problems/maximum-subarray/">测试链接</a>
 * 先后提交了几次：二维数组的dp，一维数组的dp，前缀和数组，三种方法都没通过
 * 思路：要求解 最大子数组和，我们就要在左右减去一部分，当然也可能不减
 * 左边剪支：左边剪支的原因一定是 前面的累加和是负数，即 pre + nums[i] < nums[i]
 * 右边剪支：我们从左到右遍历的过程中，可以记录区域累加的最大值，这样遍历到最后就拿到了最大值
 *
 * @author chenzihan
 * @date 2022/09/15
 */
public class Code53 {
    public int maxSubArray(int[] nums) {
        int pre = 0;
        int max = nums[0];
        for (int num : nums) {
            // 看看 前缀和 + 当前值 和 当前值 谁更大，谁更大，谁就是 pre
            pre = Math.max(pre + num, num);
            // 之前计算过的 max  和 当前 pre，谁更大谁就是 max
            max = Math.max(max, pre);
        }
        return max;
    }
}
