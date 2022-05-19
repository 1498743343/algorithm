package com.chen.algorithm.day7;

/**
 * code02平衡二叉树
 * 判断一个二叉树是否是平衡二叉树。平衡二叉树定义：|leftMaxDepth - rightMaxDepth| <=1
 * 解题思路：判断是否是平衡二叉树，从叶子节点开始往上，只要每个节点都满足是平衡二叉树，那么传入的 root 节点就是平衡二叉树，所以使用递归是一个不错的方法。
 * 我们判断是否是平衡二叉树需要四个条件：1.左树是否是平衡二叉树 2.左树最大深度 3.右树是否是平衡二叉树 4.右树最大深度。这样我们就能判断当前节点下是否是平衡二叉树。
 * 因为我们使用递归解题，所以创建一个类，让他包含 最大深度和是否平衡 这两个信息就可以了，因为递归可以分别拿到左树和右树的信息
 * <p>
 * 测试链接：https://leetcode.cn/problems/balanced-binary-tree
 *
 * @author chenzihan
 * @date 2022/05/19
 */
public class Code02BalancedBinaryTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info {
        public int maxDepth;
        public boolean isBalance;

        public Info(int maxDepth, boolean isBalance) {
            this.maxDepth = maxDepth;
            this.isBalance = isBalance;
        }
    }

    public boolean isBalanced(TreeNode root) {
        return getInfo(root).isBalance;
    }

    public static Info getInfo(TreeNode root) {
        if (root == null) {
            return new Info(0, true);
        }
        Info leftInfo = getInfo(root.left);
        Info rightInfo = getInfo(root.right);
        boolean isBalance = leftInfo.isBalance && rightInfo.isBalance && ((Math.abs(leftInfo.maxDepth - rightInfo.maxDepth) <= 1));
        int maxDepth = Math.max(leftInfo.maxDepth, rightInfo.maxDepth) + 1;
        return new Info(maxDepth, isBalance);
    }
}
