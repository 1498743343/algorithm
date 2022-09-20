package com.chen.algorithm.leetcode;

/**
 * code101 leetcode 第101题
 * <a href="https://leetcode.cn/problems/symmetric-tree/">测试链接</a>
 *
 * @author chenzihan
 * @date 2022/09/20
 */
public class Code101 {
    /**
     * 判断是否是对称二叉树、镜像树，即沿头节点对称
     *
     * @param root 根
     * @return boolean
     */
    public boolean isSymmetric(TreeNode root) {
        return process(root.left, root.right);
    }

    public boolean process(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return left.val == right.val && process(left.left, right.right) && process(left.right, right.left);
    }
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }
}
