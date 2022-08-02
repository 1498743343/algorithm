package com.chen.algorithm.day18;

/**
 * 二叉树跑工具类
 *
 * @author chenzihan
 * @date 2022/08/02
 */
public class BinaryTreeUtil {
    /**
     * 生成随机二叉树
     *
     * @param maxLevel 最大层数
     * @param maxValue 最大值
     * @return {@link BinaryTree}
     */
    public static BinaryTree generateRandomBinaryTree(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    /**
     * 生成
     *
     * @param currentLevel 当前层数
     * @param maxLevel     最大层数
     * @param maxValue     最大值
     * @return {@link BinaryTree}
     */
    private static BinaryTree generate(int currentLevel, int maxLevel, int maxValue) {
        if (currentLevel > maxLevel || Math.random() < 0.5) {
            return null;
        }
        int value = (int) (Math.random() * maxValue);
        BinaryTree head = new BinaryTree(value);
        head.left = generate(currentLevel + 1, maxLevel, maxValue);
        head.right = generate(currentLevel + 1, maxLevel, maxValue);
        return head;
    }

}
