package com.chen.algorithm.day6;

/**
 * code03二叉树
 *
 * 通过观察 printPre、printIn、printAfter 三个函数可以发现一个很有意思的现象，同样是递归遍历二叉树，因为打印的位置不同导致输出的顺序有很大的变化，
 * 这里我们来分析一下整个递归的过程，以 main 方法中的二叉树为例：
 * 1 -> 2 -> 4 -> 4 -> 4 -> 2 -> 5 -> 5 -> 5 -> 2 -> 1 -> 3 -> 6 -> 6 -> 6 -> 3 -> 7 -> 7 -> 7 -> 3 -> 1
 * 由上面的顺序可知，每个节点都过了 3 次，虽然 4、5、6、7 四个节点的 left、right 都是 null，但也是实打实的进入了一次递归，所以每个节点的 print 方法
 * 都被调用了 3 次，这 3 次可以满足前序、中序、后序遍历的所有情况，所以才可以稍微改动打印方法就做到了完全不同的打印顺序
 *
 * @author chenzihan
 * @date 2022/05/16
 */
public class Code03BinaryTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 前序遍历二叉树
     * 对于每一个节点，先打印本身，再打印 left 节点，最后打印 right 节点
     *
     * @param head 头
     */
    public static void printPre(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.println(head.val);
        printPre(head.left);
        printPre(head.right);
    }

    /**
     * 中序遍历二叉树
     * 对于每一个节点，先打印 left 节点，再打印本身，最后打印 right 节点
     *
     * @param head 头
     */
    public static void printIn(TreeNode head) {
        if (head == null) {
            return;
        }
        printIn(head.left);
        System.out.println(head.val);
        printIn(head.right);
    }

    /**
     * 后序遍历二叉树
     * 对于每一个节点，先打印 left 节点，再打印打印 right 节点，最后打印本身
     *
     * @param head 头
     */
    public static void printAfter(TreeNode head) {
        if (head == null) {
            return;
        }
        printAfter(head.left);
        printAfter(head.right);
        System.out.println(head.val);
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;
        printPre(treeNode1);
        System.out.println("=====================");
        printIn(treeNode1);
        System.out.println("=====================");
        printAfter(treeNode1);
    }
}
