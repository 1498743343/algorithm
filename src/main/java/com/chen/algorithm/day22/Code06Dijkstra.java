package com.chen.algorithm.day22;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

/**
 * code06
 * 1）Dijkstra算法必须指定一个源点
 * 2）生成一个源点到各个点的最小距离表，一开始只有一条记录，即原点到自己的最小距离为0，源点到其他所有点的最小距离都为正无穷大
 * 3）从距离表中拿出没拿过记录里的最小记录，通过这个点发出的边，更新源点到各个点的最小距离表，不断重复这一步
 * 4）源点到所有的点记录如果都被拿过一遍，过程停止，最小距离表得到了
 *
 * @author chenzihan
 * @date 2022/08/23
 */
public class Code06Dijkstra {

    /**
     * dijkstra第一种算法：
     * 每次找到 map 中未封锁且距离最短的点，然后根据他所有的边的权重去更新 map 中的距离，直到所有的点都遍历完
     *
     * @param from 起始节点
     * @return {@link HashMap}<{@link Node}, {@link Integer}>
     */
    public static HashMap<Node, Integer> dijkstra1(Node from) {
        HashMap<Node, Integer> ans = new HashMap<>();
        ans.put(from, 0);
        // 已经封锁的点的集合
        HashSet<Node> set = new HashSet<>();
        Node cur = from;
        while (cur != null) {
            for (Edge edge : cur.edges) {
                Node next = edge.to;
                int weight = edge.weight;
                if (ans.containsKey(next)) {
                    ans.put(next, Math.min(ans.get(next), ans.get(cur) + weight));
                } else {
                    ans.put(next, ans.get(cur) + weight);
                }
            }
            set.add(cur);
            cur = getMinDistanceNode(ans, set);
        }
        return ans;
    }

    private static Node getMinDistanceNode(HashMap<Node, Integer> ans, HashSet<Node> set) {
        int min = Integer.MAX_VALUE;
        Node result = null;
        for (Entry<Node, Integer> entry : ans.entrySet()) {
            Node node = entry.getKey();
            if (!set.contains(node)) {
                if (min > entry.getValue()) {
                    min = entry.getValue();
                    result = entry.getKey();
                }
            }
        }
        return result;
    }

    /**
     * dijkstra第二种算法：
     * 优化寻找距离最短的点的过程，使用加强堆完成
     *
     * @param head 头
     * @param size 大小
     * @return {@link HashMap}<{@link Node}, {@link Integer}>
     */
    public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
        HashMap<Node, Integer> ans = new HashMap<>();
        NodeHeap heap = new NodeHeap(size);
        heap.addOrUpdateOrIgnore(head, 0);
        while (!heap.isEmpty()) {
            Record record = heap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges) {
                heap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            ans.put(cur, distance);
        }
        return ans;
    }
}
