package com.chen.algorithm.day15.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 复制带随机指针的链表
 * <a href="https://leetcode.cn/problems/copy-list-with-random-pointer/">测试链接</a>
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

    /**
     * 使用哈希表缓存复制
     *
     * @param head 头
     * @return {@link Node}
     */
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

    /**
     * 空间复杂度 O(1)
     * 整体分为三个步骤：
     * 1.在原有链表的基础上进行复制，每个节点都复制一个节点，然后挂在自己的后面：原来：1 -> 2 -> 3，现在 1 -> 1 -> 2 -> 2 -> 3 -> 3
     * 2.遍历 1 中建立好的链表，把随机指针放置好
     * 3.拆分原有链表和新链表
     *
     * @param head 头
     * @return {@link Node}
     */
    public static Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        // 第一步：复制链表，复制节点挂在原节点后面
        Node cur = head;
        Node next;
        while (cur != null) {
            next = cur.next;
            Node node = new Node(cur.val);
            cur.next = node;
            node.next = next;
            cur = next;
        }
        // 第二步：设置随即指针
        cur = head;
        Node random;
        while (cur != null) {
            random = cur.random;
            if (random != null) {
                cur.next.random = random.next;
            }
            cur = cur.next.next;
        }
        // 第三步：拆分
        Node newHead = head.next;
        cur = head;
        Node newCur;
        while (cur != null) {
            next = cur.next.next;
            newCur = cur.next;
            newCur.next = next == null ? null : next.next;
            cur.next = next;
            cur = next;
        }
        return newHead;
    }
}
