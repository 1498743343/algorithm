package com.chen.algorithm.day6;

import java.util.HashMap;
import java.util.Map;

/**
 * code07
 * 给定两个整数数组 preorder 和 inorder，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * 两个数组中均无重复元素，即 val 都不相等；1 <= preorder.length <= 3000
 * 测试链接：https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 *
 * @author chenzihan
 * @date 2022/05/17
 */
public class Code07ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 先序遍历的第一个一定是头部节点，包装为 node 类型，这就是最后的头部节点
        TreeNode head = new TreeNode(preorder[0]);
        int length = preorder.length;
        if (length == 1) {
            return head;
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            indexMap.put(inorder[i], i);
        }
        Integer headIndex = indexMap.get(head.val);
        head.left = buildTreeInScope(preorder, 1, headIndex, inorder, 0, headIndex - 1, indexMap);
        head.right = buildTreeInScope(preorder, headIndex + 1, length - 1, inorder, headIndex + 1, length - 1, indexMap);
        return head;
    }

    /**
     * 构建树范围
     *
     * @param preorder 前序数组
     * @param preLeft  本次遍历前序数组的左边界
     * @param preRight 本次遍历前序数组的右边界
     * @param inorder  中序数组
     * @param inLeft   本次遍历中序数组的坐边界
     * @param inRight  本次遍历中序数组的右边界
     * @param indexMap 指数图
     * @return {@link TreeNode}
     */
    public static TreeNode buildTreeInScope(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight, Map<Integer, Integer> indexMap) {
        if (preLeft > preRight) {
            return null;
        }
        TreeNode head = new TreeNode(preorder[preLeft]);
        Integer headIndex = indexMap.get(head.val);
        int size = headIndex - inLeft;
        head.left = buildTreeInScope(preorder, preLeft + 1, preLeft + 1 + size, inorder, inLeft, headIndex - 1, indexMap);
        head.right = buildTreeInScope(preorder, preLeft + 2 + size, preRight, inorder, headIndex + 1, inRight, indexMap);
        return head;
    }
}
