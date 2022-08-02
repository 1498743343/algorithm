package com.chen.algorithm.day18;

import java.util.ArrayList;
import java.util.List;

/**
 * code02 是否是搜索二叉树-Binary Search Tree
 * 定义：若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值； 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值； 它的左、右子树也分别为二叉排序树
 *
 * @author chenzihan
 * @date 2022/08/02
 */
public class Code02_IsBST {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            BinaryTree head = BinaryTreeUtil.generateRandomBinaryTree(maxLevel, maxValue);
            if (isBST1(head) != isBST2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    /**
     * 方法一：中序遍历一个二叉树，将结果放入到 list 中，如果整个集合是升序排列，那么它就是一个搜索二叉树
     *
     * @param head 头
     * @return boolean
     */
    private static boolean isBST1(BinaryTree head) {
        if (head == null) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        in(head, list);
        boolean isBST = true;
        for (int i = 0; i < list.size(); i++) {
            int num1 = list.get(i);
            if (isBST) {
                for (int j = i + 1; j < list.size(); j++) {
                    int num2 = list.get(j);
                    if (num2 <= num1) {
                        isBST = false;
                        break;
                    }
                }
            }
        }
        return isBST;
    }

    private static void in(BinaryTree head, List<Integer> list) {
        if (head == null) {
            return;
        }
        in(head.left, list);
        list.add(head.value);
        in(head.right, list);
    }

    private static boolean isBST2(BinaryTree head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    private static Info process(BinaryTree head) {
        // 在这个题中，head == null 时，不能给 max 和 min 赋值，因为会影响到对于整体的判断
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int max = head.value;
        int min = head.value;
        boolean leftCase = false;
        boolean rightCase = false;
        // 有左节点
        if (leftInfo != null && leftInfo.isBST && leftInfo.max < head.value) {
            leftCase = true;
            min = leftInfo.min;
        } else if (leftInfo == null) {
            leftCase = true;
        }
        // 有右节点
        if (rightInfo != null && rightInfo.isBST && rightInfo.min > head.value) {
            rightCase = true;
            max = rightInfo.max;
        } else if (rightInfo == null) {
            rightCase = true;
        }
        boolean isBST = leftCase && rightCase;
        return new Info(max, min, isBST);
    }

    static class Info {
        public int max;
        public int min;
        public boolean isBST;

        public Info(int max, int min, boolean isBST) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }
    }


}
