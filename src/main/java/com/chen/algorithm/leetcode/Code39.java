package com.chen.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * code39 leetcode 第39题
 * <a href="https://leetcode.cn/problems/combination-sum/submissions/">测试链接</a>
 * 这道题其实就是之前做过纸币组合的变种，不过由于之前求的是组合数，所以可以用动态规划进行简化，这里求的是所有的组合情况，所以采用剪支可以更方便一点
 * 注意这道题种，nums 值的取值范围都是正数，如果存在负数，那么一定要先将数组排序在进行递归
 *
 * @author chenzihan
 * @date 2022/09/14
 */
public class Code39 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 6, 7};
        List<List<Integer>> lists = combinationSum(nums, 7);
        System.out.println(lists);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        process(0, target, candidates, ans, path);
        return ans;
    }

    public static void process(int index, int rest, int[] arr, List<List<Integer>> ans, List<Integer> path) {
        if (index == arr.length) {
            return;
        }
        if (rest == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        process(index + 1, rest, arr, ans, path);
        int curNum = arr[index];
        if (rest >= curNum) {
            path.add(curNum);
            process(index, rest - curNum, arr, ans, path);
            path.remove(path.size() - 1);
        }
    }
}
