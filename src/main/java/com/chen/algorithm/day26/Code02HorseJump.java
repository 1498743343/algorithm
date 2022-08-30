package com.chen.algorithm.day26;

/**
 * code02马跳
 * 有一个象棋的棋盘，把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 * 10条横线，9条纵先，即右上角的坐标是(9,8)
 * 给你三个 参数 x，y，k
 * 返回“马”从(0,0)位置出发，必须走k步
 * 最后落在(x,y)上的方法数有多少种?
 *
 * @author chenzihan
 * @date 2022/08/30
 */
public class Code02HorseJump {

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(jump(x, y, step));
        System.out.println(jumpDp(x, y, step));
        System.out.println(dp(x, y, step));
    }

    public static int dp(int a, int b, int k) {
        int[][][] dp = new int[10][9][k + 1];
        dp[a][b][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 9; y++) {
                    int ways = pick(dp, x + 2, y + 1, rest - 1);
                    ways += pick(dp, x + 1, y + 2, rest - 1);
                    ways += pick(dp, x - 1, y + 2, rest - 1);
                    ways += pick(dp, x - 2, y + 1, rest - 1);
                    ways += pick(dp, x - 2, y - 1, rest - 1);
                    ways += pick(dp, x - 1, y - 2, rest - 1);
                    ways += pick(dp, x + 1, y - 2, rest - 1);
                    ways += pick(dp, x + 2, y - 1, rest - 1);
                    dp[x][y][rest] = ways;
                }
            }
        }
        return dp[0][0][k];
    }

    public static int pick(int[][][] dp, int x, int y, int rest) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return dp[x][y][rest];
    }

    /**
     * 暴力递归
     *
     * @param x    横坐标
     * @param y    纵坐标
     * @param step 步数
     * @return int
     */
    public static int jump(int x, int y, int step) {
        return process(0, 0, step, x, y);
    }

    /**
     * 递归求解
     *
     * @param x       当前横坐标
     * @param y       当前纵坐标
     * @param step    剩余步数
     * @param targetX 目标点横坐标
     * @param targetY 目标点纵坐标
     * @return int
     */
    private static int process(int x, int y, int step, int targetX, int targetY) {
        // base case：越界说明是一个错误的答案，返回 0
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        // base case：没有剩余步数时，当前位置是目标位置，说明是正确的解，返回 1，否则返回 0
        if (step == 0) {
            if (x == targetX && y == targetY) {
                return 1;
            } else {
                return 0;
            }
        }

        // 马走日，把所有的情况都列出来然后累加
        return process(x + 1, y + 2, step - 1, targetX, targetY)
                + process(x + 2, y + 1, step - 1, targetX, targetY)
                + process(x + 2, y - 1, step - 1, targetX, targetY)
                + process(x + 1, y - 2, step - 1, targetX, targetY)
                + process(x - 1, y - 2, step - 1, targetX, targetY)
                + process(x - 2, y - 1, step - 1, targetX, targetY)
                + process(x - 2, y + 1, step - 1, targetX, targetY)
                + process(x - 1, y + 2, step - 1, targetX, targetY);
    }

    /**
     * dp跳
     * 动态规划：根据暴力递归方法可以知道，一个点的值和三个变量有关：x,y,step
     * 用 step 做 z 轴建立三维坐标系可知，每一层的点互不影响，每个点只和自己下层的8个(全部不越界的情况下是8个)点的值有关
     * 综上，建立三维数组来求解
     *
     * @param targetX 目标点横坐标
     * @param targetY 目标点纵坐标
     * @param step    步数
     * @return int
     */
    public static int jumpDp(int targetX, int targetY, int step) {
        int[][][] dp = new int[10][9][step + 1];
        // base case：先填第一层，因为是每层向下依赖，所以第一层就是 base case
        // 当且仅当 横坐标为 x，纵坐标为 y时，满足
        dp[targetX][targetY][0] = 1;
        for (int stepLeft = 1; stepLeft <= step; stepLeft++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 9; y++) {
                    dp[x][y][stepLeft] = pick(x + 1, y + 2, stepLeft - 1, dp)
                            + pick(x + 2, y + 1, stepLeft - 1, dp)
                            + pick(x + 2, y - 1, stepLeft - 1, dp)
                            + pick(x + 1, y - 2, stepLeft - 1, dp)
                            + pick(x - 1, y - 2, stepLeft - 1, dp)
                            + pick(x - 2, y - 1, stepLeft - 1, dp)
                            + pick(x - 2, y + 1, stepLeft - 1, dp)
                            + pick(x - 1, y + 2, stepLeft - 1, dp);
                }
            }
        }
        return dp[0][0][step];
    }

    public static int pick(int x, int y, int step, int[][][] dp) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        } else {
            return dp[x][y][step];
        }
    }
}
