package com.chen.algorithm.day18;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * code06最大距离
 *
 * @author chenzihan
 * @date 2022/08/03
 */
public class Code06MaxDistance {

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			BinaryTree head = BinaryTreeUtil.generateRandomBinaryTree(maxLevel, maxValue);
			if (maxDistance1(head) != maxDistance2(head)) {
				System.out.println("Oops!");
				break;
			}
		}
		System.out.println("finish!");
	}

	private static int maxDistance1(BinaryTree head) {
		if (head == null) {
			return 0;
		}
		return process(head).maxDistance;
	}

	private static Info process(BinaryTree head) {
		if (head == null) {
			return new Info(0, 0);
		}
		Info leftInfo = process(head.left);
		Info rightInfo = process(head.right);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		int maxDistance = Math.max(leftInfo.maxDistance, Math.max(rightInfo.maxDistance, leftInfo.height + rightInfo.height + 1));
		return new Info(maxDistance, height);
	}

	static class Info{
		public int maxDistance;
		public int height;

		public Info(int maxDistance, int height) {
			this.maxDistance = maxDistance;
			this.height = height;
		}
	}

	public static int maxDistance2(BinaryTree head) {
		if (head == null) {
			return 0;
		}
		ArrayList<BinaryTree> arr = getPrelist(head);
		HashMap<BinaryTree, BinaryTree> parentMap = getParentMap(head);
		int max = 0;
		for (int i = 0; i < arr.size(); i++) {
			for (int j = i; j < arr.size(); j++) {
				max = Math.max(max, distance(parentMap, arr.get(i), arr.get(j)));
			}
		}
		return max;
	}

	public static ArrayList<BinaryTree> getPrelist(BinaryTree head) {
		ArrayList<BinaryTree> arr = new ArrayList<>();
		fillPrelist(head, arr);
		return arr;
	}

	public static void fillPrelist(BinaryTree head, ArrayList<BinaryTree> arr) {
		if (head == null) {
			return;
		}
		arr.add(head);
		fillPrelist(head.left, arr);
		fillPrelist(head.right, arr);
	}

	public static HashMap<BinaryTree, BinaryTree> getParentMap(BinaryTree head) {
		HashMap<BinaryTree, BinaryTree> map = new HashMap<>();
		map.put(head, null);
		fillParentMap(head, map);
		return map;
	}

	public static void fillParentMap(BinaryTree head, HashMap<BinaryTree, BinaryTree> parentMap) {
		if (head.left != null) {
			parentMap.put(head.left, head);
			fillParentMap(head.left, parentMap);
		}
		if (head.right != null) {
			parentMap.put(head.right, head);
			fillParentMap(head.right, parentMap);
		}
	}

	public static int distance(HashMap<BinaryTree, BinaryTree> parentMap, BinaryTree o1, BinaryTree o2) {
		HashSet<BinaryTree> o1Set = new HashSet<>();
		BinaryTree cur = o1;
		o1Set.add(cur);
		while (parentMap.get(cur) != null) {
			cur = parentMap.get(cur);
			o1Set.add(cur);
		}
		cur = o2;
		while (!o1Set.contains(cur)) {
			cur = parentMap.get(cur);
		}
		BinaryTree lowestAncestor = cur;
		cur = o1;
		int distance1 = 1;
		while (cur != lowestAncestor) {
			cur = parentMap.get(cur);
			distance1++;
		}
		cur = o2;
		int distance2 = 1;
		while (cur != lowestAncestor) {
			cur = parentMap.get(cur);
			distance2++;
		}
		return distance1 + distance2 - 1;
	}

}
