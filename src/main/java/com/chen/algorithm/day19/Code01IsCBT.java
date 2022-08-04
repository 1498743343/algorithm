package com.chen.algorithm.day19;

import com.chen.algorithm.day18.BinaryTree;
import com.chen.algorithm.day18.BinaryTreeUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * code01 判断二叉树是否是完全二叉树
 *
 * @author chenzihan
 * @date 2022/08/04
 */
public class Code01IsCBT {
    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int tryTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < tryTimes; i++) {
            BinaryTree head = BinaryTreeUtil.generateRandomBinaryTree(maxLevel, maxValue);
            if (isCBT(head) != test(head)) {
                System.out.println("oops");
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static boolean isCBT(BinaryTree head) {
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }

    private static Info process(BinaryTree head) {
        if (head == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isCBT = false;
        if (isFull) {
            isCBT = true;
        } else if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
            isCBT = true;
        } else if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
            isCBT = true;
        }
        return new Info(isFull, isCBT, height);
    }

    private static boolean test(BinaryTree head) {
        if (head == null) {
            return true;
        }
        Queue<BinaryTree> queue = new LinkedList<>();
        boolean flag = false;
        queue.add(head);
        while (!queue.isEmpty()) {
            BinaryTree node = queue.poll();
            BinaryTree left = node.left;
            BinaryTree right = node.right;
            // 1、前面已经有非双全节点，后面还有非叶子节点
            // 2、没有左节点，有右节点
            if ((flag && (left != null || right != null)) || (left == null && right != null)) {
                return false;
            }
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
            if (left == null || right == null) {
                flag = true;
            }
        }
        return flag;
    }


    static class Info {
        public boolean isFull;
        public boolean isCBT;
        public int height;

        public Info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }
}
