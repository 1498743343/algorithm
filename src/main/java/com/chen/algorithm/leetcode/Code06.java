package com.chen.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * code06 leetcode 第6题
 * <a href="https://leetcode.cn/problems/zigzag-conversion/submissions/">测试链接</a>
 *
 * @author chenzihan
 * @date 2022/09/07
 */
public class Code06 {
    /**
     * 转换
     *
     * @param s       字符串
     * @param numRows 行数
     * @return {@link String}
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        // 用于遍历行的变量
        int rowNum = 0;
        // 用于转换遍历方式的变量
        int flag = -1;
        char[] arr = s.toCharArray();
        for (char a : arr) {
            rows.get(rowNum).append(a);
            // 当 row 来到第 0 行或者最后一行时，需要变换遍历的方式
            if (rowNum == 0 || rowNum == numRows - 1) {
                flag = -flag;
            }
            rowNum += flag;
        }
        StringBuilder ans = new StringBuilder();
        for (StringBuilder sb : rows) {
            ans.append(sb);
        }
        return ans.toString();
    }
}
