package com.chen.algorithm.day18;

import java.util.ArrayList;

/**
 * code05 求当前节点下，最大搜索二叉树的size
 * 解题思路：可以使用递归二叉树的套路求解。需要的信息有：
 * max - 当前节点下的最大值
 * min - 当前节点下的最小值
 * maxSize - 当前节点下满足搜索二叉树的节点包含的最大节点个数
 * isBST - 当前节点是否是搜索二叉树
 * <p>
 * 在线测试链接 : <a href="https://leetcode.cn/problems/largest-bst-subtree">...</a>
 *
 * @author chenzihan
 * @date 2022/08/02
 */
public class Code05MaxSubBSTSize {

    // 提交时不要提交这个类
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            val = value;
        }
    }

    // 提交如下的largestBSTSubtree方法，可以直接通过
    public static int largestBSTSubtree(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxSize;
    }

    public static Info process(TreeNode head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int max = head.val;
        int min = head.val;
        boolean leftCase = false;
        boolean rightCase = false;
        int leftSize = 0;
        int rightSize = 0;
        // 有左节点
        if (leftInfo != null) {
            // 如果左节点是BST，并且当前节点加入以后满足左半部分是一个BST
            if (leftInfo.isBST && leftInfo.max < head.val) {
                leftCase = true;
            }
            min = Math.min(min, leftInfo.min);
            leftSize = leftInfo.maxSize;
        } else {
            leftCase = true;
        }
        // 有右节点
        if (rightInfo != null) {
            // 如果右节点是BST，并且当前节点加入以后满足右半部分是一个BST
            if (rightInfo.isBST && rightInfo.min > head.val) {
                rightCase = true;
            }
            max = Math.max(max, rightInfo.max);
            rightSize = rightInfo.maxSize;
        } else {
            rightCase = true;
        }
        boolean isBST = false;
        int maxSize;
        if (leftCase && rightCase) {
            isBST = true;
            maxSize = leftSize + rightSize + 1;
        } else {
            maxSize = Math.max(leftSize, Math.max(1, rightSize));
        }
        return new Info(max, min, maxSize, isBST);
    }

    public static class Info {
        public int max;
        public int min;
        public int maxSize;
        public boolean isBST;

        public Info(int max, int min, int maxSize, boolean isBST) {
            this.max = max;
            this.min = min;
            this.maxSize = maxSize;
            this.isBST = isBST;
        }
    }

    // 为了验证
    // 对数器方法
    public static int right(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(right(head.left), right(head.right));
    }

    // 为了验证
    // 对数器方法
    public static int getBSTSize(TreeNode head) {
        if (head == null) {
            return 0;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).val <= arr.get(i - 1).val) {
                return 0;
            }
        }
        return arr.size();
    }

    // 为了验证
    // 对数器方法
    public static void in(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    // 为了验证
    // 对数器方法
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // 为了验证
    // 对数器方法
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (largestBSTSubtree(head) != right(head)) {
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
