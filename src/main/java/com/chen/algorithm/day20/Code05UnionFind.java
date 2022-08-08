package com.chen.algorithm.day20;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * code05 并查集
 *
 * @author chenzihan
 * @date 2022/08/08
 */
public class Code05UnionFind {

    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFind<V> {
        public HashMap<V, Node<V>> nodeMap;
        public HashMap<Node<V>, Node<V>> parentsMap;
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionFind(List<V> values) {
            nodeMap = new HashMap<>();
            parentsMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : values) {
                Node<V> node = new Node<>(value);
                nodeMap.put(value, node);
                parentsMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        /**
         * 调整本条链上的所有节点，并返回顶级父节点
         * 因为在合并的方法中，没有将所有节点的父节点都做修改，所以在这需要将涉及到的所有节点的父节点都调整为正确的
         *
         * @param node 节点
         * @return {@link Node}<{@link V}>
         */
        public Node<V> findFather(Node<V> node) {
            Stack<Node> stack = new Stack<>();
            // 包括自己在内的除了顶级父节点的所有节点都压栈，node一直更新，直到成为顶级父节点
            while (parentsMap.get(node) != node) {
                stack.add(node);
                node = parentsMap.get(node);
            }
            // 将栈中所有的节点的父节点都设置为 node
            while (!stack.isEmpty()) {
                parentsMap.put(stack.pop(), node);
            }
            return node;
        }

        /**
         * 将包含 a 的集合和包含 b 的集合合并
         *
         * @param a 一个
         * @param b b
         */
        public void union(V a, V b) {
            Node<V> aHead = findFather(nodeMap.get(a));
            Node<V> bHead = findFather(nodeMap.get(b));
            // 如果 a 和 b 不是同一个父节点，则进行合并
            if (aHead != bHead) {
                int aSize = sizeMap.get(aHead);
                int bSize = sizeMap.get(bHead);
                Node<V> bigNode = aSize > bSize ? aHead : bHead;
                Node<V> smallNode = bigNode == aHead ? bHead : aHead;
                parentsMap.put(smallNode, bigNode);
                sizeMap.put(bigNode, aSize + bSize);
                // 需要在 sizeMap 中删除掉 size 小的节点，方便后面统计集合数量
                sizeMap.remove(smallNode);
            }
        }

        /**
         * 判断 a 和 b 是否在同一个集合中
         *
         * @param a 一个
         * @param b b
         * @return boolean
         */
        public boolean isSameSet(V a, V b) {
            return findFather(nodeMap.get(a)) == findFather(nodeMap.get(b));
        }

        /**
         * 集合数量
         *
         * @return int
         */
        public int sets() {
            return sizeMap.size();
        }
    }
}
