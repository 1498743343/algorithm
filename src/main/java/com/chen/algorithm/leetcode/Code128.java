package com.chen.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * code128 leetcode 第128题
 * <a href="https://leetcode.cn/problems/longest-consecutive-sequence/comments/">测试链接</a>
 * 这道题比较简单粗暴的办法就是把数组排序，然后去寻找连续的最大区间，但是要求时间复杂度是 O(n)，所以不能给数组先排序
 * 一般时间复杂度低，就需要用空间换时间
 *
 * @author chenzihan
 * @date 2022/09/20
 */
public class Code128 {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 把所有的数字添加到 set 中
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 0;
        for (int num : nums) {
            // 找到一个区间段的起始位置，去计算这一段内的连续区间长度
            if (!set.contains(num - 1)) {
                int cur = num;
                int curMax = 0;
                while (set.contains(cur)) {
                    curMax++;
                    cur++;
                }
                max = Math.max(max, curMax);
            }
        }
        return max;
    }
}
