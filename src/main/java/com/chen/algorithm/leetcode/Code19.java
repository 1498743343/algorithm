package com.chen.algorithm.leetcode;

/**
 * code19 leetcode 第19题
 * <a href="https://leetcode.cn/problems/remove-nth-node-from-end-of-list/">测试链接</a>
 *
 * @author chenzihan
 * @date 2022/09/13
 */
public class Code19 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            length++;
        }
        if (n == length) {
            return head.next;
        }
        int target = length - n + 1;
        cur = head;
        int curIndex = 1;
        ListNode pre = null;
        while (curIndex != target) {
            pre = cur;
            cur = cur.next;
            curIndex++;
        }
        pre.next = cur.next;
        return head;
    }
}
