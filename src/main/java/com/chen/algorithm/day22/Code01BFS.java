package com.chen.algorithm.day22;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * code01 图的宽度优先遍历(Breadth First Search)
 *
 * @author chenzihan
 * @date 2022/08/15
 */
public class Code01BFS {

    public static void bfs(Node start) {
        if (start == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println("node: " + node.value);
            for (Node next : node.nextNodes) {
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }
}
