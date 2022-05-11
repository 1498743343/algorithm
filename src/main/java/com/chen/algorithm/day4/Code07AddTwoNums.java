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
    public class ListNode {
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
        int list1Length = getLength(l1);
        int list2Length = getLength(l2);
        ListNode longList = list1Length >= list2Length ? l1 : l2;
        ListNode shortList = longList == l1 ? l2 : l1;
        ListNode currentNodeInLongList = longList;
        ListNode currentNodeInShortList = shortList;
        int carry = 0;
        int currentNum;
        ListNode last = currentNodeInLongList;
        while (currentNodeInShortList != null) {
            currentNum = currentNodeInLongList.val + currentNodeInShortList.val + carry;
            carry = currentNum / 10;
            currentNodeInLongList.val = currentNum % 10;
            last = currentNodeInLongList;
            currentNodeInLongList = currentNodeInLongList.next;
            currentNodeInShortList = currentNodeInShortList.next;
        }
        while (currentNodeInLongList != null) {
            currentNum = currentNodeInLongList.val + carry;
            carry = currentNum / 10;
            currentNodeInLongList.val = currentNum % 10;
            last = currentNodeInLongList;
            currentNodeInLongList = currentNodeInLongList.next;
        }
        if (carry != 0) {
            last.next = new ListNode(1);
        }
        return longList;
    }

    public static int getLength(ListNode head){
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }
}
