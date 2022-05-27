package com.chen.algorithm.day9;

/**
 * code02 删除链表中给定值
 * 测试链接：https://leetcode.cn/problems/remove-linked-list-elements/
 *
 * @author chenzihan
 * @date 2022/05/27
 */
public class Code02DeleteGivenValue {

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int data) {
            this.val = data;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return null;
        }
        ListNode current = head.next;
        ListNode pre = head;
        while (current != null) {
            if (current.val == val) {
                pre.next = current.next;
            } else {
                pre = current;
            }
            current = current.next;
        }
        return head;
    }

}
