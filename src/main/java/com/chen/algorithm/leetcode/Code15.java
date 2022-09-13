package com.chen.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * code15 leetcode 第15题
 * <a href="https://leetcode.cn/problems/3sum/?favorite=2cktkvj">测试链接</a>
 * 先把数组排序，然后使用左右双指针来解题
 *
 * @author chenzihan
 * @date 2022/09/13
 */
public class Code15 {
    public static void main(String[] args) {
        int[] test = {1, -1, -1, 0};
        List<List<Integer>> lists = threeSum(test);
        System.out.println(lists);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        // 先把数组排序，然后使用双指针来找到答案
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int length = nums.length;
        for (int index = 0; index < length - 2; index++) {
            // 如果 nums[index] > 0，那他后面的值肯定也都 > 0，此时就不会有正确答案了
            if (nums[index] > 0) {
                break;
            }
            // 当 nums[index] == nums[index-1]时，直接跳到下一个，因为要去掉重复解
            // 注意这里不能用 nums[index] == nums[index+1]来表示，因为这样可能会让 left 的取值少一个
            // 比如，[-1,-1,0，2]这种情况，其实 [-1,-1,2]是一个正确解，但是 index == 0时，跳到了 index == 1，这样就让 left 错过了可以拿到 -1 的机会
            if (index > 0 && nums[index] == nums[index - 1]) {
                continue;
            }
            int left = index + 1;
            int right = length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right] + nums[index];
                if (sum == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[index]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    ans.add(list);
                    // 去除掉关于 nums[left] 的重复解
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 去除掉关于 nums[right] 的重复解
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    // 到这里仍然满足 sum == 0，所以让左右指针都挪动一个位置
                    left++;
                    right--;
                } else if (sum < 0) {
                    // < 0，让 left++，来让 nums[left] 的值增加
                    left++;
                } else {
                    // > 0，让 right--，来让 nums[right] 的值减小
                    right--;
                }
            }
        }
        return ans;
    }
}
