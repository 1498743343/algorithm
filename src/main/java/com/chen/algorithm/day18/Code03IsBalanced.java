package com.chen.algorithm.day18;

/**
 * code03 判断二叉树是否平衡，不是判断二叉树是否是平衡二叉树
 * 左右子树高度差不超过1就认为平衡
 *
 * @author chenzihan
 * @date 2022/08/02
 */
public class Code03IsBalanced {


    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            BinaryTree head = BinaryTreeUtil.generateRandomBinaryTree(maxLevel, maxValue);
            if (isBalanced(head) != test(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    private static boolean isBalanced(BinaryTree head) {
        if (head == null) {
            return true;
        }
        return process(head).isBalanced;
    }

    private static Info process(BinaryTree head) {
        if (head == null) {
            return new Info(0, true);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.height - rightInfo.height) <= 1;
        return new Info(height, isBalanced);
    }

    private static boolean test(BinaryTree head) {
        boolean[] arr = {true};
        pos(head, arr);
        return arr[0];
    }

    private static int pos(BinaryTree head,boolean[] arr) {
        if (head == null) {
            return 0;
        }
        int leftHeight = pos(head.left, arr);
        int rightHeight = pos(head.right, arr);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            arr[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    static class Info {
        public int height;
        public boolean isBalanced;

        public Info(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }
}
