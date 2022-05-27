package com.chen.algorithm.day9;


import java.util.ArrayList;
import java.util.List;

/**
 * code01 反转链表
 * 给定 head 节点，分别反转单向链表和双向链表
 *
 * @author chenzihan
 * @date 2022/05/27
 */
public class Code01ReverseList {

    private static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    /**
     * 生成随机链表
     * 取值范围：[0,maxValue]，长度范围：[0,maxLength]
     *
     * @param maxValue  最大值
     * @param maxLength 最大长度
     * @return {@link Node}
     */
    private static Node generateRandomLinkedList(int maxValue, int maxLength) {
        int length = (int) (Math.random() * (maxLength + 1));
        if (length == 0) {
            return null;
        }
        Node head = new Node((int) (Math.random() * (maxValue + 1)));
        Node currentNode = head;
        for (int i = 0; i < length - 1; i++) {
            Node node = new Node((int) (Math.random() * (maxValue + 1)));
            currentNode.next = node;
            currentNode = node;
        }
        return head;
    }

    /**
     * 根据给定的 head 节点，将此链表上的所有节点的值顺序存储到 list 中并返回
     *
     * @param head 头
     * @return {@link List}<{@link Integer}>
     */
    private static List<Integer> generateCheckList(Node head) {
        List<Integer> result = new ArrayList<>();
        while (head != null) {
            result.add(head.value);
            head = head.next;
        }
        return result;
    }

    /**
     * 根据给定的 head 节点，反转链表
     *
     * @param head 头
     * @return {@link Node}
     */
    private static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 检查是否反转链表成功
     *
     * @param list        列表
     * @param reverseHead 扭转头
     * @return boolean
     */
    private static boolean checkIsReversed(List<Integer> list, Node reverseHead) {
        int size = list.size();
        if (size == 0 && reverseHead == null) {
            return true;
        }
        for (int i = size - 1; i >= 0; i--) {
            if (reverseHead == null || !list.get(i).equals(reverseHead.value)) {
                return false;
            }
            reverseHead = reverseHead.next;
        }
        return true;
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            value = data;
        }
    }

    /**
     * 生成随机双链表
     * 生成随机双向链表
     * 取值范围：[0,maxValue]，长度范围：[0,maxLength]
     *
     * @param maxValue  最大值
     * @param maxLength 最大长度
     * @return {@link DoubleNode}
     */
    private static DoubleNode generateRandomDoubleLinkedList(int maxValue, int maxLength) {
        int length = (int) (Math.random() * (maxLength + 1));
        if (length == 0) {
            return null;
        }
        DoubleNode head = new DoubleNode((int) (Math.random() * (maxValue + 1)));
        DoubleNode currentNode = head;
        for (int i = 0; i < length - 1; i++) {
            DoubleNode doubleNode = new DoubleNode((int) (Math.random() * (maxValue + 1)));
            currentNode.next = doubleNode;
            doubleNode.last = currentNode;
            currentNode = doubleNode;
        }
        return head;
    }

    /**
     * 根据给定的 head 节点，将此链表上的所有节点的值顺序存储到 list 中并返回
     *
     * @param head 头
     * @return {@link List}<{@link Integer}>
     */
    private static List<Integer> generateCheckDoubleList(DoubleNode head) {
        List<Integer> result = new ArrayList<>();
        while (head != null) {
            result.add(head.value);
            head = head.next;
        }
        return result;
    }

    /**
     * 反向双向链表
     *
     * @param head 头
     * @return {@link DoubleNode}
     */
    private static DoubleNode reverseDoubleLinkedList(DoubleNode head) {
        if (head == null) {
            return null;
        }
        DoubleNode pre = null;
        DoubleNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 检查双向链表是否反转成功
     *
     * @param list 列表
     * @param head 头
     * @return boolean
     */
    private static boolean checkIsReversed2(List<Integer> list, DoubleNode head) {
        int size = list.size();
        if (size == 0 && head == null) {
            return true;
        }
        for (int i = size - 1; i >= 0; i--) {
            if (head == null || !list.get(i).equals(head.value)) {
                return false;
            }
            if (head.next != null) {
                head = head.next;
            }
        }
        for (Integer integer : list) {
            if (head == null || !integer.equals(head.value)) {
                return false;
            }
            head = head.last;
        }
        return true;

    }

    public static void main(String[] args) {
        int len = 1000;
        int value = 100;
        int testTime = 100000;
        System.out.println("test begin!");
        for (int i = 0; i < testTime; i++) {
            Node head = generateRandomLinkedList(value, len);
            List<Integer> list = generateCheckList(head);
            Node reverseHead = reverseLinkedList(head);
            if (!checkIsReversed(list, reverseHead)) {
                System.out.println("单向链表反转失败");
            }

            DoubleNode head2 = generateRandomDoubleLinkedList(value, len);
            List<Integer> list2 = generateCheckDoubleList(head2);
            DoubleNode reverseHead2 = reverseDoubleLinkedList(head2);
            if (!checkIsReversed2(list2, reverseHead2)) {
                System.out.println("双向链表反转失败");
            }
        }
        System.out.println("test finish!");

    }
}