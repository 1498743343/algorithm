package com.chen.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * code78 leetcode 第78题
 * <a href="https://leetcode.cn/problems/subsets/submissions/">测试链接</a>
 *
 * @author chenzihan
 * @date 2022/09/15
 */
public class Code78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        process(0, nums, path, ans);
        return ans;
    }

    public void process(int index, int[] nums, List<Integer> path, List<List<Integer>> ans) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        process(index + 1, nums, path, ans);
        path.add(nums[index]);
        process(index + 1, nums, path, ans);
        path.remove(path.size() - 1);
    }
}
