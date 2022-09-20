package com.chen.algorithm.leetcode;

/**
 * code141 leetcode 第141题
 * <a href="https://leetcode.cn/problems/linked-list-cycle/?favorite=2cktkvj">测试链接</a>
 *
 * @author chenzihan
 * @date 2022/09/20
 */
public class Code141 {
    /**
     * 快慢指针判断链表是否存在环
     *
     * @param head 头
     * @return boolean
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return false;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
