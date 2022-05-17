package com.chen.algorithm.day6;

/**
 * code06 二叉树最大深度
 * 给定一个二叉树，返回二叉树的最大深度
 *
 * 测试链接：https://leetcode.cn/problems/maximum-depth-of-binary-tree
 *
 * @author chenzihan
 * @date 2022/05/17
 */
public class Code06MaximumDepthOfBinaryTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    /**
     * 最大深度
     * 递归调用本方法，当当前节点的 left 和 right 都为 null 时，返回自己的深度 1，通过 Math.max 方法将左右节点中的最大值返回，这就是最大深度
     *
     * @param root 根
     * @return int
     */
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
