package com.chen.algorithm.day17;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * code05二叉树最大宽度
 *
 * @author chenzihan
 * @date 2022/08/01
 */
public class Code05TreeMaxWidth {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static void main(String[] args) {
		int maxLevel = 10;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");

	}

	private static int maxWidthNoMap(Node head) {
		if (head == null) {
			return 0;
		}
		Queue<Node> queue = new LinkedList<>();
		Node currentLevelEnd = head;
		Node nextLevelEnd = null;
		int max = 0;
		int currentLevelNodeNum = 0;
		queue.add(head);
		while (!queue.isEmpty()) {
			Node currentNode = queue.poll();
			if (currentNode.left != null) {
				queue.add(currentNode.left);
				nextLevelEnd = currentNode.left;
			}
			if (currentNode.right != null) {
				queue.add(currentNode.right);
				nextLevelEnd = currentNode.right;
			}
			currentLevelNodeNum ++;
			if (currentNode == currentLevelEnd) {
				max = Math.max(max, currentLevelNodeNum);
				currentLevelEnd = nextLevelEnd;
				currentLevelNodeNum = 0;
			}
		}
		return max;
	}

	private static int maxWidthUseMap(Node head) {
		if (head == null) {
			return 0;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		// key 在 哪一层，value
		HashMap<Node, Integer> levelMap = new HashMap<>();
		levelMap.put(head, 1);
		int curLevel = 1; // 当前你正在统计哪一层的宽度
		int curLevelNodes = 0; // 当前层curLevel层，宽度目前是多少
		int max = 0;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int curNodeLevel = levelMap.get(cur);
			if (cur.left != null) {
				levelMap.put(cur.left, curNodeLevel + 1);
				queue.add(cur.left);
			}
			if (cur.right != null) {
				levelMap.put(cur.right, curNodeLevel + 1);
				queue.add(cur.right);
			}
			if (curNodeLevel == curLevel) {
				curLevelNodes++;
			} else {
				max = Math.max(max, curLevelNodes);
				curLevel++;
				curLevelNodes = 1;
			}
		}
		max = Math.max(max, curLevelNodes);
		return max;
	}

	private static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	private static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

}
