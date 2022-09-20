package com.chen.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * code105 leetcode 第105题
 * <a href="https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/">测试链接</a>
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 * @author chenzihan
 * @date 2022/09/20
 */
public class Code105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 建立关于中序遍历的反向索引
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return process(preorder, 0, n - 1, inorder, 0, n - 1, map);
    }

    public TreeNode process(int[] pre, int preLeft, int preRight, int[] in, int inLeft, int inRight, Map<Integer, Integer> map) {
        if (preLeft > preRight) {
            return null;
        }
        // 先序遍历的第一个 pre[preLeft] 就是当前头部节点的值
        TreeNode head = new TreeNode(pre[preLeft]);
        // 找到头节点在中序遍历中的位置，这样 index - inLeft 就是左树包含的节点个数
        int index = map.get(pre[preLeft]);
        // preLeft + 1 就是左树的起始位置， preLeft + (index - inLeft)就是左树的结束位置
        head.left = process(pre, preLeft + 1, preLeft + index - inLeft, in, inLeft, index - 1, map);
        // preLeft + (index - inLeft) + 1就是右树的起始位置
        head.right = process(pre, preLeft + index - inLeft + 1, preRight, in, index + 1, inRight, map);
        return head;
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
