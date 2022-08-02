package com.chen.algorithm.day7;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * code01二叉树层次遍历
 * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * 测试链接：https://leetcode.cn/problems/binary-tree-level-order-traversal-ii
 *
 * @author chenzihan
 * @date 2022/05/19
 */
public class Code01BinaryTreeLevelOrderTraversal {

	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> result = new LinkedList<>();
		if (root == null) {
			return result;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int size = queue.size();
		while (!queue.isEmpty()) {
			List<Integer> list = new LinkedList<>();
			for (int i = 0; i < size; i++) {
				TreeNode currentNode = queue.poll();
				list.add(currentNode.val);
				if (currentNode.left != null) {
					queue.offer(currentNode.left);
				}
				if (currentNode.right != null) {
					queue.offer(currentNode.right);
				}
			}
			size = queue.size();
			result.add(0,list);
		}
		return result;
	}

}
