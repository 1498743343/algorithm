package com.chen.algorithm.day15.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个由Node节点类型组成的无环单链表的头节点 head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点
 * 测试链接 : https://leetcode.com/problems/copy-list-with-random-pointer/
 *
 * @author chenzihan
 * @date 2022/07/04
 */
public class CopyListWithRandom {

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static Node copyRandomList1(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node current = head;
        while (current != null) {
            map.put(current, new Node(current.val));
            current = current.next;
        }
        current = head;
        while (current != null) {
            Node node = map.get(current);
            node.random = map.get(current.random);
            current = current.next;
            node.next = map.get(current);
        }
        return map.get(head);
    }

    public static Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        Node current = head;
        Node next;
        while (current != null) {
            next = current.next;
            current.next = new Node(current.val);
            current.next.next = next;
            current = next;
        }
        current = head;
        while (current != null) {
            Node newHead = current.next;
            if (current.random != null) {
                newHead.random = current.random.next;
            } else {
                newHead.random = null;
            }
            current = current.next.next;
        }
        Node result = head.next;
        Node newCurrent;
        current = head;
        while (current != null) {
            next = current.next.next;
            newCurrent = current.next;
            current.next = next;
            newCurrent.next = next == null ? null : next.next;
            current = next;
        }
        return result;
    }
}
