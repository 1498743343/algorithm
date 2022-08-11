package com.chen.algorithm.day21;

/**
 * code02 岛问题
 * 给定一个二维数组matrix，里面的值不是1就是0，上、下、左、右相邻的1认为是一片岛，返回matrix中岛的数量
 * <a href="https://leetcode.cn/problems/number-of-islands/">leetcode链接</a>
 * numIslands 和 numIslands2 方法都可以直接提交并通过
 *
 * @author chenzihan
 * @date 2022/08/10
 */
public class Code02NumberOfIslands {
    /**
     * 方法一：递归渲染
     *
     * @param grid 入参数组
     * @return int
     */
    public int numIslands(char[][] grid) {
        int landNum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    landNum++;
                    process(grid, i, j);
                }
            }
        }
        return landNum;
    }

    private void process(char[][] grid, int i, int j) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] != '1') {
            return;
        }
        // 这里改成什么都可以，只要不是 '1' 就行，否则会无限递归下去
        grid[i][j] = '0';
        process(grid, i + 1, j);
        process(grid, i - 1, j);
        process(grid, i, j + 1);
        process(grid, i, j - 1);
    }

    /**
     * 方法二：优化的并查集解决
     *
     * @param grid 入参数组
     * @return int
     */
    public static int numIslands2(char[][] grid) {
        UnionFind1 unionFind = new UnionFind1(grid);
        // 下面分了 3 个 for 循环是为了避免越界的判断，合成 1 个也没问题
        // 需要注意的是：两个节点都是 '1' 才可以进行合并
        for (int i = 1; i < grid[0].length; i++) {
            if (grid[0][i] == '1' && grid[0][i - 1] == '1') {
                unionFind.union(0, i, 0, i - 1);
            }
        }
        for (int i = 1; i < grid.length; i++) {
            if (grid[i][0] == '1' && grid[i - 1][0] == '1') {
                unionFind.union(i, 0, i - 1, 0);
            }
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    if (grid[i - 1][j] == '1') {
                        unionFind.union(i, j, i - 1, j);
                    }
                    if (grid[i][j - 1] == '1') {
                        unionFind.union(i, j, i, j - 1);
                    }
                }
            }
        }
        return unionFind.sets;
    }

    static class UnionFind1 {
        public int[] parent;
        public int[] size;
        public int[] help;
        // 一行有几个元素
        public int rowNum;
        public int sets;

        public UnionFind1(char[][] grid) {
            rowNum = grid[0].length;
            int length = grid.length;
            int arrSize = rowNum * length;
            parent = new int[arrSize];
            size = new int[arrSize];
            help = new int[arrSize];
            sets = 0;
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < rowNum; j++) {
                    if (grid[i][j] == '1') {
                        int index = index(i, j);
                        parent[index] = index;
                        size[index(i, j)] = 1;
                        sets++;
                    }
                }
            }
        }

        public int index(int line, int row) {
            return line * rowNum + row;
        }

        public int findFather(int i, int j) {
            int index = index(i, j);
            int helpIndex = 0;
            while (index != parent[index]) {
                help[helpIndex++] = index;
                index = parent[index];
            }
            while (--helpIndex >= 0) {
                parent[help[helpIndex]] = index;
            }
            return index;
        }

        public void union(int i, int j, int a, int b) {
            int father1 = findFather(i, j);
            int father2 = findFather(a, b);
            if (father1 != father2) {
                int size1 = size[father1];
                int size2 = size[father2];
                if (size1 > size2) {
                    parent[father2] = father1;
                    size[father1] = size1 + size2;
                } else {
                    parent[father1] = father2;
                    size[father2] = size1 + size2;
                }
                sets--;
            }
        }
    }
}
