package com.chen.algorithm.day4;

/**
 * 测试
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * @author chenzihan
 * @date 2022/05/10
 */
public class Test {
    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end = dummy;
        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = start;
        }
        return dummy.next;
    }

    /**
     * 反转链表，并返回反转后的头部节点
     *
     * @param head 头
     * @return {@link ListNode}
     */
    public static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


}
