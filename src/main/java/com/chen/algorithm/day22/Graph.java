package com.chen.algorithm.day22;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
	/**
	 * 节点: key 是对应 node 的 value
	 */
	public HashMap<Integer, Node> nodes;
	public HashSet<Edge> edges;
	
	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}
