package com.chen.algorithm.day13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * code01 找到线段重合的最大值
 * 给定很多线段，每个线段都有两个数[start, end]，表示线段开始位置和结束位置，左右都是闭区间，规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 *
 * @author chenzihan
 * @date 2022/06/10
 */
public class Code01CoverMax {
    public static class Line {
        public int start;
        public int end;

        public Line(int s, int e) {
            start = s;
            end = e;
        }

        public int getStart() {
            return start;
        }
    }

    public static void main(String[] args) {
        int maxSize = 100;
        int minLeft = -100;
        int maxRight = 200;
        int testTimes = 200000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Line[] lines = generateLines(maxSize, minLeft, maxRight);
            int ans1 = maxCover1(lines);
            int ans2 = maxCover2(lines);
            if (ans1 != ans2) {
                System.out.println("出错了!");
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static int maxCover2(Line[] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (Line line : lines) {
            min = Math.min(min, line.start);
            max = Math.max(max, line.end);
        }
        int result = 0;
        double num = min + 0.5;
        while (num < max) {
            int currentCount = 0;
            for (Line line : lines) {
                if (line.start < num && line.end > num) {
                    currentCount++;
                }
            }
            num++;
            result = Math.max(result, currentCount);
        }
        return result;
    }

    private static int maxCover1(Line[] lines) {
        if (lines == null || lines.length == 0) {
            return 0;
        }
        // 先将所有线段按照 start 的大小进行升序排列
        Arrays.sort(lines, Comparator.comparingInt(Line::getStart));
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int result = 0;
        for (Line line : lines) {
            // 将当前 line 的 start 和堆中的值进行比较，将堆中 <= line.start 的值弹出，然后将 line.end 放入堆中，此时堆中的元素个数就是可以穿过 line.start 这个值的线段数
            while (!heap.isEmpty() && heap.peek() <= line.start) {
                heap.poll();
            }
            heap.offer(line.end);
            result = Math.max(heap.size(), result);
        }
        return result;
    }

    private static Line[] generateLines(int maxSize, int minStart, int maxEnd) {
        int size = (int) (Math.random() * (maxSize + 1));
        Line[] lines = new Line[size];
        for (int i = 0; i < size; i++) {
            int a = minStart + (int) (Math.random() * (maxEnd - minStart + 1));
            int b = minStart + (int) (Math.random() * (maxEnd - minStart + 1));
            if (a == b) {
                if (a == maxEnd) {
                    a--;
                } else {
                    a++;
                }
            }
            int start = Math.min(a, b);
            int end = Math.max(a, b);
            lines[i] = new Line(start, end);
        }
        return lines;
    }

}
