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
public class Code05ReverseNodesInKGroup {

    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode end = getKNode(start, k);
        // 不够 k 个节点时，直接返回原来的 head 节点
        if (end == null) {
            return head;
        }
        // 够 k 个节点时，做第一组的反转操作，反转后的 head 节点就是需要返回的结果，以后都不再动 head
        reverse(start, end);
        head = end;
        // 以后每次做反转操作时，通过 getKNode 获取到的 end 节点实际就是反转后的头部节点，start 节点就是反转后的尾部节点，重新申请一个指针 lastEnd，
        // 可以让我们记录上一组的 end 节点，用于指向 getKNode 获取到的 end 节点，当 lastEnd.next == null 时，说明最后一组不够 k 个，直接返回
        ListNode lastEnd = start;
        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = getKNode(start, k);
            if (end == null) {
                return head;
            }
            reverse(start, end);
            lastEnd.next = end;
            lastEnd = start;
        }
        return head;
    }

    /**
     * 以 start 节点开始，得到一组个数为 k 的节点的尾部节点，当 start 节点后没有 k-1 个元素，那么就返回 null
     * 这里简单说明一下 start 节点后没有 k-1 个元素时，不能返回链表尾部元素的原因：我们如果想统一处理分组后反转的逻辑，就不能返回链表尾部的元素，
     * 因为返回以后，最后一组也会进行反转，但是题意是不反转最后不够 k 个元素的这组
     *
     * @param start 起始节点
     * @param k     k
     * @return {@link ListNode}
     */
    public static ListNode getKNode(ListNode start, int k) {
        while (k > 1 & start != null) {
            start = start.next;
            k--;
        }
        return start;
    }

    public static void reverse(ListNode start, ListNode end) {
        end = end.next;
        ListNode pre = null;
        ListNode current = start;
        ListNode next;
        while (current != end) {
            next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        start.next = end;
    }
}
