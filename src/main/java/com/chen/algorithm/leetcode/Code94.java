package com.chen.algorithm.leetcode;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * code94 leetcode 第94题
 * <a href="https://leetcode.cn/problems/binary-tree-inorder-traversal/?favorite=2cktkvj">测试链接</a>
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 *
 * @author chenzihan
 * @date 2022/09/20
 */
public class Code94 {
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                ans.add(cur.val);
                cur = cur.right;
            }
        }
        return ans;
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        process(root, ans);
        return ans;
    }

    private void process(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        process(root.left, ans);
        ans.add(root.val);
        process(root.right, ans);
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }
}
