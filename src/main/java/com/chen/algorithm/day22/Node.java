package com.chen.algorithm.day22;

import java.util.ArrayList;
import java.util.List;

/**
 * 图结构：节点
 *
 * @author chenzihan
 * @date 2022/08/15
 */
public class Node {
    public int value;
    public int in;
    public int out;
    public List<Edge> edges;
    public List<Node> nextNodes;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        edges = new ArrayList<>();
        nextNodes = new ArrayList<>();
    }
}
