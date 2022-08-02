package com.chen.algorithm.day18;

import java.util.LinkedList;
import java.util.Queue;

/**
 * code01 判断一个树是否是完全二叉树
 * <img width="640" height="320" src="https://imgconvert.csdnimg.cn/aHR0cDovL2MuYmlhbmNoZW5nLm5ldC91cGxvYWRzL2FsbGltZy8xOTA0MjcvMDk0NTJNYjUtMi5naWY" alt="">
 *
 * @author chenzihan
 * @date 2022/08/02
 */
public class Code01IsCBT {

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            BinaryTree head = BinaryTreeUtil.generateRandomBinaryTree(maxLevel, maxValue);
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    private static boolean isCBT1(BinaryTree head) {
        if (head == null) {
            return true;
        }
        Queue<BinaryTree> queue = new LinkedList<>();
        // 二叉树中是否已经有叶子节点了
        boolean hasNoFullNode = false;
        queue.add(head);
        while (!queue.isEmpty()) {
            BinaryTree node = queue.poll();
            BinaryTree left = node.left;
            BinaryTree right = node.right;
            // 判断不是完全二叉树有以下两种情况
            //情况一：当前 node 有右节点，没有左节点
            boolean case1 = left == null && right != null;
            //情况二：之前已经存在过不双全的节点，但是当前 node 不是叶子节点
            boolean case2 = hasNoFullNode && (left != null || right != null);
            if (case1 || case2) {
                return false;
            }
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
            if (left == null || right == null) {
                hasNoFullNode = true;
            }
        }
        return true;
    }

    private static boolean isCBT2(BinaryTree head) {
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
        boolean isCBT = false;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        // 左树满，右树满，并且左树和右树等高，一定是完全二叉树，因为这是一个满二叉树
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        // 如果当前 node 是满的，那么它一定是一个完全二叉树
        if (isFull) {
            isCBT = true;
        } else {
            // 只有当左树和右树都是完全二叉树时，才能进行一下判断，否则当前节点 node 肯定不是一个完全二叉树
            if (leftInfo.isCBT && rightInfo.isCBT) {
                // 如果当前 node 不是满的，分情况讨论，怎么样才能算作完全二叉树
                // 情况一：右树满，但是左树比右树高 1 层
                if (rightInfo.isFull && (leftInfo.height - rightInfo.height) == 1) {
                    isCBT = true;
                }
                // 情况二：左树满，但是左右同高
                if (leftInfo.isFull && leftInfo.height == rightInfo.height) {
                    isCBT = true;
                }
            }
        }
        return new Info(isCBT, isFull, height);
    }

    static class Info {
        public boolean isCBT;
        public boolean isFull;
        public int height;

        public Info(boolean isCBT, boolean isFull, int height) {
            this.isCBT = isCBT;
            this.isFull = isFull;
            this.height = height;
        }
    }


}
