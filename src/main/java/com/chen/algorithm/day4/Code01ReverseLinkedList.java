package com.chen.algorithm.day4;

import java.util.ArrayList;
import java.util.List;

/**
 * code01反向单向链表
 * 给定一个头部节点 head，将此链表反转
 *
 * @author chenzihan
 * @date 2022/05/09
 */
public class Code01ReverseLinkedList {

    /**
     * 测试反向链接列表
     *
     * @param list 列表
     * @param head 头
     * @return boolean
     */
    public static boolean testReverseLinkedList(List<Node> list, Node head) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).value != head.value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 获得反向链接列表
     *
     * @param head 头
     * @return {@link List}<{@link Node}>
     */
    public static List<Node> getReverseLinkedList(Node head) {
        List<Node> result = new ArrayList<>();
        while (head != null) {
            result.add(head);
            head = head.next;
        }
        return result;
    }

    public static Node reverseLinkedList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
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

    public static void main(String[] args) {
        int maxLength = 20;
        int maxValue = 100;
        int tryTimes = 1000000;
        System.out.println("开始测试");
        for (int i = 0; i < tryTimes; i++) {
            Node head = Node.getRandomNodeList(maxLength, maxValue);
            List<Node> reverseLinkedList = getReverseLinkedList(head);
            head = reverseLinkedList(head);
            if (!testReverseLinkedList(reverseLinkedList, head)) {
                System.out.println(reverseLinkedList);
                assert head != null;
                head.print();
                break;
            }
        }
        System.out.println("测试结束");
    }
}
