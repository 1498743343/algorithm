package com.chen.algorithm.day4;

import java.util.ArrayList;
import java.util.List;

/**
 * code02反向双向链表
 * 给定一个双向链表的头部节点 head，将此链表反转
 *
 * @author chenzihan
 * @date 2022/05/09
 */
public class Code02ReverseDoubleLinkedList {

    /**
     * 将一个双向链表装入一个 List 中
     *
     * @param head 头
     * @return {@link List}<{@link Integer}>
     */
    public static List<Integer> getDoubleLinkedList(DoubleLinkedNode head) {
        List<Integer> result = new ArrayList<>();
        while (head != null) {
            result.add(head.value);
            head = head.next;
        }
        return result;
    }

    /**
     * 检查双向链表
     *
     * @param list 列表
     * @param head 头
     * @return boolean
     */
    public static boolean checkDoubleLinkedList(List<Integer> list, DoubleLinkedNode head) {
        int length = list.size();
        DoubleLinkedNode end = null;
        for (int i = length - 1; i >= 0; i--) {
            if (!list.get(i).equals(head.value)) {
                return false;
            }
            end = head;
            head = head.next;
        }
        for (Integer integer : list) {
            assert end != null;
            if (!integer.equals(end.value)) {
                return false;
            }
            end = end.last;
        }
        return true;
    }

    /**
     * 反向双向链表
     *
     * @param head 头
     */
    public static DoubleLinkedNode reverseDoubleLinkedList(DoubleLinkedNode head) {
        if (head == null || (head.last == null && head.next == null)) {
            return head;
        }
        DoubleLinkedNode pre = null;
        DoubleLinkedNode next;
        while (head != null) {
            // 用 next 占住 head.next 节点，否则不能调整 head 的 last 指针
            next = head.next;
            // 调整 head 的 last 和 next 指针
            head.next = pre;
            head.last = next;
            // 用 pre 占住当前的 head，一是进行下一次转换指针，二是当转换到最后一个节点时，head == null，此时 pre 节点即为反转后队列的 head 节点，直接返回 pre
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        int maxLength = 20;
        int maxValue = 100;
        int tryTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < tryTimes; i++) {
            DoubleLinkedNode head = DoubleLinkedNode.getRandomDoubleLinkedList(maxLength, maxValue);
            List<Integer> doubleLinkedList = getDoubleLinkedList(head);
            head = reverseDoubleLinkedList(head);
            if (!checkDoubleLinkedList(doubleLinkedList, head)) {
                System.out.println("============================");
                System.out.println(doubleLinkedList);
                assert head != null;
                head.print();
                System.out.println();
                break;
            }
        }
        System.out.println("测试结束");
    }


}
