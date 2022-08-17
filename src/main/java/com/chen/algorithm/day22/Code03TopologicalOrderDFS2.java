package com.chen.algorithm.day22;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * code03	拓扑排序：深度优先遍历，获取当前节点下所有可以到达的节点的最大深度，值大的排序在前
 * OJ链接：<a href="https://www.lintcode.com/problem/topological-sorting">测试链接</a>
 *
 * @author chenzihan
 * @date 2022/08/17
 */
public class Code03TopologicalOrderDFS2 {

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
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    static class Record {
        public DirectedGraphNode node;
        public int deep;

        public Record(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }


    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        if (graph == null || graph.isEmpty()) {
            return ans;
        }
        Map<DirectedGraphNode, Record> map = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            process(node, map);
        }
        map.values().stream()
                .sorted((record1, record2) -> record2.deep - record1.deep)
                .forEach(record -> ans.add(record.node));
        return ans;
    }

    private static Record process(DirectedGraphNode node, Map<DirectedGraphNode, Record> map) {
        if (map.containsKey(node)) {
            return map.get(node);
        }
        int deep = 0;
        for (DirectedGraphNode next : node.neighbors) {
            deep = Math.max(deep, process(next, map).deep);
        }
        Record record = new Record(node, deep + 1);
        map.put(node, record);
        return record;
    }

}
