package com.chen.algorithm.leetcode;

/**
 * code55 leetcode 第55题
 * <a href="https://leetcode.cn/problems/jump-game/?favorite=2cktkvj">测试链接</a>
 * 写了三个版本，暴力递归到动态规划
 * 思路：先按照自己的想法把暴力方法写出来，对于任意一个位置 i，只要我能到达的后面任意一个点 k 能走到终点，那就说明 i 位置可以走到终点
 *
 * @author chenzihan
 * @date 2022/09/15
 */
public class Code55 {
    /**
     * 暴力递归
     *
     * @param nums 数组
     * @return boolean
     */
    public boolean canJump1(int[] nums) {
        return process(0, nums);
    }

    /**
     * 当前位置 index 往后看，只要我能到达的点有一个可以走到终点，我就能到终点
     *
     * @param index 当前位置
     * @param nums  数组
     * @return boolean
     */
    private boolean process(int index, int[] nums) {
        if (index == nums.length - 1) {
            return true;
        }
        int cur = nums[index];
        for (int i = 1; i <= cur && index + i < nums.length; i++) {
            boolean flag = process(index + i, nums);
            if (flag) {
                return true;
            }
        }
        return false;
    }

    /**
     * 动态规划1：使用 dp 数组来记录之前已经算过的结果
     * 其实这个版本就已经可以 ac 了，但是速度和内存占用都很差
     *
     * @param nums 数组
     * @return boolean
     */
    public boolean canJump2(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[n - 1] = true;
        for (int index = n - 2; index >= 0; index--) {
            int cur = nums[index];
            boolean flag = false;
            // 我自己就可以到达终点
            if (cur + index >= n - 1) {
                flag = true;
            } else {
                for (int i = 1; i <= cur && index + i < nums.length; i++) {
                    // 我后面的点有能到终点的
                    if (dp[index + i]) {
                        flag = true;
                        break;
                    }
                }
            }
            dp[index] = flag;
        }
        return dp[0];
    }

    /**
     * 动态规划2：由动态规划1方法延申思考：能不能将空间复杂度降到 O(1)
     * 经过思考以后发现是可以的，我只要使用一个变量 okIndex 来记录能到达终点的最靠前的位置，对于任意 index 我看看我能不能到达 okIndex 位置就能知道我能不能到达终点，
     * 如果 index 可以到达 okIndex 位置，就更新 okIndex = index，这样从后往前遍历，到达 0 位置时，只需要看看 okIndex 有没有被更新为 0 即可得到答案
     *
     * @param nums 数组
     * @return boolean
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int okIndex = n - 1;
        for (int index = n - 2; index >= 0; index--) {
            int cur = nums[index];
            if (cur + index >= okIndex) {
                okIndex = index;
            }
        }
        return okIndex == 0;
    }
}
