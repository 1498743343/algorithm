package com.chen.algorithm.day15.linkedlist;

import java.net.NoRouteToHostException;

/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 *
 * @author chenzihan
 * @date 2022/07/04
 */
public class SmallerEqualBigger {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node listPartition1(Node head, int value) {
        if (head == null || head.next == null) {
            return head;
        }
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        Node[] nodeArr = new Node[size];
        current = head;
        for (int i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = current;
            current = current.next;
        }
        arrPartition(nodeArr, value);
        for (int i = 1; i < nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[size - 1].next = null;
        return nodeArr[0];
    }

    public static Node listPartition2(Node head, int value) {
        if (head == null || head.next == null) {
            return head;
        }
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node bH = null;
        Node bT = null;
        while (head != null) {
            Node next = head.next;
            head.next = null;
            if (head.value < value) {
                if (sH == null) {
                    sH = head;
                } else {
                    sT.next = head;
                }
                sT = head;
            } else if (head.value == value) {
                if (eH == null) {
                    eH = head;
                } else {
                    eT.next = head;
                }
                eT = head;
            } else {
                if (bH == null) {
                    bH = head;
                } else {
                    bT.next = head;
                }
                bT = head;
            }
            head = next;
        }
        if (sT != null) {
            sT.next = eH == null ? bH : eH;
        }
        if (eT != null) {
            eT.next = bH;
        }
        return sH != null ? sH : (eH != null ? eH : bH);
    }

    public static void arrPartition(Node[] nodeArr, int value) {
        int leftBorder = -1;
        int rightBorder = nodeArr.length;
        int index = 0;
        while (index < rightBorder) {
            if (nodeArr[index].value < value) {
                swap(nodeArr, index++, ++leftBorder);
            } else if (nodeArr[index].value == value) {
                index++;
            } else {
                swap(nodeArr, index, --rightBorder);
            }
        }
    }

    public static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int length = 10;
        int value = 20;
        int tryTimes = 10;
        for (int i = 0; i < tryTimes; i++) {
            Node head1 = genernateRandomLinkedList(length, value);
            printLinkedList(head1);
            int randomValue = (int) (Math.random() * (value + 1));
            System.out.println("random value = " + randomValue);
            Node head2 = copyLinkedList(head1);
            Node node = listPartition1(head1, randomValue);
            printLinkedList(node);
            Node node1 = listPartition2(head2, randomValue);
            printLinkedList(node1);
            System.out.println("==========================");
        }
    }

    private static Node copyLinkedList(Node head) {
        if (head == null) {
            return null;
        }
        Node result = new Node(head.value);
        Node cur = result;
        head = head.next;
        while (head != null) {
            cur.next = new Node(head.value);
            cur = cur.next;
            head = head.next;
        }
        return result;
    }

    private static Node genernateRandomLinkedList(int length, int value) {
        int len = (int) (Math.random() * (length + 1));
        if (len >= 1) {
            Node head = new Node((int) (Math.random() * (value + 1)));
            Node cur = head;
            for (int i = 1; i < len; i++) {
                cur.next = new Node((int) (Math.random() * (value + 1)));
                cur = cur.next;
            }
            return head;
        } else {
            return null;
        }
    }

}
