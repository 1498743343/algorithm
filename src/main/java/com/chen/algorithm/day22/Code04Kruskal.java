package com.chen.algorithm.day22;

import java.util.*;

/**
 * code04 K算法找到最小生成树
 * k算法：将所有边拿到，按照权重排序，依次弹出边，判断边连接的两个点是否形成环，如果形成环了，不要当前边，继续下一条边
 * 使用并查集来判断是否形成环
 *
 * @author chenzihan
 * @date 2022/08/17
 */
public class Code04Kruskal {

    static class UnionFind {
        public Map<Node, Node> parent;
        public Map<Node, Integer> size;

        public UnionFind(Collection<Node> nodes) {
            parent = new HashMap<>();
            size = new HashMap<>();
            for (Node node : nodes) {
                parent.put(node, node);
                size.put(node, 1);
            }
        }

        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        public Node findFather(Node node) {
            Stack<Node> stack = new Stack<>();
            while (node != parent.get(node)) {
                stack.push(node);
                node = parent.get(node);
            }
            while (!stack.isEmpty()) {
                Node cur = stack.pop();
                parent.put(cur, node);
            }
            return node;
        }

        public void union(Node a, Node b) {
            Node aHead = findFather(a);
            Node bHead = findFather(b);
            if (aHead != bHead) {
                int aSize = size.get(aHead);
                int bSize = size.get(bHead);
                if (aSize > bSize) {
                    aSize = aSize + bSize;
                    parent.put(bHead, aHead);
                    size.put(aHead, aSize);
                } else {
                    bSize = aSize + bSize;
                    parent.put(aHead, bHead);
                    size.put(bHead, bSize);
                }
            }
        }
    }

    public static Set<Edge> kruskalMST(Graph graph) {
        Set<Edge> ans = new HashSet<>();
        if (graph == null) {
            return ans;
        }
        UnionFind unionFind = new UnionFind(graph.nodes.values());
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        queue.addAll(graph.edges);
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                ans.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return ans;
    }
}
