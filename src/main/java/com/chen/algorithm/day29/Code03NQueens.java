package com.chen.algorithm.day29;

/**
 * code03 n 皇后问题
 *
 * @author chenzihan
 * @date 2022/09/26
 */
public class Code03NQueens {

    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process(record, 0);
    }

    private static int process(int[] record, int index) {
        if (index == record.length) {
            return 1;
        }
        int ans = 0;
        // 将皇后放在第 index 行， 第 i 列
        for (int i = 0; i < record.length; i++) {
            if (isOK(record, index, i)) {
                record[index] = i;
                ans += process(record, index + 1);
            }
        }
        return ans;
    }

    private static boolean isOK(int[] record, int x, int y) {
        // 查看 x 行前面每一行放置皇后的位置，如果有在同一列或者同一斜线上的，返回 false
        for (int i = 0; i < x; i++) {
            if (record[i] == y || Math.abs(record[i] - y) == Math.abs(i - x)) {
                return false;
            }
        }
        return true;
    }

    // 请不要超过32皇后问题
    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        // 将 limit 右侧 n 个位上的数置为 1
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    private static int process2(int limit, int colLimit, int leftLimit, int rightLimit) {
        // 如果 limit == colLimit，说明每一行都找到了合适的位置，此时是一个正确的结果
        if (limit == colLimit) {
            return 1;
        }
        // 找到此时可以选择的位置
        // 将列限制、左限制、右限制已经占的位，置为 0 ，剩下的就是可以选择的位置
        int pos = limit & (~(colLimit | leftLimit | rightLimit));
        int ans = 0;
        int mostRightOne;
        // 从右到左，每次取 pos 的最右侧位置去尝试，直到 pos 为 0
        while (pos != 0) {
            mostRightOne = pos & (-pos);
            pos ^= mostRightOne;
            // colLimit | mostRightOne 是之前列的影响加上当前已选择列的影响，合并到一起传给下一层
            // (leftLimit | mostRightOne) << 1，当前行的左限制为 mostRightOne << 1，但是因为当前行被 leftLimit 限制了左边，而当前行的
            // leftLimit 传递到下一行还需要往左移一位，因为一条斜线上往左下拓展，往下走一步，同时需要往左走一步。所以合并起来就是 (leftLimit | mostRightOne) << 1
            // (rightLimit | mostRightOne) >>> 1，与左限制同理，但是右移是带符号的
            ans += process2(limit, colLimit | mostRightOne, (leftLimit | mostRightOne) << 1,
                    (rightLimit | mostRightOne) >> 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 10;

        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }
}
