package com.chen.algorithm.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * code399 leetcode 第399题
 * <a href="https://leetcode.cn/problems/evaluate-division/">测试链接</a>
 * 很典型的并查集问题，开始我用暴力手段来做，但是涉及到了map在遍历过程中的改动和扩容，所以没通过，后来想到了用并查集
 *
 * @author chenzihan
 * @date 2022/09/30
 */
public class Code399 {
    public static void main(String[] args) {
        //[["a","e"],["b","e"]]
        //[4.0,3.0]
        //[["a","b"],["e","e"],["x","x"]]
        //equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
        List<List<String>> equations = List.of(List.of("a", "e"), List.of("b", "e"));
        double[] values = {4.0, 3.0};
        List<List<String>> queries = List.of(List.of("a", "b"), List.of("e", "e"), List.of("x", "x"));
        Code399 code399 = new Code399();
        double[] doubles = code399.calcEquation(equations, values, queries);
        for (double value : doubles) {
            System.out.print(value + " ");
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int n = equations.size();
        Map<String, Integer> map = new HashMap<>();
        UnionFind uf = new UnionFind(n * 2);
        int index = 0;
        for (int i = 0; i < n; i++) {
            List<String> info = equations.get(i);
            String a = info.get(0);
            String b = info.get(1);
            if (!map.containsKey(a)) {
                map.put(a, index++);
            }
            if (!map.containsKey(b)) {
                map.put(b, index++);
            }
            uf.union(map.get(a), map.get(b), values[i]);
        }
        double[] ans = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> info = queries.get(i);
            Integer a = map.get(info.get(0));
            Integer b = map.get(info.get(1));
            if (a == null || b == null) {
                ans[i] = -1.0;
            } else {
                ans[i] = uf.getDistance(a, b);
            }
        }
        return ans;
    }

    static class UnionFind {
        public int[] parent;
        public double[] weights;
        public int[] stack;

        public UnionFind(int size) {
            parent = new int[size];
            weights = new double[size];
            stack = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                weights[i] = 1.0;
            }
        }

        public double getDistance(int a, int b) {
            int aHead = findFather(a);
            int bHead = findFather(b);
            if (aHead != bHead) {
                return -1.0;
            } else {
                return weights[a] / weights[b];
            }
        }

        public void union(int a, int b, double value) {
            int aHead = findFather(a);
            int bHead = findFather(b);
            if (aHead != bHead) {
                parent[bHead] = a;
                weights[bHead] = (weights[bHead] / weights[b]) / value;
            }
        }

        public int findFather(int id) {
            int index = 0;
            while (id != parent[id]) {
                stack[index++] = id;
                id = parent[id];
            }
            index--;
            if (index > 0) {
                double weight = weights[stack[index--]];
                while (index >= 0) {
                    int curId = stack[index--];
                    parent[curId] = id;
                    weight *= weights[curId];
                    weights[curId] = weight;
                }
            }
            return id;
        }
    }
}
