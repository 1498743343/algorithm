package com.chen.algorithm.leetcode;



/**
 * code79 leetcode 第79题
 * <a href="https://leetcode.cn/problems/word-search/?favorite=2cktkvj">测试链接</a>
 * 尝试了两个版本，开始用 list 去保存已经访问过的位置超时了，后面改成数组就可以了
 * 整个题还是比较简单的，找到其实位置，然后发散的去找能拼成 word 的字符就可以了，边界条件需要注意一下
 *
 * @author chenzihan
 * @date 2022/09/16
 */
public class Code79 {

    public boolean exist(char[][] board, String word) {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                if (board[x][y] == word.charAt(0)) {
                    if (process(x, y, 0, new boolean[board.length][board[0].length], board, word)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean process(int x, int y, int index, boolean[][] visited, char[][] board, String word) {
        if (board[x][y] != word.charAt(index)) {
            return false;
        } else if (index == word.length() - 1) {
            return true;
        }
        visited[x][y] = true;
        if (x >= 1 && !visited[x - 1][y]) {
            boolean result = process(x - 1, y, index + 1, visited, board, word);
            if (result) {
                return true;
            }
        }
        if (x <= board.length - 2 && !visited[x + 1][y]) {
            boolean result = process(x + 1, y, index + 1, visited, board, word);
            if (result) {
                return true;
            }
        }
        if (y <= board[0].length - 2 && !visited[x][y + 1]) {
            boolean result = process(x, y + 1, index + 1, visited, board, word);
            if (result) {
                return true;
            }
        }
        if (y >= 1 && !visited[x][y - 1]) {
            boolean result = process(x, y - 1, index + 1, visited, board, word);
            if (result) {
                return true;
            }
        }
        visited[x][y] = false;
        return false;
    }
}
