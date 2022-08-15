package com.chen.algorithm.day22;

/**
 * 图结构：边
 *
 * @author chenzihan
 * @date 2022/08/15
 */
public class Edge {
    /**
     * 这条边的权重
     */
    public int weight;
    /**
     * 边的 from 节点
     */
    public Node from;
    /**
     * 边的 to 节点
     */
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
