package com.chen.algorithm.day4;

/**
 * 测试
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * @author chenzihan
 * @date 2022/05/10
 */
public class Test {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dumy = new ListNode();
        dumy.next = head;
        ListNode pre;
        ListNode start = head;
        ListNode end;
        ListNode next;
        while(true) {
            end = countK(start, k);
            if(end == null) {
                return dumy.next;
            }
            next = end.next;
            end.next = null;
            reverse(start);
            start.next = next;
            pre = start;
            start = next;
            pre.next = end;
        }
    }

    public void reverse(ListNode node) {
        ListNode pre = null;
        ListNode next;
        while(node != null){
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
    }

    public ListNode countK(ListNode node, int k){
        for(int i = 1; node != null && i < k; i++) {
            node = node.next;
        }
        return node;
    }
    public static class ListNode {
        public int val;
        public ListNode next;
    }
//
//    public ListNode reverseKGroup(ListNode head, int k) {
//        ListNode dummy = new ListNode();
//        dummy.next = head;
//        ListNode pre = dummy;
//        ListNode end = dummy;
//        ListNode next;
//        ListNode start = head;
//        while (end.next != null) {
//            for (int i = 0; i < k && end != null; i++) {
//                end = end.next;
//            }
//            if (end == null) {
//                break;
//            }
//            next = end.next;
//            end.next = null;
//            pre.next = reverse(start);
//            start.next = next;
//            pre = start;
//            end = start;
//            start = next;
//        }
//        return dummy.next;
//
//    }
//
//    public static ListNode reverse(ListNode head) {
//        ListNode pre = null;
//        ListNode next;
//        while (head != null) {
//            next = head.next;
//            head.next = pre;
//            pre = head;
//            head = next;
//        }
//        return pre;
//    }

}
