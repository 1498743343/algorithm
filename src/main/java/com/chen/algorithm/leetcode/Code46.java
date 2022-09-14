package com.chen.algorithm.leetcode;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * code46 leetcode 第46题
 * <a href="https://leetcode.cn/problems/permutations/submissions/">测试链接</a>
 * 全排列问题就是递归的经典套路
 *
 * @author chenzihan
 * @date 2022/09/14
 */
public class Code46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        for (int num : nums) {
            path.add(num);
        }
        process(0, path, ans);
        return ans;
    }

    private void process(int index, List<Integer> path, List<List<Integer>> ans) {
        // 越界时说明没有需要填充的数字了，返回
        if (index == path.size()) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // 将 index 位置的元素和他后面的每个元素都进行交换
        for (int i = index; i < path.size(); i++) {
            Collections.swap(path, i, index);
            process(index + 1, path, ans);
            Collections.swap(path, i, index);
        }
    }
}
