package com.chen.algorithm.leetcode;

/**
 * code124 leetcode 第124题
 * <a href="https://leetcode.cn/problems/binary-tree-maximum-path-sum/">测试链接</a>
 * 求解 二叉树中的最大路径和
 *
 * @author chenzihan
 * @date 2022/09/20
 */
public class Code124 {
    /**
     * ans 必须是这个值，如果设置为0，当 root.val < 0 时，回返回 0，这样就不对了，例如二叉树只有一个节点，value == -1，此时会返回 0
     */
    public int ans = Integer.MAX_VALUE;

    /**
     * 思路：最大路径和一定是以某个节点为头的路径，所以我们只需要遍历一遍二叉树，把经过每个头的最大路径和都求出来，就能知道 ans
     *
     * @param root 根
     * @return int
     */
    public int maxPathSum(TreeNode root) {
        process(root);
        return ans;
    }

    public int process(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(process(root.left), 0);
        int right = Math.max(process(root.right), 0);
        // 比较，看看当前节点的最大路径和能不能更新 ans
        ans = Math.max(ans, root.val + left + right);
        // 我返回的不能时 root.val + left + right，只能返回一半，否则上层节点没办法组织过他自己的路径和
        return root.val + Math.max(left, right);
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
