package com.chen.algorithm.day14;

/**
 * code01 前缀树 -> 数组实现
 * 1）单个字符串中，字符从前到后的加到一棵多叉树上
 * 2）字符放在路上，节点上有专属的数据项（常见的是pass和end值）
 * 3）所有样本都这样添加，如果没有路就新建，如有路就复用
 * 4）沿途节点的pass值增加1，每个字符串结束时来到的节点end值增加1
 * <p>
 * 可以完成前缀相关的查询
 *
 * @author chenzihan
 * @date 2022/06/28
 */
public class Code01PrefixTree {
    private final Node root;

    /**
     * 节点
     * 基于数组实现的前缀树有局限性，比如案例中的只能插入只包含 'a' -> 'z' 的字符串
     *
     * @author chenzihan
     * @date 2022/06/28
     */
    private static class Node {
        private int pass;
        private int end;
        private final Node[] next;

        public Node() {
            this.pass = 0;
            this.end = 0;
            this.next = new Node[26];
        }
    }

    public Code01PrefixTree() {
        this.root = new Node();
    }

    public void insert(String str) {
        if (str == null) {
            return;
        }
        root.pass++;
        Node currentNode = root;
        char[] chars = str.toCharArray();
        int index;
        for (char aChar : chars) {
            index = aChar - 'a';
            if (currentNode.next[index] == null) {
                currentNode.next[index] = new Node();
            }
            currentNode = currentNode.next[index];
            currentNode.pass++;
        }
        currentNode.end++;
    }

    public int search(String str) {
        if (str == null) {
            return 0;
        }
        char[] chars = str.toCharArray();
        Node currentNode = root;
        int index;
        for (char aChar : chars) {
            index = aChar - 'a';
            if (currentNode.next[index] == null) {
                return 0;
            }
            currentNode = currentNode.next[index];
        }
        return currentNode.end;
    }

    /**
     * 删除
     * 在前缀树中删除指定字符传，调用一次删一个
     *
     * @param str str
     */
    public void delete(String str) {
        if (search(str) == 0) {
            return;
        }
        char[] chars = str.toCharArray();
        Node currentNode = root;
        currentNode.pass--;
        int index;
        for (char aChar : chars) {
            index = aChar - 'a';
            if (--currentNode.next[index].pass == 0) {
                currentNode.next[index] = null;
                return;
            }
            currentNode = currentNode.next[index];
        }
        currentNode.end--;
    }

    /**
     * 查询有多少个字符串，是以str做前缀的
     *
     * @param str str
     * @return int
     */
    public int prefixNumber(String str) {
        if (str == null) {
            return 0;
        }
        Node currentNode = root;
        char[] chars = str.toCharArray();
        int index;
        for (char aChar : chars) {
            index = aChar - 'a';
            if (currentNode.next[index] == null) {
                return 0;
            }
            currentNode = currentNode.next[index];
        }
        return currentNode.pass;
    }
}
