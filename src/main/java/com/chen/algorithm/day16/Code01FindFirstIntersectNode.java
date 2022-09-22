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

    /**
     * 得到相交节点
     *
     * @param head1 head1
     * @param head2 head2
     * @return {@link Node}
     */
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getFirstLoopNode(head1);
        Node loop2 = getFirstLoopNode(head2);
        // 如果一个有环，一个没环，一定不相交
        // 都有环
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, head2, loop1, loop2);
        }
        // 都无环
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        return null;
    }

    /**
     * 都有环，可能两个链表相交于环上，也可能相交与入环前的节点上
     *
     * @param head1 head1
     * @param head2 head2
     * @param loop1 loop1
     * @param loop2 loop2
     * @return {@link Node}
     */
    private static Node bothLoop(Node head1, Node head2, Node loop1, Node loop2) {
        // 如果相等，那么相交与入环前或者入环的点相同
        if (loop1 == loop2) {
            Node c1 = head1;
            int n = 0;
            while (c1 != loop1) {
                n++;
                c1 = c1.next;
            }
            Node c2 = head2;
            while (c2 != loop2) {
                n--;
                c2 = c2.next;
            }
            c1 = n > 0 ? head1 : head2;
            c2 = c1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n > 0) {
                c1 = c1.next;
                n--;
            }
            while (c1 != c2) {
                c1 = c1.next;
                c2 = c2.next;
            }
            return c1;
        }
        // 如果不相等，一个环自旋一周，如果找到了另一个入环点，那么相交，否则是两个不同的环
        Node cur = loop1.next;
        while (cur != loop1) {
            if (cur == loop2) {
                return loop2;
            }
            cur = cur.next;
        }
        return null;
    }

    private static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        Node cur2 = head2;
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 == cur2) {
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n > 0) {
                cur1 = cur1.next;
                n--;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            return null;
        }
    }

    private static Node getFirstLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast.next == null || fast.next.next == null) {
            return null;
        }
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
