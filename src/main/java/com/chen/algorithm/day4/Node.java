package com.chen.algorithm.day4;

/**
 * 节点
 *
 * @author chenzihan
 * @date 2022/05/09
 */
public class Node {
    /**
     * 当前节点值
     */
    public int value;
    /**
     * 下一个节点
     */
    public Node next;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 得到随机单向链表
     *
     * @param maxLength 最大长度
     * @param maxValue  最大值
     * @return {@link Node}
     */
    public static Node getRandomNodeList(int maxLength, int maxValue) {
        int length = (int) (Math.random() * (maxLength + 1));
        if (length == 0) {
            return null;
        }
        Node head = new Node((int) (Math.random() * (maxValue + 1)));
        Node tail = head;
        for (int i = 1; i < length; i++) {
            Node node = new Node((int) (Math.random() * (maxValue + 1)));
            tail.next = node;
            tail = node;
        }
        return head;
    }

    public void print() {
        Node head = this;
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return value + "";
    }
}
