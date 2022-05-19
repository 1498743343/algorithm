package com.chen.algorithm.day7;

/**
 * code03路径和
 *
 * 测试链接：https://leetcode.cn/problems/path-sum
 *
 * @author chenzihan
 * @date 2022/05/19
 */
public class Code03PathSum {

	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public static boolean hasPath = false;

	public static boolean hasPathSum(TreeNode root, int targetSum) {
		if (root == null) {
			return hasPath;
		}
		// 设置的是 static 变量，需要每次重置
		hasPath = false;
		sum(root, 0, targetSum);
		return hasPath;
	}

	// 递归求和，遍历所有叶子节点，求出 root 节点到每个叶子节点的和，如果 sum == targetSum 就将 hasPath 设置为 true
	public static void sum(TreeNode root, int preSum, int targetSum){
		if(root==null){
			return;
		}
		int value = root.val;
		if(root.left == null && root.right == null){
			int sum = preSum + value;
			if(sum == targetSum){
				hasPath = true;
			}
			return;
		}
		sum(root.left, preSum + value, targetSum);
		sum(root.right, preSum + value, targetSum);
	}

	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		node1.left = node2;
		node1.right = node3;
		System.out.println(hasPathSum(node1,5));
	}
}
