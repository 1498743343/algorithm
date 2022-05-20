package com.chen.algorithm.day7;

import java.util.LinkedList;
import java.util.List;

/**
 * code05路径sum3
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 测试链接：https://leetcode.cn/problems/path-sum-ii
 *
 * @author chenzihan
 * @date 2022/05/20
 */
public class Code05PathSum3 {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }


    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        process(result, path, root, targetSum);
        return result;
    }

    private static void process(List<List<Integer>> result, LinkedList<Integer> path, TreeNode currentNode, int targetSum) {
        if (currentNode == null) {
            return;
        }
        int value  = currentNode.val;
        path.addLast(value);
        // 很多时候递归直接使用 targetSum -= value，或者 targetSum += value 去做判断就可以了，这样可以少使用一个变量 preSum
        targetSum -= value;
        if (currentNode.left == null && currentNode.right == null && targetSum == 0) {
            // 这里必须 new 一个新的 list 添加到 result 中，因为整个递归过程使用的是同一个 path
            result.add(new LinkedList<>(path));
        }
        process(result, path, currentNode.left, targetSum);
        process(result, path, currentNode.right, targetSum);
        path.removeLast();
    }

}
