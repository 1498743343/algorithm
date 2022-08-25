package com.chen.algorithm.day23;

import java.util.Stack;

/**
 * code04 使用递归将栈逆序
 *
 * @author chenzihan
 * @date 2022/08/25
 */
public class Code04ReverseStackUsingRecursive {

	public static void main(String[] args) {
		Stack<Integer> test = new Stack<>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}

	}

	/**
	 * 拿到栈底元素，保存在当前的栈帧中，当栈被拿空以后，依次往回填，这样原来的栈底元素就到了栈顶
	 *
	 * @param stack 栈
	 */
	private static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		Integer stackBottom = getBottom(stack);
		reverse(stack);
		stack.push(stackBottom);
	}

	/**
	 * 将栈底元素抽出并返回，将其他元素按照原来的顺序放回去
	 *
	 * @param stack 堆栈
	 * @return {@link Integer}
	 */
	private static Integer getBottom(Stack<Integer> stack) {
		Integer pop = stack.pop();
		if (stack.isEmpty()) {
			return pop;
		}
		Integer result = getBottom(stack);
		stack.push(pop);
		return result;
	}
}
