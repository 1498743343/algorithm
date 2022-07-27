package com.chen.algorithm.day17;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * code02序列化和重建树
 * 可以完成二叉树序列化的方式有：前序遍历、后序遍历和按层遍历，中序遍历不可以，因为反序列化会有歧义，一个序列化的结果可能反序列化成多个二叉树
 *
 * @author chenzihan
 * @date 2022/07/27
 */
public class Code02SerializeAndReconstructTree {
    static class Node {
        public Node left;
        public Node right;
        public int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        int maxLevel = 100;
        int maxValue = 100;
        int tryTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < tryTime; i++) {
            Node head = generateRandomBinaryTree(1, maxLevel, maxValue);
            Queue<String> preQueue = preSerial(head);
            Queue<String> posQueue = posSerial(head);
            Queue<String> levelQueue = levelSerial(head);
            Node preHead = buildPre(preQueue);
            Node posHead = buildPos(posQueue);
            Node levelHead = buildLevel(levelQueue);
            if (!isSameValueStructure(preHead, posHead) || !isSameValueStructure(posHead, levelHead)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }

    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    private static Node buildLevel(Queue<String> levelQueue) {
        if (levelQueue == null || levelQueue.isEmpty()) {
            return null;
        }
        Node head = generateNode(levelQueue.poll());
        if (head != null) {
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                node.left = generateNode(levelQueue.poll());
                node.right = generateNode(levelQueue.poll());
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return head;
    }

    private static Node generateNode(String nodeStr) {
        if (nodeStr == null) {
            return null;
        } else {
            return new Node(Integer.parseInt(nodeStr));
        }
    }

    private static Node buildPos(Queue<String> posQueue) {
        if (posQueue == null || posQueue.isEmpty()) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        while (!posQueue.isEmpty()) {
            stack.push(posQueue.poll());
        }
        return posb(stack);
    }

    private static Node posb(Stack<String> stack) {
        String headStr = stack.pop();
        if (headStr == null) {
            return null;
        }
        Node head = new Node(Integer.parseInt(headStr));
        head.right = posb(stack);
        head.left = posb(stack);
        return head;
    }

    private static Node buildPre(Queue<String> preQueue) {
        if (preQueue == null || preQueue.isEmpty()) {
            return null;
        }
        return preb(preQueue);
    }

    private static Node preb(Queue<String> preQueue) {
        String headStr = preQueue.poll();
        if (headStr == null) {
            return null;
        }
        Node head = new Node(Integer.parseInt(headStr));
        head.left = preb(preQueue);
        head.right = preb(preQueue);
        return head;
    }

    private static Queue<String> levelSerial(Node head) {
        Queue<String> result = new LinkedList<>();
        if (head == null) {
            result.add(null);
        } else {
            result.add(String.valueOf(head.value));
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                    result.add(String.valueOf(node.left.value));
                } else {
                    result.add(null);
                }
                if (node.right != null) {
                    queue.add(node.right);
                    result.add(String.valueOf(node.right.value));
                } else {
                    result.add(null);
                }
            }
        }
        return result;
    }

    private static Queue<String> posSerial(Node head) {
        Queue<String> result = new LinkedList<>();
        pos(head, result);
        return result;
    }

    private static void pos(Node head, Queue<String> result) {
        if (head == null) {
            result.add(null);
        } else {
            pos(head.left, result);
            pos(head.right, result);
            result.add(String.valueOf(head.value));
        }
    }


    private static Queue<String> preSerial(Node head) {
        Queue<String> result = new LinkedList<>();
        pre(head, result);
        return result;
    }

    private static void pre(Node head, Queue<String> result) {
        if (head == null) {
            result.add(null);
        } else {
            result.add(String.valueOf(head.value));
            pre(head.left, result);
            pre(head.right, result);
        }
    }

    /**
     * 生成随机二叉树
     *
     * @param maxLevel 最大层数
     * @param maxValue 最大值
     * @param level    当前层
     * @return {@link Node}
     */
    private static Node generateRandomBinaryTree(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generateRandomBinaryTree(level + 1, maxLevel, maxValue);
        head.right = generateRandomBinaryTree(level + 1, maxLevel, maxValue);
        return head;
    }
}
