package com.chen.algorithm.day23;

import java.util.Stack;

/**
 * code01 汉诺塔
 * 一个 n 层的塔，从下到上的元素按照从大到小排列，即下面的最大，上面的最小
 * 已知有左、中、右三个位置，大的元素不能放在小的元素上方，n 层的塔位于左侧，请将它移动到右侧
 *
 * @author chenzihan
 * @date 2022/08/24
 */
public class Code01Hanoi {
    public static void main(String[] args) {
        int n = 4;
        hanoi1(n);
        System.out.println("==================================");
        hanoi2(n);
    }

    /**
     * 方法一：递归实现
     *
     * @param n n
     */
    public static void hanoi1(int n) {
        process(n, "left", "right", "mid");
    }

    public static void process(int cur, String from, String to, String other) {
        if (cur == 1) {
            System.out.println("move " + cur + " from " + from + " to " + to);
            return;
        }
        process(cur - 1, from, other, to);
        System.out.println("move " + cur + " from " + from + " to " + to);
        process(cur - 1, other, to, from);
    }

    public static void hanoi2(int n) {
        if (n < 1) {
            return;
        }
        Stack<Record> stack = new Stack<>();
        stack.push(new Record(false, n, "left", "right", "mid"));
        while (!stack.isEmpty()) {
            Record pop = stack.pop();
            if (pop.value == 1) {
                System.out.println("move " + pop.value + " from " + pop.from + " to " + pop.to);
                if (!stack.isEmpty()) {
                    stack.peek().noNodeOnMe = true;
                }
            } else {
                if (!pop.noNodeOnMe) {
                    stack.push(pop);
                    stack.push(new Record(false, pop.value - 1, pop.from, pop.other, pop.to));
                } else {
                    System.out.println("move " + pop.value + " from " + pop.from + " to " + pop.to);
                    stack.push(new Record(false, pop.value - 1, pop.other, pop.to, pop.from));
                }
            }
        }
    }

    static class Record {
        public boolean noNodeOnMe;
        public int value;
        public String from;
        public String to;
        public String other;

        public Record(boolean noNodeOnMe, int value, String from, String to, String other) {
            this.noNodeOnMe = noNodeOnMe;
            this.value = value;
            this.from = from;
            this.to = to;
            this.other = other;
        }
    }
}
