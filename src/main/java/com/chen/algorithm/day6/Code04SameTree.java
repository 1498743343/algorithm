package com.chen.algorithm.day6;

/**
 * code04 判断两个二叉树是否相等
 * 测试链接：https://leetcode.cn/problems/same-tree
 *
 * @author chenzihan
 * @date 2022/05/16
 */
public class Code04SameTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // p 和 q 有一个是 null，一个不是 null
        if ((p == null) ^ (q == null)) {
            return false;
        }
        // 这里用 p 和 q 进行判断都可以，因为只要有一个是 null 说明 p 和 q 都是 null
        if (p == null) {
            return true;
        }
        // 当 p 和 q 都不是 null 时，比较 val 和左右节点，递归至结束
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
