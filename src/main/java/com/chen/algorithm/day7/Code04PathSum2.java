package com.chen.algorithm.day7;

/**
 * code04路径和2
 *
 * 测试链接：https://leetcode.cn/problems/path-sum
 *
 * @author chenzihan
 * @date 2022/05/19
 */
public class Code04PathSum2 {

	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	/**
	 * 有路径和
	 *
	 * @param root      根
	 * @param targetSum 目标和
	 * @return boolean
	 */
	public static boolean hasPathSum(TreeNode root, int targetSum) {
		if (root == null) {
			return false;
		}
		if (root.left == null && root.right == null) {
			return targetSum == root.val;
		}
		return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
	}
}
