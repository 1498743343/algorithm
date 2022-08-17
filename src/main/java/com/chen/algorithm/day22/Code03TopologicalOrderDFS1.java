package com.chen.algorithm.day22;

import java.util.*;

/**
 * code03	拓扑排序：深度优先遍历，获取当前节点下所有可以到达的节点个数和，值大的排序在前
 * OJ链接：<a href="https://www.lintcode.com/problem/topological-sorting">测试链接</a>
 *
 * @author chenzihan
 * @date 2022/08/17
 */
public class Code03TopologicalOrderDFS1 {

    /**
     * 题目中提供的类
     *
     * @author chenzihan
     * @date 2022/08/17
     */
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }

    static class Record {
        /**
         * 这里必须使用 long，否则数据量大时不能通过
         */
        public long nodes;
        public DirectedGraphNode node;

        public Record(long nodes, DirectedGraphNode node) {
            this.nodes = nodes;
            this.node = node;
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        Map<DirectedGraphNode, Record> map = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            process(node, map);
        }
        map.values().stream()
                .sorted((record1, record2) -> Long.compare(record2.nodes, record1.nodes))
                .forEach(record -> ans.add(record.node));
        return ans;
    }

    private static Record process(DirectedGraphNode node, Map<DirectedGraphNode, Record> map) {
        if (map.containsKey(node)) {
            return map.get(node);
        }
        long num = 0;
        for (DirectedGraphNode next : node.neighbors) {
            num += process(next, map).nodes;
        }
        Record record = new Record(num + 1, node);
        map.put(node, record);
        return record;
    }

}
