package com.chen.algorithm.leetcode;

/**
 * code98 leetcode 第98题
 * <a href="https://leetcode.cn/problems/validate-binary-search-tree/">测试链接</a>
 *
 * @author chenzihan
 * @date 2022/09/20
 */
public class Code98 {

    long pre = Long.MIN_VALUE;

    /**
     * 最优解：注意 pre 是 long 类型的，因为如果 root.val == Integer.MIN_VALUE时，pre 也是这个值，就会让 root.val == pre，return false
     * 这个解依赖的是中序遍历二叉搜索树，值是递增的
     *
     * @param root 根
     * @return boolean
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST(root.left)) {
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        // 访问右子树
        return isValidBST(root.right);
    }
    /**
     * 递归 + 信息收集
     *
     * @param root 根
     * @return boolean
     */
    public boolean isValidBST1(TreeNode root) {
        return process(root).isBST;
    }

    public Info process(TreeNode head){
        if(head == null){
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        boolean leftCase;
        boolean rightCase;
        int max = head.val;
        int min = head.val;
        if(leftInfo != null){
            leftCase = leftInfo.isBST && min > leftInfo.max;
            min = Math.min(min, leftInfo.min);
        }else{
            leftCase = true;
        }

        if(rightInfo != null){
            rightCase = rightInfo.isBST && max < rightInfo.min;
            max = Math.max(max, rightInfo.max);
        }else{
            rightCase = true;
        }
        return new Info(min, max, leftCase && rightCase);
    }

    static class Info{
        public int min;
        public int max;
        public boolean isBST;
        public Info(int min, int max, boolean isBST){
            this.min = min;
            this.max = max;
            this.isBST = isBST;
        }
    }
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }
}
