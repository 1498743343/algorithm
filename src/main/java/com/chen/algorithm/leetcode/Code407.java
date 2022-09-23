package com.chen.algorithm.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * code407 leetcode 第407题
 * <a href="https://leetcode.cn/problems/trapping-rain-water-ii/">测试链接</a>
 * 三维接雨水问题
 *
 * @author chenzihan
 * @date 2022/09/23
 */
public class Code407 {
    public static void main(String[] args) {
        // [[5,8,7,7],[5,2,1,5],[7,1,7,1],[8,9,6,9],[9,8,9,9]]
        int[][] test = new int[5][4];
        test[0] = new int[]{5, 8, 7, 7};
        test[1] = new int[]{5, 2, 1, 5};
        test[2] = new int[]{7, 1, 7, 1};
        test[3] = new int[]{8, 9, 6, 9};
        test[4] = new int[]{9, 8, 9, 9};
        int ans = trapRainWater(test);
        System.out.println(ans);
    }

    /**
     * 思路：先把四边都压栈，然后弹出栈顶元素，这样就找到了最小的边，然后去看他的四周，有没有没访问过的节点，找到以后计算他们的存水量
     *
     * @param heightMap 高度图
     * @return int
     */
    public static int trapRainWater(int[][] heightMap) {
        if (heightMap.length <= 2 || heightMap[0].length <= 2) {
            return 0;
        }
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        for (int y = 0; y < n; y++) {
            queue.add(new int[]{y, heightMap[0][y]});
            visited[0][y] = true;
            queue.add(new int[]{(m - 1) * n + y, heightMap[m - 1][y]});
            visited[m - 1][y] = true;
        }

        for (int x = 1; x < m - 1; x++) {
            queue.add(new int[]{x * n, heightMap[x][0]});
            visited[x][0] = true;
            queue.add(new int[]{(x + 1) * n - 1, heightMap[x][n - 1]});
            visited[x][n - 1] = true;
        }

        int ans = 0;
        int[] dirs = new int[]{1, 0, -1, 0, 1};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = cur[0] / n + dirs[i];
                int y = cur[0] % n + dirs[i + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
                    int max = Math.max(cur[1], heightMap[x][y]);
                    ans += max - heightMap[x][y];
                    visited[x][y] = true;
                    queue.add(new int[]{x * n + y, max});
                }
            }
        }
        return ans;
    }
}
