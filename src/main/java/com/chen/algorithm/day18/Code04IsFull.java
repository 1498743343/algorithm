package com.chen.algorithm.day18;

/**
 * code04 判断二叉树是否是满二叉树
 * 满二叉树定义：size = 2^height -1;左右高度相等
 *
 * @author chenzihan
 * @date 2022/08/02
 */
public class Code04IsFull {

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            BinaryTree head = BinaryTreeUtil.generateRandomBinaryTree(maxLevel, maxValue);
            if (isFull1(head) != isFull2(head)) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }

    private static boolean isFull1(BinaryTree head) {
        if (head == null) {
            return true;
        }
        Info1 info1 = process1(head);
        return info1.size == (1 << info1.height) - 1;
    }

    private static Info1 process1(BinaryTree head) {
        if (head == null) {
            return new Info1(0, 0);
        }
        Info1 leftInfo = process1(head.left);
        Info1 rightInfo = process1(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int size = leftInfo.size + rightInfo.size + 1;
        return new Info1(height, size);
    }

    private static boolean isFull2(BinaryTree head) {
        if (head == null) {
            return true;
        }
        Info2 info2 = process2(head);
        return info2.isFull;
    }

    private static Info2 process2(BinaryTree head) {
        if (head == null) {
            return new Info2(0, true);
        }
        Info2 leftInfo = process2(head.left);
        Info2 rightInfo = process2(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        return new Info2(height, isFull);
    }


    static class Info1{
        public int height;
        public int size;

        public Info1(int height, int size) {
            this.height = height;
            this.size = size;
        }
    }

    static class Info2{
        public int height;
        public boolean isFull;

        public Info2(int height, boolean isFull) {
            this.height = height;
            this.isFull = isFull;
        }
    }
}
