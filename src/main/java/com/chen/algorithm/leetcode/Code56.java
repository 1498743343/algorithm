package com.chen.algorithm.leetcode;

import java.util.*;

/**
 * code56 leetcode 第56题
 * <a href="https://leetcode.cn/problems/merge-intervals/submissions/">测试链接</a>
 * 对于线段交集的题，将线段先排序，然后求解即可，类似的题还有 day13.Code01CoverMax
 *
 * @author chenzihan
 * @date 2022/09/15
 */
public class Code56 {

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        List<int[]> ans = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(arr -> arr[0]));
        for (int[] line : intervals) {
            int left = line[0];
            int right = line[1];
            // 如果当前集合为空，或者新的线段与集合中最后一个线段没有交际，直接添加新的线段
            if (ans.size() == 0 || ans.get(ans.size() - 1)[1] < left) {
                ans.add(new int[]{left, right});
            } else {
                // 如果当前线段与集合中的最后一个线段有交集，看看能不能更新集合中交际的右边界
                int curRight = ans.get(ans.size() - 1)[1];
                if (curRight < right) {
                    ans.get(ans.size() - 1)[1] = right;
                }
            }
        }
        return ans.toArray(new int[ans.size()][2]);
    }
}
