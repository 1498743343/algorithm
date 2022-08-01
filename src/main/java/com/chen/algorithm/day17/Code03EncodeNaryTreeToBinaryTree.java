package com.chen.algorithm.day17;

import java.util.*;

/**
 * code03 多叉树与二叉树的转换
 * 将当前节点的所有子节点挂在二叉树当前节点的左节点的右侧
 * <p>
 * 本题测试链接：<a href="https://leetcode.cn/problems/encode-n-ary-tree-to-binary-tree">...</a>
 *
 * @author chenzihan
 * @date 2022/07/28
 */
public class Code03EncodeNaryTreeToBinaryTree {

    // 提交时不要提交这个类
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    // 提交时不要提交这个类
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 只提交这个类即可
    static class Codec {

        /**
         * 将多叉树转换为二叉树
         *
         * @param root 根
         * @return {@link TreeNode}
         */
        public TreeNode encode(Node root) {
            return en(root);
        }

        private TreeNode en(Node root) {
            if (root == null) {
                return null;
            }
            TreeNode result = new TreeNode(root.val);
            TreeNode head = null;
            for (Node child : root.children) {
                if (head == null) {
                    head = en(child);
                    result.left = head;
                } else {
                    head.right = en(child);
                    head = head.right;
                }
            }
            return result;
        }

        /**
         * 将二叉树转换为多叉树
         *
         * @param root 根
         * @return {@link Node}
         */
        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }
            return new Node(root.val, de(root.left));
        }

        private List<Node> de(TreeNode head) {
            List<Node> children = new ArrayList<>();
            while (head != null) {
                children.add(new Node(head.val, de(head.left)));
                head = head.right;
            }
            return children;
        }
    }

    public static boolean isEqual(Node node1, Node node2) {
        if (node1 == null && node2 != null) {
            return false;
        }
        if (node1 != null && node2 == null) {
            return false;
        }
        if (node1 == null) {
            return true;
        }
        Queue<Node> queue1 = new LinkedList<>();
        Queue<Node> queue2 = new LinkedList<>();
        queue1.add(node1);
        queue2.add(node2);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            Node poll1 = queue1.poll();
            Node poll2 = queue2.poll();
            if (poll1.val != poll2.val) {
                return false;
            }
            List<Node> children1 = poll1.children;
            queue1.addAll(children1);
            List<Node> children2 = poll2.children;
            queue2.addAll(children2);
        }
        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(1, new ArrayList<>());
        Node child11 = new Node(11, new ArrayList<>());
        Node child12 = new Node(12, new ArrayList<>());
        Node child13 = new Node(13, new ArrayList<>());
        Node child14 = new Node(14, new ArrayList<>());
        Node child121 = new Node(121, new ArrayList<>());
        Node child122 = new Node(122, new ArrayList<>());
        Node child123 = new Node(123, new ArrayList<>());
        Node child141 = new Node(141, new ArrayList<>());
        Node child142 = new Node(142, new ArrayList<>());
        Node child143 = new Node(143, new ArrayList<>());
        Node child1231 = new Node(1231, new ArrayList<>());
        Node child1232 = new Node(1232, new ArrayList<>());
        Node child1233 = new Node(1233, new ArrayList<>());
        Node child1234 = new Node(1234, new ArrayList<>());
        List<Node> children1 = new ArrayList<>();
        children1.add(child11);
        children1.add(child12);
        children1.add(child13);
        children1.add(child14);
        head.children = children1;
        List<Node> children12 = new ArrayList<>();
        children12.add(child121);
        children12.add(child122);
        children12.add(child123);
        List<Node> children14 = new ArrayList<>();
        children14.add(child141);
        children14.add(child142);
        children14.add(child143);
        List<Node> children123 = new ArrayList<>();
        children123.add(child1231);
        children123.add(child1232);
        children123.add(child1233);
        children123.add(child1234);
        child12.children = children12;
        child14.children = children14;
        child123.children = children123;
        Codec codec = new Codec();
        TreeNode treeNode = codec.encode(head);
        Node decode = codec.decode(treeNode);
        System.out.println("isEqual: " + isEqual(head, decode));
    }
}
