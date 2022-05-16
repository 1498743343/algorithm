package com.chen.algorithm.day6;


/**
 * code01 将 k 个有序链表合并，然后返回新链表的 head
 * day4做过一个合并两个有序链表的题，在这里将 k 个有序链表两两分组，进行递归合并，最终返回head
 * 测试链接：https://leetcode.cn/problems/merge-k-sorted-lists/
 *
 * @author chenzihan
 * @date 2022/05/16
 */
public class Code01MergeKSortedLists {
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
        return merge(lists, 0, lists.length - 1);
    }

    public static ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[right];
        }
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        return mergeTwoLists(merge(lists, left, mid), merge(lists, mid + 1, right));
    }

    public static ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a == null ? b : a;
        }
        ListNode head = new ListNode();
        ListNode currentNode = head;
        while (a != null && b != null) {
            if (a.val < b.val) {
                currentNode.next = a;
                a = a.next;
            } else {
                currentNode.next = b;
                b = b.next;
            }
            currentNode = currentNode.next;
        }
        currentNode.next = a == null ? b : a;
        return head.next;
    }
}
