package com.chen.algorithm.day6;

/**
 * code05 判断两个二叉树是否是镜像树
 * 测试链接：https://leetcode.cn/problems/symmetric-tree
 *
 * @author chenzihan
 * @date 2022/05/17
 */
public class Code05SymmetricTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return twoNodesIsSymmetric(root.left, root.right);
    }

    /**
     * 两个节点是对称
     *
     * @param left  左
     * @param right 右
     * @return boolean
     */
    public static boolean twoNodesIsSymmetric(TreeNode left, TreeNode right) {
        if (left == null ^ right == null) {
            return false;
        }
        if (left == null) {
            return true;
        }
        return left.val == right.val && twoNodesIsSymmetric(left.left, right.right) && twoNodesIsSymmetric(left.right, right.left);
    }
}
