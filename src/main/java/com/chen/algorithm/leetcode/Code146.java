package com.chen.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * code146 leetcode 第146题
 * <a href="https://leetcode.cn/problems/lru-cache/">测试链接</a>
 *
 * @author chenzihan
 */
public class Code146 {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(5);
        for (int i = 0; i < 5; i++) {
            lruCache.put(i, i);
        }
        lruCache.put(0, 0);
    }

    static class LRUCache {
        static class Node {
            public int key;
            public int value;
            public Node next;
            public Node pre;
        }

        private final Map<Integer, Node> map;
        private final Node head;
        private final Node tail;
        private final int capacity;

        public LRUCache(int capacity) {
            this.map = new HashMap<>();
            this.head = new Node();
            this.tail = new Node();
            this.head.next = this.tail;
            this.tail.pre = this.head;
            this.capacity = capacity;
        }

        public int get(int key) {
            Node node = map.get(key);
            if (node == null) {
                return -1;
            } else {
                moveToHead(node);
                return node.value;
            }
        }

        public void put(int key, int value) {
            Node node = map.get(key);
            if (node != null) {
                node.value = value;
                moveToHead(node);
            } else {
                node = new Node();
                node.key = key;
                node.value = value;
                addToHead(node);
                if (map.size() > capacity) {
                    node = tail.pre;
                    remove(node);
                }
            }
        }

        private void addToHead(Node node) {
            Node next = head.next;
            node.next = next;
            next.pre = node;
            head.next = node;
            node.pre = head;
            map.put(node.key, node);
        }

        private void remove(Node node) {
            map.remove(node.key);
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        private void moveToHead(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.pre = head;
            node.next = head.next;
            head.next = node;
            node.next.pre = node;
        }
    }
}
