package com.chen.algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * code102 leetcode 第102题
 * <a href="https://leetcode.cn/problems/binary-tree-level-order-traversal/">测试链接</a>
 * 二叉树的按层遍历
 *
 * @author chenzihan
 * @date 2022/09/20
 */
public class Code102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        // 设置本次的 end 标识 和下次的 end 标识
        TreeNode end = root;
        TreeNode nextEnd = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            list.add(cur.val);
            // 每次在队列中添加元素都更新 nextEnd
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            // 当走到本层的最后一个结点时，结算，并更新 end
            if (cur == end) {
                ans.add(list);
                list = new ArrayList<>();
                end = nextEnd;
            }
        }
        return ans;
    }
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }
}
