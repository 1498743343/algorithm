package com.chen.algorithm.day19;

import com.chen.algorithm.day18.BinaryTree;
import com.chen.algorithm.day18.BinaryTreeUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * code02
 * 给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的头节点
 * 找个类整理的方法要比 day18 中整理的相似问题的解法要更加好记一些
 *
 * @author chenzihan
 * @date 2022/08/04
 */
public class Code02MaxSubBSTHead {

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            BinaryTree head = BinaryTreeUtil.generateRandomBinaryTree(maxLevel, maxValue);
            if (maxSubBSTHead(head) != test(head)) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("finish!");
    }

    private static BinaryTree maxSubBSTHead(BinaryTree head) {
        if (head == null) {
            return null;
        }
        return process(head).node;
    }

    private static Info process(BinaryTree head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int min = head.value;
        int max = head.value;
        int maxSize = 0;
        BinaryTree node = null;
        // 有左设置左
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
            maxSize = leftInfo.maxSize;
            node = leftInfo.node;
        }
        // 有右设置右
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            // 需要和可能被左节点设置过的值进行比较
            if (rightInfo.maxSize > maxSize) {
                maxSize = rightInfo.maxSize;
                node = rightInfo.node;
            }
        }
        // 如果左右都满足时，更新为自己
        if ((leftInfo == null || (leftInfo.node == head.left && leftInfo.max < head.value))
                && (rightInfo == null || (rightInfo.node == head.right && rightInfo.min > head.value))) {
            node = head;
            maxSize = (leftInfo == null ? 0 : leftInfo.maxSize) + (rightInfo == null ? 0 : rightInfo.maxSize) + 1;
        }
        return new Info(min, max, maxSize, node);
    }

    static class Info {
        public int min;
        public int max;
        public int maxSize;
        public BinaryTree node;

        public Info(int min, int max, int maxSize, BinaryTree node) {
            this.min = min;
            this.max = max;
            this.maxSize = maxSize;
            this.node = node;
        }
    }

    /**
     * 中序遍历二叉树，如果满足升序结构就是一个二叉搜索树
     *
     * @param head 头
     * @return {@link BinaryTree}
     */
    private static BinaryTree test(BinaryTree head) {
        if (head == null) {
            return null;
        }
        // 先判断我是不是BST
        if (getSize(head) != 0) {
            return head;
        }
        // 我不是BST时，判断 left 和 right 哪个更大
        BinaryTree left = test(head.left);
        BinaryTree right = test(head.right);
        return getSize(right) > getSize(left) ? right : left;
    }

    private static int getSize(BinaryTree head) {
        if (head == null) {
            return 0;
        }
        List<BinaryTree> list = new ArrayList<>();
        in(head, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).value >= list.get(i).value) {
                return 0;
            }
        }
        return list.size();
    }

    private static void in(BinaryTree head, List<BinaryTree> list) {
        if (head != null) {
            in(head.left, list);
            list.add(head);
            in(head.right, list);
        }
    }
}
