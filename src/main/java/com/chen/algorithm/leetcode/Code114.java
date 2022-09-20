package com.chen.algorithm.leetcode;

/**
 * code114 leetcode 第114题
 * <a href="https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/submissions/">测试链接</a>
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *
 * @author chenzihan
 * @date 2022/09/20
 */
public class Code114 {
    /**
     * 我认为这个函数就可以把 root 变为目标的样子，所以递归调用即可
     *
     * @param root 根
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        // 先把左节点整理好
        flatten(root.left);
        // 记录好右节点，把整理好的左节点挂在 root 的右侧，这样就已经整理好了 root  和 root.left
        TreeNode right = root.right;
        root.right = root.left;
        root.left = null;
        // root 走到当前的最右侧，即 root.left 的最后一个节点，为的是可以把整理好的 root.right 挂在上面
        while (root.right != null) {
            root = root.right;
        }
        // 整理 root.right
        flatten(right);
        root.right = right;
    }

    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
