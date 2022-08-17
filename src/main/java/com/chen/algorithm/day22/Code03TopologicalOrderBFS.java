package com.chen.algorithm.day22;

import java.util.*;

/**
 * code03	拓扑排序：宽度优先遍历
 * OJ链接：<a href="https://www.lintcode.com/problem/topological-sorting">测试链接</a>
 *
 * @author chenzihan
 * @date 2022/08/17
 */
public class Code03TopologicalOrderBFS {

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

    /**
     * 提交测试的方法
     *
     * @param graph 图
     * @return {@link ArrayList}<{@link DirectedGraphNode}>
     */
    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        if (graph == null || graph.isEmpty()) {
            return ans;
        }
        Map<DirectedGraphNode, Integer> map = new HashMap<>();
        Queue<DirectedGraphNode> zeroInQueue = new LinkedList<>();
        for (DirectedGraphNode node : graph) {
            map.put(node, 0);
        }
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode next : node.neighbors) {
                map.put(next, map.get(next) + 1);
            }
        }
        for (DirectedGraphNode node : map.keySet()) {
            if (map.get(node).equals(0)) {
                zeroInQueue.add(node);
            }
        }
        while (!zeroInQueue.isEmpty()) {
            DirectedGraphNode node = zeroInQueue.poll();
            ans.add(node);
            for (DirectedGraphNode next : node.neighbors) {
                map.put(next, map.get(next) - 1);
                if (map.get(next).equals(0)) {
                    zeroInQueue.add(next);
                }
            }
        }
        return ans;
    }

}
