package com.chen.algorithm.day16;

import java.util.Stack;

/**
 * code03非递归实现遍历二叉树
 * todo 后序遍历待验证
 *
 * @author chenzihan
 * @date 2022/07/05
 */
public class Code03UnRecursiveTraversalBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	public static void pre(Node head) {
		System.out.println("pre-order: ");
		if (head != null) {
			Stack<Node> stack = new Stack<>();
			stack.push(head);
			while (!stack.isEmpty()) {
				Node node = stack.pop();
				System.out.print(node.value + " ");
				if (node.right != null) {
					stack.push(node.right);
				}
				if (node.left != null) {
					stack.push(node.left);
				}
			}
		}
		System.out.println();
	}

	public static void in(Node head) {
		System.out.print("in-order: ");
		if (head != null) {
			Stack<Node> stack = new Stack<>();
			Node current = head;
			while (!stack.isEmpty() || current != null) {
				if (current != null) {
					stack.push(current);
					current = current.left;
				} else {
					current = stack.pop();
					System.out.print(current.value + " ");
					current = current.right;
				}
			}
		}
		System.out.println();
	}

	public static void pos1(Node head) {
		System.out.print("pos-order: ");
		if (head != null) {
			Stack<Node> s1 = new Stack<>();
			Stack<Node> s2 = new Stack<>();
			s1.push(head);
			while (!s1.isEmpty()) {
				head = s1.pop();
				s2.push(head);
				if (head.left != null) {
					s1.push(head.left);
				}
				if (head.right != null) {
					s1.push(head.right);
				}
			}
			while (!s2.isEmpty()) {
				System.out.print(s2.pop().value + " ");
			}
		}
		System.out.println();
	}

	public static void pos2(Node h) {
		System.out.print("pos-order: ");
		if (h != null) {
			Stack<Node> stack = new Stack<>();
			stack.push(h);
			Node c;
			while (!stack.isEmpty()) {
				c = stack.peek();
				if (c.left != null && h != c.left && h != c.right) {
					stack.push(c.left);
				} else if (c.right != null && h != c.right) {
					stack.push(c.right);
				} else {
					System.out.print(stack.pop().value + " ");
					h = c;
				}
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		pre(head);
		System.out.println("========");
		in(head);
		System.out.println("========");
		pos1(head);
		System.out.println("========");
		pos2(head);
		System.out.println("========");
	}

}
