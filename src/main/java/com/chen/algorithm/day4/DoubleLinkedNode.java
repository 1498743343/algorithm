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
}
