package com.chen.algorithm.day22;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * code05 P算法找到最小生成树
 * p算法：用点解锁边，将解锁的边放入堆中，弹出堆顶的边，拿到连接的点，然后解锁连接点的边，将不形成环的边放入堆中，依次循环
 *
 * @author chenzihan
 * @date 2022/08/17
 */
public class Code05Prim {


    public static Set<Edge> primMST(Graph graph) {
        Set<Edge> ans = new HashSet<>();
        if (graph == null) {
            return ans;
        }
        PriorityQueue<Edge> edgeQueue = new PriorityQueue<>();
        Set<Node> nodeSet = new HashSet<>();
        // 这里的 for 循环是为了放森林，即在这个图中并不是所有的点都是联通的
        for (Node node : graph.nodes.values()) {
            if (!nodeSet.contains(node)) {
                nodeSet.add(node);
                for (Edge edge : node.edges) {
                    edgeQueue.add(edge);
                }
                while (!edgeQueue.isEmpty()) {
                    Edge edge = edgeQueue.poll();
                    Node toNode = edge.to;
                    if (!nodeSet.contains(toNode)) {
                        nodeSet.add(toNode);
                        ans.add(edge);
                        for (Edge nextEdge : toNode.edges) {
                            edgeQueue.add(nextEdge);
                        }
                    }
                }
            }
        }
        return ans;
    }
}
