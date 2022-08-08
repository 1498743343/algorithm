package com.chen.algorithm.day20;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * W表示你初始的资金
 * 说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * 输出：你最后获得的最大钱数。
 *
 * @author chenzihan
 * @date 2022/08/08
 */
public class Code04IPO {


    /**
     * 准备两个堆：最小花费堆，最大利润堆
     * 将所有的项目都添加到最小花费堆中，循环 K 次以下逻辑：
     * 1. 将花费金额小于当前资金 W 的项目添加到最大利润堆中
     * 2. 如果最大利润堆中没有元素了，说明做不起项目了，直接返回 W
     * 3. 如果最大利润堆中有元素，说明可以做项目，取出堆顶元素，即最赚钱的项目，然后将利润累加到当前资金 W 中
     *
     * @param K       k次
     * @param W       起始资金
     * @param Profits 利润
     * @param Capital 资本
     * @return int
     */
    public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
        PriorityQueue<Program> minCostQueue = new PriorityQueue<>(Comparator.comparingInt(program -> program.c));
        PriorityQueue<Program> maxProfitQueue = new PriorityQueue<>((program1, program2) -> program2.p - program1.p);
        for (int i = 0; i < Profits.length; i++) {
            minCostQueue.add(new Program(Profits[i], Capital[i]));
        }
        for (int i = 0; i < K; i++) {
            while (!minCostQueue.isEmpty() && minCostQueue.peek().c <= W) {
                maxProfitQueue.add(minCostQueue.poll());
            }
            if (maxProfitQueue.isEmpty()) {
                return W;
            }
            W += maxProfitQueue.poll().p;
        }
        return W;
    }

    public static class Program {
        public int p;
        public int c;

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

}
