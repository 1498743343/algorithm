package com.chen.algorithm.day27;

/**
 * code01最小路径和
 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 *
 * @author chenzihan
 * @date 2022/08/31
 */
public class Code01MinPathSum {
    public static void main(String[] args) {
        int rowSize = 10;
        int colSize = 10;
        int[][] array = generateRandomMatrix(rowSize, colSize);
        System.out.println(minPathSum1(array));
        System.out.println(minPathSum2(array));
        System.out.println(minPathSum3(array));
    }

    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }

    /**
     * 暴力递归方法
     *
     * @param array 数组
     * @return int
     */
    private static int minPathSum1(int[][] array) {
        return process(array, 0, 0);
    }

    /**
     * 递归过程
     * 返回从 (x,y) 位置到右下角的最小路径和
     *
     * @param array 数组
     * @param x     x
     * @param y     y
     * @return int
     */
    private static int process(int[][] array, int x, int y) {
        int row = array.length;
        int col = array[0].length;
        // base case：不能越界
        if (x < 0 || x >= row || y < 0 || y >= col) {
            return Integer.MAX_VALUE;
        }
        // base case：到达指定位置
        if (x == row - 1 && y == col - 1) {
            return array[x][y];
        }
        // 对于当前任意一个点，只有两种情况：向右走、向下走
        // 向右走
        int p1 = process(array, x, y + 1);
        // 向下走
        int p2 = process(array, x + 1, y);
        return Math.min(p1, p2) + array[x][y];
    }

    /**
     * 动态规划一：二维数组
     * 根据暴力方法可知，对于任意一个点，他的结果和两个点有关：右边的点，下边的点，总体和两个变量有关：x , y
     * 综上建立二维数组解决
     *
     * @param array 数组
     * @return int
     */
    private static int minPathSum2(int[][] array) {
        if (array == null || array.length == 0 || array[0] == null || array[0].length == 0) {
            return 0;
        }
        int row = array.length;
        int col = array[0].length;
        int[][] dp = new int[row][col];
        // 当 x == row -1 && y == col - 1时，说明已经在目标点了
        dp[row - 1][col - 1] = array[row - 1][col - 1];
        // 最后一行，我的值等于右边的值加上 array 中当前位置的值
        for (int y = col - 2; y >= 0; y--) {
            dp[row - 1][y] = dp[row - 1][y + 1] + array[row - 1][y];
        }
        // 最后一列，我的值等于下边的值加上 array 中当前位置的值
        for (int x = row - 2; x >= 0; x--) {
            dp[x][col - 1] = dp[x + 1][col - 1] + array[x][col - 1];
        }
        // 对于其他任意位置的点，根据递归的关系求解
        for (int x = row - 2; x >= 0; x--) {
            for (int y = col - 2; y >= 0; y--) {
                int p1 = dp[x][y + 1];
                int p2 = dp[x + 1][y];
                dp[x][y] = Math.min(p1, p2) + array[x][y];
            }
        }
        return dp[0][0];
    }

    /**
     * 动态规划二：空间压缩
     * 在动态规划一方法可知，所有的节点都可以按行从右到左生成，这样我们使用一个 length == col 的一维数组来临时保存每行的计算结果
     * 到最后同样可以得到 (0,0) 位置的结果
     *
     * @param array 数组
     * @return int
     */
    private static int minPathSum3(int[][] array) {
        if (array == null || array.length == 0 || array[0] == null || array[0].length == 0) {
            return 0;
        }
        int row = array.length;
        int col = array[0].length;
        int[] dp = new int[col];
        dp[col - 1] = array[row - 1][col - 1];
        for (int i = col - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] + array[row - 1][i];
        }
        for (int x = row - 2; x >= 0; x--) {
            dp[col - 1] += array[x][col - 1];
            for (int y = col - 2; y >= 0; y--) {
                int p1 = dp[y + 1];
                int p2 = dp[y];
                dp[y] = Math.min(p1, p2) + array[x][y];
            }
        }
        return dp[0];
    }
}
