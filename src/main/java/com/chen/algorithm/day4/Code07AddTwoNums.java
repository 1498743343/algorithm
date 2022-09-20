package com.chen.algorithm.day4;

/**
 * 测试链接：https://leetcode.cn/problems/add-two-numbers/<br/>
 * 题干：给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。请你将两个数相加，
 * 并以相同形式返回一个表示和的链表。你可以假设除了数字 0 之外，这两个数都不会以 0 开头。<br/>
 * 解题思路：解题一共分为三个阶段：1.两个链表都有的部分相加。2.长链表有，短链表没有的部分相加。3.两个链表都没有，但是有进位，需要新增一个节点
 *
 * @author chenzihan
 * @date 2022/05/12
 */
public class Code07AddTwoNums {
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

    /**
     * 添加两个数字
     *
     * @param l1 l1
     * @param l2 l2
     * @return {@link ListNode}
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dumy = new ListNode();
        ListNode cur = dumy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            int value = num1 + num2 + carry;
            carry = value / 10;
            cur.next = new ListNode(value % 10);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry != 0) {
            cur.next = new ListNode(1);
        }
        return dumy.next;
    }
}
