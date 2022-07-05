package com.chen.algorithm.day16;

/**
 * code01找到第一个相交节点
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，如果两个链表相交，请返回相交的第一个节点。如果不相交，返回null
 * 如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。
 *
 * @author chenzihan
 * @date 2022/07/05
 */
public class Code01FindFirstIntersectNode {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        // 1. 判断两个链表是否是包括环形
        Node loop1 = getLoopFirstNode(head1);
        Node loop2 = getLoopFirstNode(head2);
        // 2. 如果两个链表都包括环形
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        // 3. 如果两个链表都不包括环形
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        // 4. 如果一个包括环形，一个不包括环形，那么一定不相交
        // 5. 返回结果
        return null;
    }

    private static Node noLoop(Node head1, Node head2) {
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        // 如果俩个链表的尾节点不相等，那么一定不相交
        if (cur1 != cur2) {
            return null;
        }
        // 走到这里说明两个链表的尾节点相等，寻找相交的第一个节点
        cur1 = n < 0 ? head2 : head1;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n >= 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 两个链表都包括环形
     *
     * @param head1 head1
     * @param loop1 loop1
     * @param head2 head2
     * @param loop2 loop2
     * @return {@link Node}
     */
    private static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        // 如果 loop1 == loop2 说明他们一定相交，并且相交点是 loop 或者之前的节点
        if (loop1 == loop2) {
            Node cur1 = head1;
            Node cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            // 根据两个链表到 loop 的长度来赋值，cur1 为长的链表，cur2 为短的链表
            cur1 = n < 0 ? head2 : head1;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            // 长链表往下跳 n 个节点，和短链表的长度一致，然后一起往下跳，直到找到第一个相交点
            while (n >= 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            // 如果两个 loop 不相等，可能不相交，也可能相交，如果相交 loop1 和 loop2 是环上的两个点
            Node cur = loop2.next;
            while (cur != loop2) {
                if (cur == loop1) {
                    return cur;
                }
                cur = cur.next;
            }
            return null;
        }
    }

    private static Node getLoopFirstNode(Node head) {
        if (head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        // 走到这里说明一定有环，并且此时 slow == fast
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }

}
