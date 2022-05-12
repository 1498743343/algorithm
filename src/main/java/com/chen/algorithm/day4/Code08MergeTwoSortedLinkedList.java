package com.chen.algorithm.day4;

/**
 * code08 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 测试链接：https://leetcode.cn/problems/merge-two-sorted-lists
 *
 * @author chenzihan
 * @date 2022/05/12
 */
public class Code08MergeTwoSortedLinkedList {
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 如果两个链表中有一个是 null，直接返回另一个链表
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }
        // 创建头部链表
        ListNode head;
        if (list1.val < list2.val) {
            head = list1;
            list1 = list1.next;
        } else {
            head = list2;
            list2 = list2.next;
        }
        // 使用 current 节点去遍历整个穿起来的链表
        ListNode current = head;
        // 当有一个链表遍历完时，停止循环
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                current = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                current = list2;
                list2 = list2.next;
            }
        }
        // 哪个链表不为空就让 current 的 next 指针指向谁，链表串联完成
        current.next = list1 == null ? list2 : list1;
        return head;
    }
}
