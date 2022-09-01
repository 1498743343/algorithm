package com.chen.algorithm.day27;

/**
 * code05鲍勃死
 * 给定5个参数，N，M，row，col，k：表示在N*M的区域上，醉汉Bob初始在(row,col)位置Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 *
 * @author chenzihan
 * @date 2022/09/01
 */
public class Code05BobDie {

    public static void main(String[] args) {
        System.out.println(livePosibility1(6, 6, 10, 50, 50));
        System.out.println(livePosibility2(6, 6, 10, 50, 50));
    }

    /**
     * 递归方法
     *
     * @param row 起始位置的行数 index
     * @param col 起始位置的列数 index
     * @param k   k步
     * @param n   n行
     * @param m   m列
     * @return boolean
     */
    private static double livePosibility1(int row, int col, int k, int n, int m) {
        int lives = process(row, col, k, n, m);
        double all = Math.pow(4, k);
        return lives / all;
    }

    private static int process(int x, int y, int rest, int n, int m) {
        if (x < 0 || x >= n || y < 0 || y >= m) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        int p1 = process(x - 1, y, rest - 1, n, m);
        int p2 = process(x + 1, y, rest - 1, n, m);
        int p3 = process(x, y - 1, rest - 1, n, m);
        int p4 = process(x, y + 1, rest - 1, n, m);
        return p1 + p2 + p3 + p4;
    }

    /**
     * 动态规划：
     * 根据递归方法可知，结果和三个参数有关, x,y,rest
     * 观察 rest 的依赖关系可知，每一层的各个点互不影响，当前点的值只和下一层的节点相关
     *
     * @param x    x
     * @param y    ysss
     * @param rest 休息s
     * @param n    n
     * @param m    米
     * @return double
     */
    private static double livePosibility2(int x, int y, int rest, int n, int m) {
        double all = Math.pow(4, rest);
        int[][][] dp = new int[n][m][rest + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j][0] = 1;
            }
        }
        // 按层组装 dp
        for (int k = 1; k <= rest; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    dp[i][j][k] = pick(i + 1, j, k - 1, n, m, dp)
                            + pick(i - 1, j, k - 1, n, m, dp)
                            + pick(i, j + 1, k - 1, n, m, dp)
                            + pick(i, j - 1, k - 1, n, m, dp);
                }
            }
        }
        return dp[x][y][rest] / all;
    }

    public static int pick(int x, int y, int rest, int n, int m, int[][][] dp) {
        if (x < 0 || x >= n || y < 0 || y >= m) {
            return 0;
        }
        return dp[x][y][rest];
    }

}
