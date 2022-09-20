package com.chen.algorithm.leetcode;

/**
 * code104 leetcode 第104题
 * <a href="https://leetcode.cn/problems/maximum-depth-of-binary-tree/?favorite=2cktkvj">测试链接</a>
 * 求解二叉树最大深度
 *
 * @author chenzihan
 * @date 2022/09/20
 */
public class Code104 {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }
}
