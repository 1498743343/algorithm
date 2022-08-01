package com.chen.algorithm.day17;

/**
 * code07折纸
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。
 * 如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。 请从上到下打印所有折痕的方向。
 * <p>
 * 思路解析：此问题可以用二叉树进行模拟，n 可以认为是二叉树的层数，每一层中，左节点是 凹，右节点是 凸，然后中序遍历二叉树就是结果
 *
 * @author chenzihan
 * @date 2022/08/01
 */
public class Code07PaperFolding {

    public static void printAllFolds(int n) {
        process(1, n, true);
    }

    public static void process(int i, int n, boolean down) {
        if (i > n) {
            return;
        }
        process(i + 1, n, true);
        System.out.println(down ? "凹" : "凸");
        process(i + 1, n, false);
    }

    public static void main(String[] args) {
        int n = 4;
        printAllFolds(n);
    }
}
