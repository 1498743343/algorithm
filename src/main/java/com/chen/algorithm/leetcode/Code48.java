package com.chen.algorithm.leetcode;

/**
 * code48 leetcode 第48题
 * <a href="https://leetcode.cn/problems/rotate-image/submissions/">测试链接</a>
 * 整体思路就是寻找任意位置 (x,y) 每次顺时针旋转 90° 以后的坐标规律
 * 观察可以发现只要不是正中心位置，每个位置都可以经过四次旋转回到原来的位置，并且其他三个点可以用 x 和 y进行表示，这样我们就可以将她们之间的值进行变换
 * 通过上面的规律，我们还需要找到旋转的边界，对于第一行，我们需要将其中 length - 1个点进行旋转即可，因为(0,0)位置反转后就是(0,length-1)位置
 * 第二行，我们需要从(1,1)位置开始遍历，因为(1,0)位置在第一行的遍历中已经旋转过了，第二行的结束位置是 length - 1 - 1，所以可以发现 x 的约束条件是 [y,n-y-1]
 * 因为旋转是基于中心位置做的，所以我们只需要旋转到中间行即可
 * 至此我们已经找到了 x 和 y 的约束条件和转换规律
 *
 * @author chenzihan
 * @date 2022/09/14
 */
public class Code48 {
    public static void main(String[] args) {
        int[][] arr = new int[3][3];
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {4, 5, 6};
        int[] arr3 = {7, 8, 9};
        arr[0] = arr1;
        arr[1] = arr2;
        arr[2] = arr3;
        rotate(arr);
    }

    /**
     * 这个方法和真正提交的方法其实是一样的，只不过 rotate 方法直接用四个点的关系来表示了转换，更加清晰
     *
     * @param matrix 矩阵
     */
    public static void rotate1(int[][] matrix) {
        int n = matrix.length;
        for (int y = 0; y < n - 1; y++) {
            for (int x = y; x < n - y - 1; x++) {
                int curX = x;
                int curY = y;
                int tmp = matrix[curY][n - curX - 1];
                matrix[curY][n - curX - 1] = matrix[curX][curY];
                int tmpX = curX;
                curX = curY;
                curY = n - tmpX - 1;
                while (!(curX == x && curY == y)) {
                    int next = matrix[curY][n - curX - 1];
                    matrix[curY][n - curX - 1] = tmp;
                    tmp = next;
                    tmpX = curX;
                    curX = curY;
                    curY = n - tmpX - 1;
                }
            }
        }
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int y = 0; y <= (n - 1) / 2; y++) {
            for (int x = y; x < n - y - 1; x++) {
                int tmp = matrix[x][y];
                matrix[x][y] = matrix[n - y - 1][x];
                matrix[n - y - 1][x] = matrix[n - x - 1][n - y - 1];
                matrix[n - x - 1][n - y - 1] = matrix[y][n - x - 1];
                matrix[y][n - x - 1] = tmp;
            }
        }
    }
}
