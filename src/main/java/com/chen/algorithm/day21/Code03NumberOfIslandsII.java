package com.chen.algorithm.day21;

import java.util.*;

/**
 * code03 岛问题二：相比与岛问题一，岛问题二是动态添加的一个问题
 * 给定一个 m * n 规格的范围，二维 positions 中记录的是需要动态添加的岛，返回每次添加一个岛以后，一共有多少个岛的集合
 * 测试链接：<a href="https://leetcode.cn/problems/number-of-islands-ii/">...</a>
 * 解题思路一：使用一维数组实现并查集，完成题目
 * 解题思路二：当 m 和 n 很大，但是 positions 数量很小时，使用数组实现会浪费大量内存并且完成很重的初始化，可以使用容器实现并查集，并使用 x_y 的字符串来标识具体位置
 *
 * @author chenzihan
 * @date 2022/08/12
 */
public class Code03NumberOfIslandsII {

    public static void main(String[] args) {
        int mMax = 10;
        int nMax = 10;
        int tryTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < tryTimes; i++) {
            int m = (int) (Math.random() * (mMax + 1));
            int n = (int) (Math.random() * (nMax + 1));
            int[][] positions = generateRandomArray(m, n);
            List<Integer> result1 = numIslands21(m, n, positions);
            List<Integer> result2 = numIslands22(m, n, positions);
            if (!test(result1, result2)) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static boolean test(List<Integer> result1, List<Integer> result2) {
        if (result1 == null && result2 == null) {
            return true;
        }
        if (result1 == null || result2 == null) {
            return false;
        }
        int size = result1.size();
        if (size != result2.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!result1.get(i).equals(result2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static int[][] generateRandomArray(int m, int n) {
        int[][] ans = new int[m * n][2];
        for (int i = 0; i < m * n; i++) {
            int[] onePosition = new int[2];
            onePosition[0] = (int) (Math.random() * m);
            onePosition[1] = (int) (Math.random() * n);
            ans[i] = onePosition;
        }
        return ans;
    }

    public static List<Integer> numIslands21(int m, int n, int[][] positions) {
        UnionFind1 unionFind = new UnionFind1(m, n);
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            ans.add(unionFind.connect(position[0], position[1]));
        }
        return ans;
    }

    static class UnionFind1 {
        public int[] parent;
        public int[] size;
        public int[] help;
        public int row;
        public int line;
        public int sets;

        public UnionFind1(int m, int n) {
            this.row = m;
            this.line = n;
            int length = m * n;
            this.parent = new int[length];
            this.size = new int[length];
            this.help = new int[length];
            sets = 0;
        }

        public Integer connect(int x, int y) {
            int index = index(x, y);
            // 只有当前节点没有加入过，才执行 connect 逻辑
            if (size[index] == 0) {
                parent[index] = index;
                size[index] = 1;
                sets++;
                // 上
                union(x, y, x, y - 1);
                // 下
                union(x, y, x, y + 1);
                // 左
                union(x, y, x - 1, y);
                // 右
                union(x, y, x + 1, y);
            }
            return sets;
        }

        private void union(int i, int j, int a, int b) {
            // 数组下标越界，直接停止
            if (i < 0 || i == row || j < 0 || j == line || a < 0 || a == row || b < 0 || b == line) {
                return;
            }
            // 两个点中有一个不是没有记录过，直接停止
            if (size[index(i, j)] == 0 || size[index(a, b)] == 0) {
                return;
            }
            int father1 = findFather(i, j);
            int father2 = findFather(a, b);
            if (father1 != father2) {
                int size1 = size[father1];
                int size2 = size[father2];
                if (size1 > size2) {
                    size[father1] = size1 + size2;
                    parent[father2] = father1;
                } else {
                    size[father2] = size1 + size2;
                    parent[father1] = father2;
                }
                sets--;
            }
        }

        private int findFather(int x, int y) {
            int helpIndex = 0;
            int index = index(x, y);
            while (parent[index] != index) {
                help[helpIndex++] = index;
                index = parent[index];
            }
            while (--helpIndex >= 0) {
                parent[help[helpIndex]] = index;
            }
            return index;
        }

        private int index(int x, int y) {
            return y * row + x;
        }
    }

    public static List<Integer> numIslands22(int m, int n, int[][] positions) {
        UnionFind2 uf = new UnionFind2();
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            ans.add(uf.connect(position[0], position[1]));
        }
        return ans;
    }

    static class UnionFind2 {
        public HashMap<String, String> parent;
        public HashMap<String, Integer> size;
        public Stack<String> help;

        public UnionFind2() {
            parent = new HashMap<>();
            size = new HashMap<>();
            help = new Stack<>();
        }

        public int connect(int x, int y) {
            String cur = x + "_" + y;
            if (!parent.containsKey(cur)) {
                parent.put(cur, cur);
                size.put(cur, 1);
                union(cur, x - 1 + "_" + y);
                union(cur, x + 1 + "_" + y);
                union(cur, x + "_" + (y - 1));
                union(cur, x + "_" + (y + 1));
            }
            return size.size();
        }

        private void union(String a, String b) {
            if (!parent.containsKey(a) || !parent.containsKey(b)) {
                return;
            }
            String aHead = findFather(a);
            String bHead = findFather(b);
            if (!aHead.equals(bHead)) {
                int aSize = size.get(aHead);
                int bSize = size.get(bHead);
                if (aSize > bSize) {
                    aSize = aSize + bSize;
                    size.put(aHead, aSize);
                    parent.put(bHead, aHead);
                    size.remove(bHead);
                } else {
                    bSize = aSize + bSize;
                    size.put(bHead, bSize);
                    parent.put(aHead, bHead);
                    size.remove(aHead);
                }
            }
        }

        private String findFather(String cur) {
            while (!cur.equals(parent.get(cur))) {
                help.add(cur);
                cur = parent.get(cur);
            }
            while (!help.isEmpty()) {
                parent.put(help.pop(), cur);
            }
            return cur;
        }
    }

}
