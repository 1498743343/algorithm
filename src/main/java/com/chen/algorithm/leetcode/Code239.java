
package com.chen.algorithm.leetcode;

import java.util.LinkedList;

/**
 * code239 leetcode 第239题
 * <a href="https://leetcode.cn/problems/sliding-window-maximum/?envType=study-plan-v2&envId=top-100-liked">测试链接</a>
 *
 * @author chenzihan
 */
public class Code239 {
    /**
     * 滑动窗口最大值<br/>
     * 利用一个降序排列的双端链表，链表只记录数组中元素的下标，每个数组的下标都会放到队尾。
     * 对于下标为 i 的元素，如果链表尾部的元素小于当前元素，那么就弹出尾部元素，直到尾部元素大于当前元素，这样就保证了链表的降序（这样做是因为 i 前面比 i 小的元素
     * 没有存在的必要，因为在 k 范围内他们都不是最大值）。所以我们只需要每次都拿队首元素，他就是最大值
     * @param nums 数组
     * @param k 窗口大小
     * @return 结果
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int ai = 0;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            // 弹出无效元素
            while (list.size() > 0 && nums[list.peekLast()] <= nums[i]) {
                list.pollLast();
            }
            // 当前元素入队
            list.addLast(i);
            // 校验队首元素是否过期，过期就需要弹出
            if (i - list.peekFirst() >= k) {
                list.pollFirst();
            }
            // 校验当前元素的位置是否够了 k 个，如果够了就可以开始在结果数组中添加元素了
            if (i >= k - 1) {
                ans[ai++] = nums[list.peekFirst()];
            }
        }
        return ans;
    }
}