package com.chen.algorithm.day4;

/**
 * 测试链接：https://leetcode.cn/problems/reverse-nodes-in-k-group/
 * <p>
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * @author chenzihan
 * @date 2022/05/09
 */
public class Code06ReverseNodesInKGroup2 {

    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end = dummy;
        // 当 end.next == null 时，说明 end 正好是最后一个节点了，此时就可以结束循环
        while (end.next != null) {
            // 从 end 开始数 k 个 node 作为下次的起始节点
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            // 用 start 指针指向局部翻转前的节点，因为他是翻转后的 end 节点，翻转后就拿不到他了，需要占住他作为下次循环的起始节点
            ListNode start = pre.next;
            // 用 next 指针指向 end.next 节点，这样可以占住 end.next 不被释放，在下一次循环时使用
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    /**
     * 给定链表头部节点 head，反转链表
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

