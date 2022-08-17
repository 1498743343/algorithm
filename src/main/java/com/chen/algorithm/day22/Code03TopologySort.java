package com.chen.algorithm.day22;

import java.util.*;

/**
 * code03 拓扑排序
 * 排序思路：将入度为 0 的元素排在前面，然后消除他们的影响：即将他们的 nextNodes 中的所有的入度都 -1，然后再找到入度为 0 的元素，依次循环
 *
 * @author chenzihan
 * @date 2022/08/16
 */
public class Code03TopologySort {

    public static List<Node> sortedTopology(Graph graph) {
        // key 是 node，value 是入度
        Map<Node, Integer> map = new HashMap<>();
        // 用来装入度为 0 的 node，保证他们的顺序
        Queue<Node> queue = new LinkedList<>();
        // 返回结果
        List<Node> ans = new ArrayList<>();
        for (Node node : graph.nodes.values()) {
            if (node.in == 0) {
                queue.add(node);
            }
            map.put(node, node.in);
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            ans.add(node);
            for (Node next : node.nextNodes) {
                map.put(next, --next.in);
                if (map.get(next) == 0) {
                    queue.add(node);
                }
            }
        }
        return ans;
    }
}
