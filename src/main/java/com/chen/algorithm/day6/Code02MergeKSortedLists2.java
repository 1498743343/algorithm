package com.chen.algorithm.day6;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * code02 将 k 个有序链表合并，然后返回新链表的 head
 * 可以使用 Java 自带的有序结构去完成这个题目，如优先级队列
 * 测试链接：https://leetcode.cn/problems/merge-k-sorted-lists/
 *
 * @author chenzihan
 * @date 2022/05/16
 */
public class Code02MergeKSortedLists2 {
    public static class ListNode {
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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        int length = lists.length;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists) {
            // 必须保证每个 list 都不是 null 才可以添加到队列中
            if (list != null) {
                queue.add(list);
            }
        }
        if (queue.isEmpty()) {
            return null;
        }
        ListNode head = queue.poll();
        ListNode currentNode = head;
        if (head.next != null) {
            queue.add(head.next);
        }
        while (!queue.isEmpty()) {
            ListNode poll = queue.poll();
            currentNode.next = poll;
            if (poll.next != null) {
                queue.add(poll.next);
            }
            currentNode = currentNode.next;
        }
        return head;
    }
}
