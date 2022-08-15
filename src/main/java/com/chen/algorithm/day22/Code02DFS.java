package com.chen.algorithm.day22;

import java.util.HashSet;
import java.util.Stack;

/**
 * code02 图的深度优先遍历(Depth First Search)
 * 目前不清楚我的方法有没有问题，但是通过部分用例测试是没问题的
 *
 * @author chenzihan
 * @date 2022/08/15
 */
public class Code02DFS {
    /**
     * 我自己的方法
     *
     * @param start 开始
     */
    public static void dfs(Node start) {
        if (start == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(start);
        set.add(start);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println("node: " + node.value);
            for (Node next : node.nextNodes) {
                if (!set.contains(next)) {
                    stack.push(next);
                    set.add(next);
                }
            }
        }
    }

    /**
     * 课上讲的方法
     *
     * @param node 节点
     */
    public static void dfs2(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(node);
        set.add(node);
        System.out.println("node: " + node.value);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            for (Node next : pop.nextNodes) {
                if (!set.contains(next)) {
                    stack.push(pop);
                    stack.push(next);
                    set.add(next);
                    System.out.println("node: " + next.value);
                    break;
                }
            }
        }
    }
}
