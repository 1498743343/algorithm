package com.chen.algorithm.day21;


/**
 * code01朋友圈
 * 有 n 个人，其中一些彼此认识，另一些不认识。如果 a 和 b 认识， b 和 c 认识，那么 a 和 c 间接认识，abc就组成了一个朋友圈
 * 给定一个 n * n 的二维数组，如果 arr[i][j] == 1，那么说明 i 和 j 认识，同样 arr[j][i] 也肯定等于 1
 * 请求解一共有多少个朋友圈<br/>
 * 解题思路：使用并查集来解决<br/>
 * 测试链接：<a href="https://leetcode.cn/problems/number-of-provinces/submissions/">LeetCode</a>
 *
 * @author chenzihan
 * @date 2022/08/09
 */
public class Code01FriendCircles {

    public int findCircleNum(int[][] arr) {
        int size = arr.length;
        UnionFind unionFind = new UnionFind(size);
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (arr[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.sets;
    }

    static class UnionFind {
        /**
         * 父节点映射数组
         */
        public int[] parent;
        /**
         * 集合大小映射数组
         */
        public int[] size;
        /**
         * 帮助设置父节点的数组
         */
        public int[] help;
        /**
         * 集合数量
         */
        public int sets;

        public UnionFind(int size) {
            this.parent = new int[size];
            this.size = new int[size];
            this.help = new int[size];
            this.sets = size;
            // 初始化各个节点的父节点和集合大小
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                this.size[i] = 1;
            }
        }

        public void union(int a, int b) {
            int aHead = findFather(a);
            int bHead = findFather(b);
            // 如果是同一个父节点，说明 a 和 b 属于同一个集合，不需要在进行合并
            if (aHead != bHead) {
                // 根据 a 和 b 的情况设置父节点、集合大小和 sets，原则就是小的挂在大的上面
                if (size[aHead] > size[bHead]) {
                    parent[bHead] = aHead;
                    size[aHead] = size[aHead] + size[bHead];
                } else {
                    parent[aHead] = bHead;
                    size[bHead] = size[aHead] + size[bHead];
                }
                sets--;
            }
        }

        public int findFather(int index) {
            int helpIndex = 0;
            while (index != parent[index]) {
                help[helpIndex++] = index;
                index = parent[index];
            }
            // 因为在while中，最后一次将 helpIndex 多加了一次，所以要先减一次在进行下面的逻辑
            helpIndex--;
            while (helpIndex >= 0) {
                parent[help[helpIndex]] = index;
                helpIndex--;
            }
            return index;
        }
    }
}
