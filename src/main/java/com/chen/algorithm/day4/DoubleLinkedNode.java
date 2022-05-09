package com.chen.algorithm.day4;

/**
 * 双向链表节点
 *
 * @author chenzihan
 * @date 2022/05/09
 */
public class DoubleLinkedNode {
    /**
     * 当前节点值
     */
    public int value;
    /**
     * 上一个节点
     */
    public DoubleLinkedNode last;
    /**
     * 下一个节点
     */
    public DoubleLinkedNode next;

    public DoubleLinkedNode(int value) {
        this.value = value;
    }

    /**
     * 得到随机双向链表
     *
     * @param maxLength 最大长度
     * @param maxValue  最大值
     * @return {@link DoubleLinkedNode}
     */
    public static DoubleLinkedNode getRandomDoubleLinkedList(int maxLength, int maxValue) {
        int length = (int) (Math.random() * (maxLength + 1));
        if (length == 0) {
            return null;
        }
        DoubleLinkedNode head = new DoubleLinkedNode((int) (Math.random() * (maxValue + 1)));
        DoubleLinkedNode currentNode = head;
        for (int i = 1; i < length; i++) {
            DoubleLinkedNode node = new DoubleLinkedNode((int) (Math.random() * (maxValue + 1)));
            currentNode.next = node;
            node.last = currentNode;
            currentNode = node;
        }
        return head;
    }

    public void print() {
        DoubleLinkedNode currentNode = this;
        while (currentNode != null) {
            System.out.print(currentNode);
            currentNode = currentNode.next;
        }
    }

    @Override
    public String toString() {
        return value + " ";
    }
}
