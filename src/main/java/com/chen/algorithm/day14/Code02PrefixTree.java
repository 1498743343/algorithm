package com.chen.algorithm.day14;

import java.util.HashMap;
import java.util.Map;

/**
 * code02 前缀树 -> hashmap 实现
 *
 * @author chenzihan
 * @date 2022/06/28
 */
public class Code02PrefixTree {
    private Node root;

    /**
     * 节点
     * 基于 map 实现的前缀树可以插入任意的字符串
     *
     * @author chenzihan
     * @date 2022/06/28
     */
    private static class Node {
        private int pass;
        private int end;
        private final Map<Integer, Node> next;

        public Node() {
            this.pass = 0;
            this.end = 0;
            this.next = new HashMap<>();
        }
    }

    public Code02PrefixTree() {
        this.root = new Node();
    }

    public void insert(String str) {
        if (str == null) {
            return;
        }
        char[] chars = str.toCharArray();
        Node currentNode = root;
        currentNode.pass++;
        int index;
        for (char aChar : chars) {
            index = aChar;
            if (!currentNode.next.containsKey(index)) {
                currentNode.next.put(index, new Node());
            }
            currentNode = currentNode.next.get(index);
            currentNode.pass++;
        }
        currentNode.end++;
    }

    public int search(String str) {
        if (str == null) {
            return 0;
        }
        Node currentNode = root;
        char[] chars = str.toCharArray();
        int index;
        for (char aChar : chars) {
            index = aChar;
            if (!currentNode.next.containsKey(index)) {
                return 0;
            }
            currentNode = currentNode.next.get(index);
        }
        return currentNode.end;
    }

    public void delete(String str) {
        if (search(str) == 0) {
            return;
        }
        char[] chars = str.toCharArray();
        Node currentNode = root;
        currentNode.pass--;
        int index;
        for (char aChar : chars) {
            index = aChar;
            if (--currentNode.next.get(index).pass == 0) {
                currentNode.next.remove(index);
                return;
            }
            currentNode = currentNode.next.get(index);
        }
        currentNode.end--;
    }

    public int prefixNumber(String pre) {
        if (pre == null) {
            return 0;
        }
        char[] chars = pre.toCharArray();
        Node currentNode = root;
        int index;
        for (char aChar: chars) {
            index = aChar;
            if (!currentNode.next.containsKey(index)) {
                return 0;
            }
            currentNode = currentNode.next.get(index);
        }
        return currentNode.pass;
    }


}
